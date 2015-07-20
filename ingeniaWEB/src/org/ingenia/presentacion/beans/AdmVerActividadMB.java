package org.ingenia.presentacion.beans;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
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


@ManagedBean(name = "AdmVerActividadMB")
@SessionScoped
public class AdmVerActividadMB extends BaseMB {
	


	private static final long serialVersionUID = -4578987507032867585L;

	private String UrlJsInicio;
	private UsuarioVO usuarioVO=new UsuarioVO();
	private ActividadVO actividadVO= new ActividadVO();
	
	private JuegoVO juegoVO=new JuegoVO();
	private String actividad;
	private List<JuegoVO> listaJuegos;
	private List<String> listaelegida;
	private List<EstructuraVO> listaestructuras;
	private List<EstructuraVO> estructralistaelegida;
	private List<ActividadVO> listaActividades;
	private int posicion;
	private boolean buscando=false;
		
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
	private final static String NAV_IRADMACTIVIDAD = "iradminactividad";

	@EJB
	IGestorActividadesLocal gestorActividades;
	
	public AdmVerActividadMB(){
	
		
	}
	
	@PostConstruct
	public void init() {
		try {
			//this.setIdActividad(10);
			this.setUrlJsInicio("/ingeniaWEB/pluggins/CatCraft/js/init/loading.js");
			 faceContext=FacesContext.getCurrentInstance();
             httpServletRequest=(HttpServletRequest)faceContext.getExternalContext().getRequest();
		        if(httpServletRequest.getSession().getAttribute("sessionUsuario")!=null)
		        {
		        	this.usuarioVO=(UsuarioVO) httpServletRequest.getSession().getAttribute("sessionUsuario");
		        	//this.actividadVO=(ActividadVO) httpServletRequest.getSession().getAttribute("ActividadActual");
		        	//Prueba antes de redireccion
		        	this.actividadVO = new ActividadVO();
		        	this.actividadVO.setIdactividad(11);
		        	this.actividadVO.setNombre("CatCraft");
		        }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String buscar() {
		ActividadVO actividadVO = new ActividadVO();
		actividadVO.setNombre(actividad);
		actividadVO.setProfesor(this.usuarioVO);
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
         actividadVO.setProfesor(this.usuarioVO);
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
			actividadVO.setProfesor(this.usuarioVO);
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

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public List<ActividadVO> getListaActividades() {
		try {

			if(buscando==false){
			setListaActividades(gestorActividades.consultarActividadesProfesor(usuarioVO.getId()));
			}

		} catch (AdaptadorException e) {
			e.printStackTrace();
		}
		return listaActividades;
	}

	public void setListaActividades(List<ActividadVO> listaActividades) {
		this.listaActividades = listaActividades;
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

	public String getUrlJsInicio() {
		return UrlJsInicio;
	}

	public void setUrlJsInicio(String urlJsInicio) {
		UrlJsInicio = urlJsInicio;
	}

	public UsuarioVO getUsuarioVO() {
		return usuarioVO;
	}

	public void setUsuarioVO(UsuarioVO usuarioVO) {
		this.usuarioVO = usuarioVO;
	}
}
