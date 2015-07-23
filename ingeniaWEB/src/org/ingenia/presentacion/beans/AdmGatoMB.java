package org.ingenia.presentacion.beans;


import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.ActividadVO;
import org.ingenia.comunes.vo.ArmaVO;
import org.ingenia.comunes.vo.ArmaduraVO;
import org.ingenia.comunes.vo.ColorVO;
import org.ingenia.comunes.vo.GatoVO;
import org.ingenia.comunes.vo.JuegoVO;
import org.ingenia.comunes.vo.TipoGatoVO;
import org.ingenia.negocio.igestor.IGestorGatosLocal;
import org.ingenia.presentacion.BaseMB;


@ManagedBean(name = "AdmGatoMB")
@SessionScoped
public class AdmGatoMB extends BaseMB {
	


	private static final long serialVersionUID = -4578987507032867585L;

	private GatoVO gatoVO=new GatoVO();
	private GatoVO gatoVO1=null;
	private GatoVO gatoVO2=null;
	private List<TipoGatoVO> listaTiposGato;
	private List<ColorVO> listaColores;
	private List<ArmaVO> listaArmas;
	private List<GatoVO> listaGatos;
	private List<ArmaduraVO> listaArmaduras;
	private ArmaVO arma = new ArmaVO();
	private ArmaduraVO armadura= new ArmaduraVO();
	private ColorVO color = new ColorVO();
	private TipoGatoVO tipogato= new TipoGatoVO();
	private int idactividad;	
	private final static String NAV_IRACTIVIDAD = "iractividad";
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
			this.setListaArmaduras(gestorGatos.consultarArmaduras());

			ActividadVO actividadVO = new ActividadVO();
			 actividadVO.setIdactividad(1);
		
		} catch (AdaptadorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	public String actualizar() {
		this.setGatoVO2(null);
		String destino=null;
		 if((arma!=null)&&(armadura!=null)&&(color!=null)&&(tipogato!=null)){
				GatoVO gatoVO = this.gatoVO1;
				gatoVO.setArma(arma);
				 gatoVO.setArmadura(armadura);
				 gatoVO.setColor(color);
				 gatoVO.setTipogato(tipogato);
				ActividadVO actividadVO = new ActividadVO();
				 int idactividad=Integer.parseInt(recuperarParametro("idactividad"));
				 actividadVO.setIdactividad(idactividad);
	try {	
		
		gestorGatos.modificarGato(gatoVO,actividadVO);		
		actualizaGatos();
		destino=NAV_CONFIGURARACTIVIDAD;
	    this.gatoVO=new GatoVO();
		 arma = new ArmaVO();
			 armadura= new ArmaduraVO();
			  color = new ColorVO();
			 tipogato= new TipoGatoVO();
			 this.gatoVO1=null;
		
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
		 else{
			 FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar todos los datos del gato", null));
	
		 }
		 return destino;
	}
	
	
	  public String configurarActividad() {
		  this.setGatoVO2(null);
		  this.gatoVO1=null;
		  System.out.println("entrrooooo al gato");
		  this.gatoVO=new GatoVO();
		 arma = new ArmaVO();
			 armadura= new ArmaduraVO();
			  color = new ColorVO();
			 tipogato= new TipoGatoVO();
		  idactividad=Integer.parseInt(recuperarParametro("idact"));
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
	  
	  public String actualizaGatos() {

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
			
	        return NAV_CONFIGURARACTIVIDAD;
	    }
	  
	  public String cancelar() {
	
		  this.gatoVO1=null;
		  this.gatoVO2=null;
		  this.gatoVO=new GatoVO();
			 arma = new ArmaVO();
				 armadura= new ArmaduraVO();
				  color = new ColorVO();
				 tipogato= new TipoGatoVO();
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
			this.gatoVO=new GatoVO();
	        return NAV_CONFIGURARACTIVIDAD;
	    }
	  

	public String crear() {
		
		this.setGatoVO2(null);
   String destino=null;
		 idactividad=Integer.parseInt(recuperarParametro("idactividad"));
		 if((arma!=null)&&(armadura!=null)&&(color!=null)&&(tipogato!=null)){
		GatoVO gatoVO = this.gatoVO;			
		ActividadVO actividadVO = new ActividadVO();
		 int idactividad=Integer.parseInt(recuperarParametro("idactividad"));
		 actividadVO.setIdactividad(idactividad);
		 gatoVO.setArma(arma);
		 gatoVO.setArmadura(armadura);
		 gatoVO.setColor(color);
		 gatoVO.setTipogato(tipogato);
		try {
			gestorGatos.crearGato(gatoVO,actividadVO); 				
			actualizaGatos();
			destino= NAV_CONFIGURARACTIVIDAD;
			 FacesContext context = FacesContext.getCurrentInstance();
	         
		        context.addMessage(null, new FacesMessage("Successful",  "Your message: " ) );
		        context.addMessage(null, new FacesMessage("Second Message", "Additional Message Detail"));
		        this.gatoVO=new GatoVO();
				 arma = new ArmaVO();
					 armadura= new ArmaduraVO();
					  color = new ColorVO();
					 tipogato= new TipoGatoVO();

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
		
		this.gatoVO=new GatoVO();}
		 else{
			 FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar todos los datos del gato", null));
	
		 }
		return destino;
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
	
	public String modificarGato() {

  String destino= null;
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext()
				.getRequestParameterMap();
		String id = params.get("idgatomod");
		GatoVO gatoVO = new GatoVO();
		gatoVO.setIdgato(Integer.parseInt(id));

		System.out.println(gatoVO.getIdgato());
		try {
			
			this.gatoVO1 = gestorGatos.consultarGatoVO(gatoVO);
			this.setArma(this.gatoVO1.getArma());
			this.setArmadura(this.gatoVO1.getArmadura());
			this.setColor(this.gatoVO1.getColor());
			this.setTipogato(this.gatoVO1.getTipogato());
			destino=NAV_CONFIGURARACTIVIDAD;

			/*for (JuegoVO juego : listaJuegos) {
					if(juego.getIdjuego()==this.actividadVO.getId_Juego()){
						this.juegoVO=juego;		
					}
				}*/ //propiedades gato

		} catch (AdaptadorException e) {
			e.printStackTrace();
		}


		return destino;
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

	public GatoVO getGatoVO1() {
		return gatoVO1;
	}

	public void setGatoVO1(GatoVO gatoVO1) {
		this.gatoVO1 = gatoVO1;
	}

	/**
	 * @return the listaArmaduras
	 */
	public List<ArmaduraVO> getListaArmaduras() {
		return listaArmaduras;
	}

	/**
	 * @param listaArmaduras the listaArmaduras to set
	 */
	public void setListaArmaduras(List<ArmaduraVO> listaArmaduras) {
		this.listaArmaduras = listaArmaduras;
	}

	/**
	 * @return the arma
	 */
	public ArmaVO getArma() {
		return arma;
	}

	/**
	 * @param arma the arma to set
	 */
	public void setArma(ArmaVO arma) {
		this.arma = arma;
	}

	/**
	 * @return the armadura
	 */
	public ArmaduraVO getArmadura() {
		return armadura;
	}

	/**
	 * @param armadura the armadura to set
	 */
	public void setArmadura(ArmaduraVO armadura) {
		this.armadura = armadura;
	}

	/**
	 * @return the color
	 */
	public ColorVO getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(ColorVO color) {
		this.color = color;
	}

	/**
	 * @return the tipogato
	 */
	public TipoGatoVO getTipogato() {
		return tipogato;
	}

	/**
	 * @param tipogato the tipogato to set
	 */
	public void setTipogato(TipoGatoVO tipogato) {
		this.tipogato = tipogato;
	}

	public ColorVO getColorporID(int number) {
		// TODO Auto-generated method stub
		ColorVO color = new ColorVO();

	    for(int i=0;this.listaColores.size()>i;i++){
	    	if(listaColores.get(i).getIdcolor()==number){
	    		color=listaColores.get(i);
	    	}
	    }
		return color;
	}
	
	public TipoGatoVO getTipoporID(int number) {
		// TODO Auto-generated method stub
		TipoGatoVO tipo = new TipoGatoVO();

	    for(int i=0;this.listaTiposGato.size()>i;i++){
	    	if(listaTiposGato.get(i).getIdTipoGato()==number){
	    		tipo=listaTiposGato.get(i);
	    	}
	    }
		return tipo;
	}
	
	public ArmaVO getArmaporID(int number) {
		// TODO Auto-generated method stub
		ArmaVO arma = new ArmaVO();

	    for(int i=0;this.listaArmas.size()>i;i++){
	    	if(listaArmas.get(i).getIdarma()==number){
	    		arma=listaArmas.get(i);
	    	}
	    }
		return arma;
	}

	public ArmaduraVO getArmaduraporID(int number) {
		// TODO Auto-generated method stub
		ArmaduraVO armadura = new ArmaduraVO();

	    for(int i=0;this.listaArmaduras.size()>i;i++){
	    	if(listaArmaduras.get(i).getIdarmadura()==number){
	    		armadura=listaArmaduras.get(i);
	    	}
	    }
		return armadura;
	}
	
	public void selectOneMenuColorListener(ValueChangeEvent event) {
	
				
	    Object newValue = event.getNewValue(); 
	    ColorVO c= (ColorVO)newValue;
	   this.color=c;
	   FacesContext.getCurrentInstance().renderResponse();

	}
	
	public void selectOneMenuTipoListener(ValueChangeEvent event) {
		
	    Object newValue = event.getNewValue(); 
	    TipoGatoVO t= (TipoGatoVO)newValue;
	   this.tipogato=t;
	   FacesContext.getCurrentInstance().renderResponse();

	}
	
	public void selectOneMenuArmaListener(ValueChangeEvent event) {
		
	    Object newValue = event.getNewValue(); 
	    ArmaVO a= (ArmaVO)newValue;
	   this.arma=a;
	   FacesContext.getCurrentInstance().renderResponse();
      
	}
	
	public void selectOneMenuArmaduraListener(ValueChangeEvent event) {

	    Object newValue = event.getNewValue(); 
	    ArmaduraVO d= (ArmaduraVO)newValue;
	   this.armadura=d;
	   FacesContext.getCurrentInstance().renderResponse();

	}

	public GatoVO getGatoVO2() {
		return gatoVO2;
	}

	public void setGatoVO2(GatoVO gatoVO2) {
		this.gatoVO2 = gatoVO2;
	}



}
