package org.ingenia.presentacion.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.ActividadVO;
import org.ingenia.comunes.vo.ArmaVO;
import org.ingenia.comunes.vo.ColorVO;
import org.ingenia.comunes.vo.CursoActividadVO;
import org.ingenia.comunes.vo.CursoVO;
import org.ingenia.comunes.vo.GatoVO;
import org.ingenia.comunes.vo.JuegoVO;
import org.ingenia.comunes.vo.TipoGatoVO;
import org.ingenia.comunes.vo.UsuarioVO;
import org.ingenia.negocio.igestor.IGestorActividadesLocal;
import org.ingenia.negocio.igestor.IGestorGatosLocal;
import org.ingenia.presentacion.BaseMB;


@ManagedBean(name = "AdmGatoMB")
@SessionScoped
public class AdmGatoMB extends BaseMB {
	


	private static final long serialVersionUID = -4578987507032867585L;

	private GatoVO gatoVO=new GatoVO();
	private List<TipoGatoVO> listaTiposGato;
	private List<ColorVO> listaColores;
	private List<ArmaVO> listaArmas;
	private List<GatoVO> listaGatos;
	private int idactividad;

	private final static String NAV_IRACTIVIDAD = "iractividad";
	private final static String NAV_IRADMACTIVIDAD = "iradminactividad";
	private final static String NAV_CONFIGURARACTIVIDAD = "configuraractividad";

	@EJB
	IGestorGatosLocal gestorGatos;

	
	public AdmGatoMB(){
	
		
	}
	
	@PostConstruct
	public void init() {
		try {
			System.out.println("por acaaaaaa");
			this.setListaTiposGato(gestorGatos.consultarTiposGato());
			this.setListaColores(gestorGatos.consultarColores());
			this.setListaArmas(gestorGatos.consultarArmas());
		


		} catch (AdaptadorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public void actualizar() {
		
		GatoVO gatoVO = this.gatoVO;		

	try {	
		gestorGatos.modificarGato(gatoVO);			
		
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
	
	
	  public String configurarActividad() {
		  System.out.println("entrrooooo al gato");

		  idactividad=Integer.parseInt(recuperarParametro("idactividad"));
			 System.out.println(idactividad);
			ActividadVO actividadVO = new ActividadVO();
			 actividadVO.setIdactividad(idactividad);
			try {
				this.listaGatos= gestorGatos.consultarGatos(actividadVO);
			} catch (AdaptadorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("lista"+listaGatos.size());
			
	        return NAV_CONFIGURARACTIVIDAD;
	    }
	  

	public String crear() {

		 idactividad=Integer.parseInt(recuperarParametro("idactividad"));
		 
		GatoVO gatoVO = this.gatoVO;			
		ActividadVO actividadVO = new ActividadVO();
		 int idactividad=Integer.parseInt(recuperarParametro("idactividad"));
		 actividadVO.setIdactividad(idactividad);
		try {
			gestorGatos.crearGato(gatoVO,actividadVO); 				
			configurarActividad();
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
		return NAV_CONFIGURARACTIVIDAD;
	}


	public String irGato() {


		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext()
				.getRequestParameterMap();

		String id = params.get("idgato");
		GatoVO gatoVO = new GatoVO();
		gatoVO.setIdgato(Integer.parseInt(id));
		
		try {
			this.gatoVO = gestorGatos.consultarGatoVO(gatoVO);

			/*for (JuegoVO juego : listaJuegos) {
					if(juego.getIdjuego()==this.actividadVO.getId_Juego()){
						this.juegoVO=juego;		
					}
				}*/ //propiedades gato

		} catch (AdaptadorException e) {
			e.printStackTrace();
		}

		return NAV_IRACTIVIDAD;
	}

	public List<TipoGatoVO> getListaTiposGato() {
		return listaTiposGato;
	}

	public void setListaTiposGato(List<TipoGatoVO> listaTiposGato) {
		this.listaTiposGato = listaTiposGato;
	}

	public List<ColorVO> getListaColores() {
		return listaColores;
	}

	public void setListaColores(List<ColorVO> listaColores) {
		this.listaColores = listaColores;
	}

	public List<ArmaVO> getListaArmas() {
		return listaArmas;
	}

	public void setListaArmas(List<ArmaVO> listaArmas) {
		this.listaArmas = listaArmas;
	}
		
	public GatoVO getGatoVO() {
		return gatoVO;
	}

	public void setGatoVO(GatoVO gatoVO) {
		this.gatoVO = gatoVO;
	}

	public List<GatoVO> getListaGatos() {
	
       
				return listaGatos;
	}

	public void setListaGatos(List<GatoVO> listaGatos) {
		this.listaGatos = listaGatos;
	}

	public int getIdactividad() {
		return idactividad;
	}

	public void setIdactividad(int idactividad) {
		this.idactividad = idactividad;
	}



}
