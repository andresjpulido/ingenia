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

import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.ActividadVO;
import org.ingenia.comunes.vo.CursoActividadVO;
import org.ingenia.comunes.vo.CursoVO;
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

	private JuegoVO juegoVO=new JuegoVO();
	private String actividad;
	private List<JuegoVO> listaJuegos;
	private List<ActividadVO> listaActividades;
	private int posicion=0;
   
	private final static String NAV_CONFIGURARACTIVIDAD = "configuraractividad";
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
			this.listaJuegos=gestorActividades.consultarJuegosDisponibles();

		} catch (AdaptadorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	  public String configurarActividad() {
		  System.out.println("entrrooooo");
			 actividadVO1 =null;		
	        this.actividadVO = new ActividadVO();   

	        return NAV_CONFIGURARACTIVIDAD;
	    }
	  
	
	  public String nuevaActividad() {
			 actividadVO1 =null;		
	        this.actividadVO = new ActividadVO();   

	        return NAV_IRACTIVIDAD;
	    }
	  

	  public String nuevaAsociarActividad() {
		  	actividadVO1 =null;
	        this.actividadVO = new ActividadVO();  
	        return NAV_IRACTIVIDADCURSO;
	    }
	
	public String buscar() {
		ActividadVO actividadVO = new ActividadVO();
		actividadVO.setEnunciado(this.actividad);

		try {
			setListaActividades(gestorActividades.consultarActividadesProfesor(7890));

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

	try {	
			gestorActividades.modificarActividad(actividadVO);			
		
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

	public String crear() {

  		 ActividadVO actividadVO = this.actividadVO;		
	     actividadVO.setId_juego(juegoVO.getIdjuego());
         UsuarioVO profesorVO = new UsuarioVO();
         profesorVO.setId(7890);
         actividadVO.setProfesor(profesorVO);
		try {
          		gestorActividades.crearActividad(actividadVO); 				

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
		return NAV_IRADMACTIVIDAD;
	}
	
	public void crearAsociando() {

			ActividadVO actividadVO = this.actividadVO;		
	        actividadVO.setId_juego(juegoVO.getIdjuego());
	        int idcurso=Integer.parseInt(recuperarParametro("idcurso"));
			CursoActividadVO cursoActividadVO = new CursoActividadVO();
         	CursoVO cursoVO=new CursoVO();
         	cursoVO.setIdcurso(idcurso);
         	UsuarioVO profesorVO = new UsuarioVO();
			profesorVO.setId(7890);
			actividadVO.setProfesor(profesorVO);
			cursoActividadVO.setActividad(actividadVO);
			cursoActividadVO.setPosicion(this.posicion);
			cursoActividadVO.setCurso(cursoVO);
			
		try {
			gestorActividades.crearActividadCurso(cursoActividadVO); 		

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


	public String irActividad() {

		 actividadVO1 =new ActividadVO();

		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext()
				.getRequestParameterMap();

		String id = params.get("idactividad");
		ActividadVO actividadVO = new ActividadVO();
		actividadVO.setIdactividad(Integer.parseInt(id));
		
		try {
			this.actividadVO = gestorActividades.consultarActividadVO(actividadVO);

			for (JuegoVO juego : listaJuegos) {
					if(juego.getIdjuego()==this.actividadVO.getId_Juego()){
						this.juegoVO=juego;		
					}
				}

		} catch (AdaptadorException e) {
			e.printStackTrace();
		}

		return NAV_IRACTIVIDAD;
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
				this.actividadVO = gestorActividades.consultarActividadVO(actividadVO);

				for (JuegoVO juego : listaJuegos) {
						if(juego.getIdjuego()==this.actividadVO.getId_Juego()){
							this.juegoVO=juego;		
						}
					}

			} catch (AdaptadorException e) {
				e.printStackTrace();
			}

			return NAV_IRACTIVIDADCURSO;
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
			setListaActividades(gestorActividades.consultarActividadesProfesor(7890));
		} catch (AdaptadorException e) {
			e.printStackTrace();
		}
	
		return listaActividades;
	}

	public void setListaActividades(List<ActividadVO> listaActividades) {
		this.listaActividades = listaActividades;
	}


}
