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
import org.ingenia.adaptadores.AdaptadorEstructura;
import org.ingenia.adaptadores.AdaptadorTipoGato;
import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.ActividadVO;
import org.ingenia.comunes.vo.ArmaVO;
import org.ingenia.comunes.vo.ArmaduraVO;
import org.ingenia.comunes.vo.ColorVO;
import org.ingenia.comunes.vo.EstructuraVO;
import org.ingenia.comunes.vo.GatoVO;
import org.ingenia.comunes.vo.TipoGatoVO;
import org.ingenia.negocio.entidades.Actividad;
import org.ingenia.negocio.entidades.Arma;
import org.ingenia.negocio.entidades.Armadura;
import org.ingenia.negocio.entidades.Color;
import org.ingenia.negocio.entidades.Estructura;
import org.ingenia.negocio.entidades.Gato;
import org.ingenia.negocio.entidades.Juego;
import org.ingenia.negocio.entidades.Tipogato;
import org.ingenia.negocio.igestor.*;

/**
 * Session Bean implementation class GestorEstructuras
 */
@Stateless
@LocalBean
public class GestorEstructuras implements IGestorEstructurasRemote, IGestorEstructurasLocal {

	@PersistenceContext(unitName = "ingeniaJPA")
	private EntityManager em;
	
	public GestorEstructuras() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public EstructuraVO crearEstructura(EstructuraVO EstructuraVO) throws AdaptadorException
	{
		AdaptadorEstructura adaptador = null;
		Estructura estructura = new Estructura();
		Query q = em.createQuery("SELECT count(e) FROM Estructura as e");   
		EstructuraVO.setIdEstructura(((Number) q.getResultList().get(0)).intValue()+1);
		adaptador = new AdaptadorEstructura(EstructuraVO);
		
		try {
			estructura = adaptador.getEstructura(); 
			em.persist(estructura);
		} catch (AdaptadorException e) {
			e.printStackTrace();
		}
		return EstructuraVO;
	}

	@Override
	public void modificarEstructura(EstructuraVO EstructuraVO) throws AdaptadorException
	{
		AdaptadorEstructura adaptador = null;
		Estructura estructura = null;		        
		adaptador = new AdaptadorEstructura(EstructuraVO);
		
		try {
			estructura = adaptador.getEstructura();
			estructura.setNombre(EstructuraVO.getNombre());
			em.merge(estructura);
		} catch (AdaptadorException e) {
			e.printStackTrace();
		}
	}

	@Override
	public EstructuraVO consultarEstructuraVO(int idEstructura) throws AdaptadorException
	{
		AdaptadorEstructura adaptador = null;
		Estructura estructura = em.find(Estructura.class,idEstructura);
		adaptador = new AdaptadorEstructura(estructura);
		EstructuraVO estructuraVO = adaptador.getEstructuraVO();
				
		return estructuraVO;
	}

	@Override
	public List<EstructuraVO> consultarEstructuras(ActividadVO actividadVO) throws AdaptadorException
	{
		List<EstructuraVO> ListaEstructuraVO = new ArrayList<EstructuraVO>();
		EstructuraVO estructura = new EstructuraVO();
		AdaptadorEstructura adaptador = null;
		Query q = em.createQuery("SELECT object(t) FROM Estructura AS t where t.actividad = :actividad");
		q.setParameter("actividad", actividadVO);
		List<Estructura> listaEstructura = q.getResultList();
 
        for (int i=0;listaEstructura.size()>i;i++) {
    
            adaptador = new AdaptadorEstructura(listaEstructura.get(i));
            try {
            	estructura = adaptador.getEstructuraVO();
			} catch (AdaptadorException e) {
				e.printStackTrace();
			}
            ListaEstructuraVO.add(estructura);
		}
        
        return ListaEstructuraVO;
	}
		
	public GestorEstructuras(EntityManager em) {
		this.em = em;
	}
}
