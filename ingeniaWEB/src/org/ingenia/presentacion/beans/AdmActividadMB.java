package org.ingenia.presentacion.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;

import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.ActividadVO;
import org.ingenia.comunes.vo.CursoActividadVO;
import org.ingenia.comunes.vo.CursoVO;
import org.ingenia.comunes.vo.EstructuraVO;
import org.ingenia.comunes.vo.JuegoVO;
import org.ingenia.comunes.vo.UsuarioVO;
import org.ingenia.negocio.igestor.IGestorActividadesLocal;
import org.ingenia.presentacion.BaseMB;


@ManagedBean(name = "AdmActividadMB")
@SessionScoped
public class AdmActividadMB extends BaseMB {
	


	private static final long serialVersionUID = -4578987507032867585L;

	private ActividadVO actividadVO= new ActividadVO();
	private ActividadVO actividadVO1= new ActividadVO();
	private ActividadVO actividadVO2= null;
	private JuegoVO juegoVO=new JuegoVO();
	private String actividad;
	private List<JuegoVO> listaJuegos;
	private List<String> listaelegida;
	private List<EstructuraVO> listaestructuras;
	private List<EstructuraVO> estructralistaelegida;
	private List<ActividadVO> listaActividades;
	private int posicion;
	private boolean buscando=false;
	private UsuarioVO UsuarioVO=new UsuarioVO();	
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;

	private final static String NAV_IRACTIVIDADCURSO = "iractividadcurso";
	private final static String NAV_IRACTIVIDAD = "iractividad";
	private final static String NAV_IRADMACTIVIDAD = "iradminactividad";

	@EJB
	IGestorActividadesLocal gestorActividades;


	
	public AdmActividadMB(){
	
		
	}
	
	@PostConstruct
	public void init() {
		try {
			
			 faceContext=FacesContext.getCurrentInstance();
		        httpServletRequest=(HttpServletRequest)faceContext.getExternalContext().getRequest();
		        if(httpServletRequest.getSession().getAttribute("sessionUsuario")!=null)
		        {
		        	this.UsuarioVO=(UsuarioVO) httpServletRequest.getSession().getAttribute("sessionUsuario");
		            System.out.println("id profe"+UsuarioVO.getId());
		        	this.listaJuegos=gestorActividades.consultarJuegosDisponibles();
		        	setListaActividades(gestorActividades.consultarActividadesProfesor(UsuarioVO.getId()));
		        	}
		

		} catch (AdaptadorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	  public String nuevaActividad() {
			 actividadVO1 =null;		
	        this.actividadVO = new ActividadVO();   
	        this.actividadVO2 = null; 
	        try {
				this.listaestructuras=gestorActividades.consultarestructuras();
		    	this.listaelegida=consultarListaEstructuras(this.listaestructuras);
			} catch (AdaptadorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return NAV_IRACTIVIDAD;
	    }
	  

	  public String nuevaAsociarActividad() {
		  	actividadVO1 =null;
	        this.actividadVO = new ActividadVO();  
	        this.actividadVO2 = null; 
	    	try {
				this.listaestructuras=gestorActividades.consultarestructuras();
		    	this.listaelegida=consultarListaEstructuras(this.listaestructuras);
			} catch (AdaptadorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return NAV_IRACTIVIDADCURSO;
	    }
	
	public String buscar() {
		ActividadVO actividadVO = new ActividadVO();
		actividadVO.setNombre(actividad);
		actividadVO.setProfesor(this.UsuarioVO);
        System.out.println(actividadVO.getNombre());
		try {
			this.buscando=true;
			setListaActividades(gestorActividades.consultarActividadesPorNombre(actividadVO));

		} catch (AdaptadorException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e
							.getMessage()));
			e.printStackTrace();
		}
		return NAV_IRADMACTIVIDAD;
	}

	public void actualizar() {
		
		ActividadVO actividadVO = this.actividadVO;		
		actividadVO.setEstructuras(this.estructralistaelegida);

	try {	
			gestorActividades.modificarActividad(actividadVO);	
      		this.buscando=false;
      		} 
	
	catch (AdaptadorException e) {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erroraaaa",
						"Error de concersion de tipos!"));
		e.printStackTrace();
	} catch (Exception e) {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erroresss", e
						.getMessage()));
		e.printStackTrace();
	}
	FacesContext.getCurrentInstance().addMessage(
			null,
			new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
					"La operacion fue realizada satisfactoriamente !"));

	}

	public String crear() {
		String destino=null;
		if((!this.actividadVO.getNombre().equals("")) && (!this.actividadVO.getEnunciado().equals("")) && (estructralistaelegida.size()>0) ){
  		 ActividadVO actividadVO = this.actividadVO;		
	     actividadVO.setJuegoVO(juegoVO);
         actividadVO.setProfesor(this.UsuarioVO);
         actividadVO.setEstructuras(estructralistaelegida);
		try {
          		gestorActividades.crearActividad(actividadVO); 	
          		this.buscando=false;
          		destino=NAV_IRADMACTIVIDAD;

		} catch (AdaptadorException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erroraaaa",
							"Error de concersion de tipos!"));
			e.printStackTrace();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erroresss", e
							.getMessage()));
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
						"La operacion fue realizada satisfactoriamente !"));}
		else if (this.actividadVO.getNombre().equals("")) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nombre", "Debe ingresar el nombre de la actividad"));
		}
		else if (this.actividadVO.getEnunciado().equals("")) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nombre", "Debe ingresar el enunciado de la actividad"));
		}
		else if (estructralistaelegida.size()==0) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nombre", "Debe elegir como minimo una estructura"));
		}
		return destino;
	}
	
	public String crearAsociando() {
   String destino=null;
		if((!this.actividadVO.getNombre().equals("")) && (!this.actividadVO.getEnunciado().equals("")) && (estructralistaelegida.size()>0) ){
			ActividadVO actividadVO = this.actividadVO;		
	        actividadVO.setJuegoVO(juegoVO);
	        int idcurso=Integer.parseInt(recuperarParametro("idcurso"));
			CursoActividadVO cursoActividadVO = new CursoActividadVO();
         	CursoVO cursoVO=new CursoVO();
         	cursoVO.setIdcurso(idcurso);
			actividadVO.setProfesor(this.UsuarioVO);
			cursoActividadVO.setActividad(actividadVO);
			cursoActividadVO.setCurso(cursoVO);
	        actividadVO.setEstructuras(estructralistaelegida);
		try {
			gestorActividades.crearActividadCurso(cursoActividadVO); 
      		this.buscando=false;
      		destino="ircurso";

		} catch (AdaptadorException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erroraaaa",
							"Error de concersion de tipos!"));
			e.printStackTrace();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erroresss", e
							.getMessage()));
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
						"La operacion fue realizada satisfactoriamente !"));
		}
		else if (this.actividadVO.getNombre().equals("")) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nombre", "Debe ingresar el nombre de la actividad"));
		}
		else if (this.actividadVO.getEnunciado().equals("")) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nombre", "Debe ingresar el enunciado de la actividad"));
		}
		else if (estructralistaelegida.size()==0) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nombre", "Debe elegir como minimo una estructura"));
		}
		return destino;
	}
	
	public void actualizarAsociando() {

			ActividadVO actividadVO = this.actividadVO;		
	        int idcurso=Integer.parseInt(recuperarParametro("idcurso"));
	        CursoActividadVO cursoActividadVO = new CursoActividadVO();
	        CursoVO cursoVO=new CursoVO();
	        cursoVO.setIdcurso(idcurso);
	        cursoActividadVO.setActividad(actividadVO);
			cursoActividadVO.setPosicion(this.posicion);
			cursoActividadVO.setCurso(cursoVO);
		try {	
				gestorActividades.modificarActividadCurso(cursoActividadVO);	
          		this.buscando=false;
          		} 
		catch (AdaptadorException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erroraaaa",
							"Error de concersion de tipos!"));
			e.printStackTrace();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erroresss", e
							.getMessage()));
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
						"La operacion fue realizada satisfactoriamente !"));

	}


	public String irActividad() {

		 actividadVO1 =new ActividadVO();
		 try {
		
			this.listaestructuras=gestorActividades.consultarestructuras();
			//faltaria cargar las estrucutras asociadas a la actividad que se esta cargando y mirar si hay que editarlas

		} catch (AdaptadorException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext()
				.getRequestParameterMap();

		String id = params.get("idactividad");
		ActividadVO actividadVO = new ActividadVO();
		actividadVO.setIdactividad(Integer.parseInt(id));
		
		try {
			this.actividadVO = gestorActividades.consultarActividadVO(actividadVO);
			this.listaelegida=consultarListaEstructuras(this.actividadVO .getEstructuras());
			 if(this.actividadVO.getJuegoVO().getIdjuego()==1){
				 System.out.println("actualizando");
				 actividadVO2 =new ActividadVO();
				 }
			for (JuegoVO juego : listaJuegos) {
					if(juego.getIdjuego()==this.actividadVO.getJuegoVO().getIdjuego()){
						this.juegoVO=juego;		
					}
				}

		} catch (AdaptadorException e) {
			e.printStackTrace();
		}

		return NAV_IRACTIVIDAD;
	}
		
	
	private List<String> consultarListaEstructuras(List<EstructuraVO> estructuras) {
		// TODO Auto-generated method stub
		List<String> lista=new 	ArrayList<String>();
		if (estructuras!=null){
		   for(int j=0;estructuras.size()>j;j++){
			   lista.add(String.valueOf(estructuras.get(j).getIdEstructura()));
			   }}
		return lista;
	}

	private List<EstructuraVO> obtenerEstructuras() {
		// TODO Auto-generated method stub
	 List<EstructuraVO> estructura = new ArrayList<EstructuraVO>();
	 if (listaelegida!=null){
	   for(int i=0;this.listaelegida.size()>i;i++){
		   
		   for(int j=0;this.listaestructuras.size()>j;j++){
			   String temp=String.valueOf(listaestructuras.get(j).getIdEstructura());
			   if(listaelegida.get(i).equals(temp)){
			   estructura.add(listaestructuras.get(j));
			   j=listaestructuras.size()+1;
			   }
		   }
	   }}
		return estructura;
	}

	public String irActividadCurso() {

			 actividadVO1 =new ActividadVO();

			 FacesContext fc = FacesContext.getCurrentInstance();
			Map<String, String> params = fc.getExternalContext()
					.getRequestParameterMap();

			String id = params.get("idactividad");
			ActividadVO actividadVO = new ActividadVO();
			actividadVO.setIdactividad(Integer.parseInt(id));
			
			try {

				int idcurso=Integer.parseInt(recuperarParametro("idcurso"));
				CursoVO cursoVO=new CursoVO();
				cursoVO.setIdcurso(idcurso);
				this.posicion=gestorActividades.consultarPosicion(cursoVO,actividadVO);
				this.actividadVO = gestorActividades.consultarActividadVO(actividadVO);
	
				for (JuegoVO juego : listaJuegos) {
						if(juego.getIdjuego()==this.actividadVO.getJuegoVO().getIdjuego()){
							this.juegoVO=juego;		
						}
					}

			} catch (AdaptadorException e) {
				e.printStackTrace();
			}

			return NAV_IRACTIVIDADCURSO;
		}
	
	public void selectOneMenuListener(ValueChangeEvent event) {

	    Object newValue = event.getNewValue(); 
	    
	   String idjuego= (String) newValue;
	   if (idjuego.equals("1")){
		   actividadVO2 = new ActividadVO();
	    System.out.println("new valiue "+idjuego);
	 actividadVO.setLimite_movimientos(1);
	 this.juegoVO.setIdjuego(Integer.parseInt(idjuego));

	      }
	   else{
		   actividadVO2 = null;
	   }
	   FacesContext.getCurrentInstance().renderResponse();

	}
	
	
	public ActividadVO getActividadVO() {
		return actividadVO;
	}

	public void setActividadVO(ActividadVO actividadVO) {
		this.actividadVO = actividadVO;
	}

	public String getActividad() {
		return actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	public JuegoVO getJuegoVO() {
		return juegoVO;
	}

	public void setJuegoVO(JuegoVO juegoVO) {
		this.juegoVO = juegoVO;
	}

	public List<JuegoVO> getListaJuegos() {
    	try {
			this.listaJuegos=gestorActividades.consultarJuegosDisponibles();
		} catch (AdaptadorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaJuegos;
	}

	public void setListaJuegos(List<JuegoVO> listaJuegos) {
		this.listaJuegos = listaJuegos;
	}


	public ActividadVO getActividadVO1() {
		return actividadVO1;
	}

	public void setActividadVO1(ActividadVO actividadVO1) {
		this.actividadVO1 = actividadVO1;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public List<ActividadVO> getListaActividades() {
		try {

			if(buscando==false){
			setListaActividades(gestorActividades.consultarActividadesProfesor(UsuarioVO.getId()));
			}

		} catch (AdaptadorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaActividades;
	}

	public void setListaActividades(List<ActividadVO> listaActividades) {
		this.listaActividades = listaActividades;
	}

	public ActividadVO getActividadVO2() {
		return actividadVO2;
	}

	public void setActividadVO2(ActividadVO actividadVO2) {
		this.actividadVO2 = actividadVO2;
	}

	public List<EstructuraVO> getListaestructuras() {
		System.out.println("la vino a buscar");
		return listaestructuras;
	}

	public void setListaestructuras(List<EstructuraVO> listaestructuras) {
		this.listaestructuras = listaestructuras;
	}

	public List<String> getListaelegida() {
		return listaelegida;
	}

	public void setListaelegida(List<String> listaelegida) {
		this.listaelegida = listaelegida;
	}

	/**
	 * @return the estructralistaelegida
	 */
	public List<EstructuraVO> getEstructralistaelegida() {
        	  
      		this.estructralistaelegida=obtenerEstructuras();

		return estructralistaelegida;
	}

	/**
	 * @param estructralistaelegida the estructralistaelegida to set
	 */
	public void setEstructralistaelegida(List<EstructuraVO> estructralistaelegida) {
		this.estructralistaelegida = estructralistaelegida;
	}






}
