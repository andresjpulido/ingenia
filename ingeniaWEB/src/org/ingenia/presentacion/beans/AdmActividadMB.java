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
import org.ingenia.comunes.vo.ActividadxUsuarioVO;
import org.ingenia.comunes.vo.CursoActividadVO;
import org.ingenia.comunes.vo.CursoVO;
import org.ingenia.comunes.vo.EstructuraVO;
import org.ingenia.comunes.vo.JuegoVO;
import org.ingenia.comunes.vo.UsuarioVO;
import org.ingenia.negocio.igestor.IGestorActividadesLocal;
import org.ingenia.presentacion.BaseMB;
import org.ingenia.presentacion.ReglasNavegacion;


@ManagedBean(name = "AdmActividadMB")
@SessionScoped
public class AdmActividadMB extends BaseMB {
	


	private static final long serialVersionUID = -4578987507032867585L;

	private ActividadVO actividadVO= new ActividadVO();
	private ActividadVO actividadVO1= new ActividadVO();
	private ActividadVO actividadVO2= null;
	private List<ActividadxUsuarioVO> listaAvancesActividad;
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
	private UsuarioVO estudiante=new UsuarioVO();	
	private CursoVO curso=new CursoVO();
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;

	
	@EJB
	IGestorActividadesLocal gestorActividades;


	
	public AdmActividadMB(){
	
		
	}
	
	public void cargarlistas () {
		if(buscando==false){
			actividad="";
		try {
			
			 faceContext=FacesContext.getCurrentInstance();
		        httpServletRequest=(HttpServletRequest)faceContext.getExternalContext().getRequest();
		        if(httpServletRequest.getSession().getAttribute("sessionUsuario")!=null)
		        {
		        	this.UsuarioVO=(UsuarioVO) httpServletRequest.getSession().getAttribute("sessionUsuario");
		        	this.listaJuegos=gestorActividades.consultarJuegosDisponibles();
		        	setListaActividades(gestorActividades.consultarActividadesProfesor(UsuarioVO.getId()));
		        	}
		

		} catch (AdaptadorException e) {
			e.printStackTrace();
		}
		}
		else 
		{
			buscando=false;
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
				e.printStackTrace();
			}
	        return ReglasNavegacion.NAV_IRACTIVIDAD;
	    }
	  

	  public String nuevaAsociarActividad() {
		  	actividadVO1 =null;
	        this.actividadVO = new ActividadVO();  
	        this.actividadVO2 = null; 
	    	try {
	    		cargarlistas ();
				this.listaestructuras=gestorActividades.consultarestructuras();
		    	this.listaelegida=consultarListaEstructuras(this.listaestructuras);
			} catch (AdaptadorException e) {
				e.printStackTrace();
			}
	        return ReglasNavegacion.NAV_IRACTIVIDADCURSO;
	    }
	
	public String buscar() {
		ActividadVO actividadVO = new ActividadVO();
		actividadVO.setNombre(actividad);
		actividadVO.setProfesor(this.UsuarioVO);
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
		return ReglasNavegacion.NAV_IRADMACTIVIDAD;
	}

	public String actualizar() {
		String destino=null;
		ActividadVO actividadVO = this.actividadVO;	
		actividadVO.setEstructuras(this.estructralistaelegida);

	try {	
			gestorActividades.modificarActividad(actividadVO);	
			destino=ReglasNavegacion.NAV_IRADMACTIVIDAD;
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
		return destino;
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
          		destino=ReglasNavegacion.NAV_IRADMACTIVIDAD;

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
	        actividadVO.setEstructuras(this.estructralistaelegida);
			cursoActividadVO.setActividad(actividadVO);
			cursoActividadVO.setCurso(cursoVO);
		try {
			gestorActividades.crearActividadCurso(cursoActividadVO); 
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
			
		} catch (AdaptadorException e1) {
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
			this.listaelegida=consultarListaEstructuras(this.actividadVO.getEstructuras());
			setListaAvancesActividad(gestorActividades.consultarActividadesCursoUsuario(actividadVO));
			 if(this.actividadVO.getJuegoVO().getIdjuego()==1){
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

		return ReglasNavegacion.NAV_IRACTIVIDAD;
	}
		
	
	private List<String> consultarListaEstructuras(List<EstructuraVO> estructuras) {
		List<String> lista=new 	ArrayList<String>();
		if (estructuras!=null){
		   for(int j=0;estructuras.size()>j;j++){
			   lista.add(String.valueOf(estructuras.get(j).getIdEstructura()));
			   }}
		return lista;
	}

	private List<EstructuraVO> obtenerEstructuras() {
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

			return ReglasNavegacion.NAV_IRACTIVIDADCURSO;
		}
	
	public void selectOneMenuListener(ValueChangeEvent event) {

	    Object newValue = event.getNewValue(); 
	    
	   String idjuego= (String) newValue;
	   if (idjuego.equals("1")){
		   actividadVO2 = new ActividadVO();
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

	public List<ActividadxUsuarioVO> getListaAvancesActividad() {
		return listaAvancesActividad;
	}

	public void setListaAvancesActividad(List<ActividadxUsuarioVO> listaAvancesActividad) {
		this.listaAvancesActividad = listaAvancesActividad;
	}

	public UsuarioVO getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(UsuarioVO estudiante) {
		this.estudiante = estudiante;
	}

	public CursoVO getCurso() {
		return curso;
	}

	public void setCurso(CursoVO curso) {
		this.curso = curso;
	}






}
