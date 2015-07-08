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
import org.ingenia.negocio.igestor.IGestorActividadesLocal;
import org.ingenia.presentacion.BaseMB;


@ManagedBean(name = "AdmActividadMB")
@SessionScoped
public class AdmActividadMB extends BaseMB {
	


	private static final long serialVersionUID = -4578987507032867585L;

	private ActividadVO actividadVO= new ActividadVO();
	private JuegoVO juegoVO=new JuegoVO();
	private String actividad;
	private List<JuegoVO> listaJuegos;
	private List<CursoActividadVO> listaCursoActividades;
   
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
	
	  public String nuevaActividad() {
	        String destino = null;
	        this.actividadVO = null;   
	        destino= "nuevaactividad";
	        return destino;
	    }
	
	public String buscar() {
		ActividadVO actividadVO = new ActividadVO();
		actividadVO.setEnunciado(this.actividad);

		try {
			listaCursoActividades= gestorActividades.consultarActividadesProfesor(7890);

		} catch (AdaptadorException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e
							.getMessage()));
			e.printStackTrace();
		}
		return NAV_IRADMACTIVIDAD;
	}

	public String guardar() {
		
		String destino =null;
		try {
			gestorActividades.modificarActividadVO(this.actividadVO);
			destino ="nuevocurso";

		} catch (AdaptadorException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error de concersion de tipos!"));
			e.printStackTrace();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e
							.getMessage()));
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
						"La operacion fue realizada satisfactoriamente !"));
		return destino;
	}

	public void crear() {

		ActividadVO actividadVO = this.actividadVO;		
	       actividadVO.setId_juego(juegoVO.getIdjuego());
		int idcurso=Integer.parseInt(recuperarParametro("idcurso"));
         int posicion=0;
         CursoActividadVO cursoActividadVO = new CursoActividadVO();
         CursoVO cursoVO=new CursoVO();
         cursoVO.setIdcurso(idcurso);
		try {
			if(actividadVO.getIdactividad()==0){

			cursoActividadVO.setActividad(actividadVO);
			cursoActividadVO.setPosicion(posicion);
			cursoActividadVO.setCurso(cursoVO);
			gestorActividades.crearActividadVO(cursoActividadVO); 	}
			
			else{
				gestorActividades.modificarActividadVO(actividadVO);
			}
			

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

	public List<CursoActividadVO> getListaCursoActividades() {
		try {
			listaCursoActividades=gestorActividades.consultarActividadesProfesor(7890);
		} catch (AdaptadorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaCursoActividades;
	}

	public void setListaCursoActividades(List<CursoActividadVO> listaActividades) {
		this.listaCursoActividades = listaActividades;
	}
	
	/*public String dogetTiposActividad(){

	/*	try {
			this.listaTiposActividad=gestorActividades.consultarTiposActividadDisponibles();
		} catch (AdaptadorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		/*return NAV_IRACTIVIDAD;
	}*/

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


}
