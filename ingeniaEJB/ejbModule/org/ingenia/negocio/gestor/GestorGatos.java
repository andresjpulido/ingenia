package org.ingenia.negocio.gestor;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.ingenia.adaptadores.AdaptadorArma;
import org.ingenia.adaptadores.AdaptadorArmadura;
import org.ingenia.adaptadores.AdaptadorColor;
import org.ingenia.adaptadores.AdaptadorGato;
import org.ingenia.adaptadores.AdaptadorTipoGato;
import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.ActividadVO;
import org.ingenia.comunes.vo.ArmaVO;
import org.ingenia.comunes.vo.ArmaduraVO;
import org.ingenia.comunes.vo.ColorVO;
import org.ingenia.comunes.vo.GatoVO;
import org.ingenia.comunes.vo.TipoGatoVO;
import org.ingenia.negocio.entidades.Actividad;
import org.ingenia.negocio.entidades.Arma;
import org.ingenia.negocio.entidades.Armadura;
import org.ingenia.negocio.entidades.Color;
import org.ingenia.negocio.entidades.Gato;
import org.ingenia.negocio.entidades.Tipogato;
import org.ingenia.negocio.igestor.IGestorGatosLocal;
import org.ingenia.negocio.igestor.IGestorGatosRemote;

/**
 * Session Bean implementation class GestorActividades
 */
@Stateless
@LocalBean
public class GestorGatos implements IGestorGatosRemote, IGestorGatosLocal {

	@PersistenceContext(unitName = "ingeniaJPA")
	private EntityManager em;
	
	public GestorGatos() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<TipoGatoVO> consultarTiposGato() throws AdaptadorException {

		List<TipoGatoVO> ListaTiposGatoVO = new ArrayList<TipoGatoVO>();;
		TipoGatoVO tipoGatoVO=new TipoGatoVO();
		AdaptadorTipoGato adaptador = null;
		Query q = em.createQuery("SELECT object(t) FROM Tipogato AS t");
		List<Tipogato> listaTipoGato= q.getResultList();
 
        for (int i=0;listaTipoGato.size()>i;i++) {
    
            adaptador = new AdaptadorTipoGato(listaTipoGato.get(i));
            try {
            	tipoGatoVO=adaptador.getTipogatoVO();
			} catch (AdaptadorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            ListaTiposGatoVO.add(tipoGatoVO);
		}
        
        return ListaTiposGatoVO;
	}

	@Override
	public List<ColorVO> consultarColores() throws AdaptadorException {
		List<ColorVO> ListaColorVO = new ArrayList<ColorVO>();;
		ColorVO colorVO=new ColorVO();
		AdaptadorColor adaptador = null;
		Query q = em.createQuery("SELECT object(c) FROM Color AS c");
		List<Color> listaColor= q.getResultList();
 
        for (int i=0;listaColor.size()>i;i++) {
    
            adaptador = new AdaptadorColor(listaColor.get(i));
            try {
            	colorVO=adaptador.getColorVO();
			} catch (AdaptadorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            ListaColorVO.add(colorVO);
		}
        
        return ListaColorVO;
	}

	@Override
	public List<ArmaVO> consultarArmas() throws AdaptadorException {
		List<ArmaVO> ListaArmaVO = new ArrayList<ArmaVO>();;
		ArmaVO armaVO=new ArmaVO();
		AdaptadorArma adaptador = null;
		Query q = em.createQuery("SELECT object(a) FROM Arma AS a");
		List<Arma> listaArma= q.getResultList();
 
        for (int i=0;listaArma.size()>i;i++) {
    
            adaptador = new AdaptadorArma(listaArma.get(i));
            try {
            	armaVO=adaptador.getArmaVO();
			} catch (AdaptadorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            ListaArmaVO.add(armaVO);
		}
        
        return ListaArmaVO;
	}

	@Override
	public void modificarGato(GatoVO gatoVO,ActividadVO actividadVO) throws AdaptadorException {
		AdaptadorGato adaptador = null;
		Gato gato = null;		        
		adaptador = new AdaptadorGato(gatoVO);
		Actividad actividad = em.find(Actividad.class,actividadVO.getIdactividad());
		Tipogato tipoGato = em.find(Tipogato.class,gatoVO.getTipogato().getIdTipoGato());
		Color color = em.find(Color.class,gatoVO.getColor().getIdcolor());
		Arma arma = em.find(Arma.class,gatoVO.getArma().getIdarma());
		Armadura armadura=em.find(Armadura.class,gatoVO.getArmadura().getIdarmadura());
		try {
			gato =adaptador.getGato();
			gato.setActividad(actividad);
			gato.setArma(arma);
			gato.setColor(color);
			gato.setTipogato(tipoGato);
			gato.setArmadura(armadura);
			gato.setOrden(gatoVO.getOrden());
			 em.merge(gato);
		} catch (AdaptadorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void crearGato(GatoVO gatoVO,ActividadVO actividadVO) throws AdaptadorException {
		AdaptadorGato adaptador = null;
		Gato gato = new Gato();
		 Query q = em.createQuery("SELECT count(g) FROM Gato as g");   
		 gatoVO.setIdgato(((Number) q.getResultList().get(0)).intValue()+1);
		adaptador = new AdaptadorGato(gatoVO);
		Actividad actividad = em.find(Actividad.class,actividadVO.getIdactividad());
		Tipogato tipoGato = em.find(Tipogato.class,gatoVO.getTipogato().getIdTipoGato());
		Color color = em.find(Color.class,gatoVO.getColor().getIdcolor());
		Arma arma = em.find(Arma.class,gatoVO.getArma().getIdarma());
		Armadura armadura=em.find(Armadura.class,gatoVO.getArmadura().getIdarmadura());
		try {
			gato = adaptador.getGato(); 
			gato.setActividad(actividad);
			gato.setArma(arma);
			gato.setColor(color);
			gato.setTipogato(tipoGato);
			gato.setArmadura(armadura);
			gato.setOrden(consultarPosicionDisponible(actividad));
			em.persist(gato);			
  
		} catch (AdaptadorException e) {
			e.printStackTrace();
		}
		
	}
	

	public int consultarPosicionDisponible(Actividad Actividad) throws AdaptadorException {

		int orden =0;
		Query q = em.createQuery("SELECT object(g) FROM Gato AS g where g.actividad = :actividad");
		q.setParameter("actividad", Actividad);
		List<Gato> listagatos= q.getResultList();
		System.out.println("lista gaticos"+listagatos.size());
		if(listagatos.size()>0){
			
			//int[] posiciones= new int[listagatos.size()];
			//int j=0;
			orden=listagatos.size()+1;
			/*  for (int i=0;listagatos.size()>i;i++) {
        	posiciones [i]=listagatos.get(i).getOrden();  
        	System.out.println("posicion"+posiciones[j]);
        	j++;
		}
		Arrays.sort(posiciones);*/
		//System.out.println("posicion"+posiciones[j]);
        }
		else{
			orden=1;
		}
		
        
        return orden;
	}

	@Override
	public GatoVO consultarGatoVO(GatoVO gatoVO) throws AdaptadorException {

		AdaptadorGato adaptador = null;
		AdaptadorArma adap_arma=null;
		AdaptadorArmadura adap_armadura=null;
		AdaptadorColor adap_color=null;
		AdaptadorTipoGato adap_tipo=null;
		Gato gato = em.find(Gato.class,gatoVO.getIdgato());
		Tipogato Tipogato = em.find(Tipogato.class, gato.getTipogato().getIdtipogato());
		Arma Arma = em.find(Arma.class, gato.getArma().getIdarma());
		Color Color = em.find(Color.class, gato.getColor().getIdcolor());
		Armadura Armadura = em.find(Armadura.class, gato.getArmadura().getIdarmadura());
		adap_arma=new AdaptadorArma(Arma);
		adap_armadura = new AdaptadorArmadura(Armadura);
		adap_color = new AdaptadorColor(Color);
		adap_tipo = new AdaptadorTipoGato(Tipogato);
		adaptador = new AdaptadorGato(gato);
		gatoVO =adaptador.getGatoVO();
		gatoVO.setArma(adap_arma.getArmaVO());
		gatoVO.setArmadura(adap_armadura.getArmaduraVO());
		gatoVO.setColor(adap_color.getColorVO());
		gatoVO.setTipogato(adap_tipo.getTipogatoVO());
				
	return gatoVO;
	}

	@Override
	public List<GatoVO> consultarGatos(ActividadVO actividadVO) throws AdaptadorException {
		Actividad Actividad = em.find(Actividad.class,actividadVO.getIdactividad());	
		Query q = em.createQuery("SELECT object(g) FROM Gato AS g where g.actividad = :actividad");
		q.setParameter("actividad", Actividad);
		List<Gato> listagatos= q.getResultList();
		List<GatoVO> listagatosVO= new ArrayList<GatoVO>();
		GatoVO gatoVO= new GatoVO();
		AdaptadorGato adaptador;
		AdaptadorArma adap_arma=null;
		AdaptadorArmadura adap_armadura=null;
		AdaptadorColor adap_color=null;
		AdaptadorTipoGato adap_tipo=null;

	
		for (int i=0;listagatos.size()>i;i++) {
		           adaptador = new AdaptadorGato(listagatos.get(i));
		   		Tipogato Tipogato = em.find(Tipogato.class, listagatos.get(i).getTipogato().getIdtipogato());
				Arma Arma = em.find(Arma.class, listagatos.get(i).getArma().getIdarma());
				Color Color = em.find(Color.class, listagatos.get(i).getColor().getIdcolor());
				Armadura Armadura = em.find(Armadura.class, listagatos.get(i).getArmadura().getIdarmadura());
				adap_arma=new AdaptadorArma(Arma);
				adap_armadura = new AdaptadorArmadura(Armadura);
				adap_color = new AdaptadorColor(Color);
				adap_tipo = new AdaptadorTipoGato(Tipogato);
		      
	            try {
	            	gatoVO=adaptador.getGatoVO();
	             	gatoVO.setArma(adap_arma.getArmaVO());
					gatoVO.setArmadura(adap_armadura.getArmaduraVO());
					gatoVO.setColor(adap_color.getColorVO());
					gatoVO.setTipogato(adap_tipo.getTipogatoVO());
	            	
				} catch (AdaptadorException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            listagatosVO.add(gatoVO);
			}

		return listagatosVO;
	}

	@Override
	public List<ArmaduraVO> consultarArmaduras() throws AdaptadorException {
		List<ArmaduraVO> ListaArmaduraVO = new ArrayList<ArmaduraVO>();;
		ArmaduraVO armaduraVO=new ArmaduraVO();
		AdaptadorArmadura adaptador = null;
		Query q = em.createQuery("SELECT object(a) FROM Armadura AS a");
		List<Armadura> listaArmadura= q.getResultList();
 
        for (int i=0;listaArmadura.size()>i;i++) {
    
            adaptador = new AdaptadorArmadura(listaArmadura.get(i));
            try {
            	armaduraVO=adaptador.getArmaduraVO();
			} catch (AdaptadorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            ListaArmaduraVO.add(armaduraVO);
		}
        
        return ListaArmaduraVO;
	}

	
   

}
