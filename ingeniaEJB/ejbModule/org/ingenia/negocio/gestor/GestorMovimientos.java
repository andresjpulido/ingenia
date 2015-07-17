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
import org.ingenia.adaptadores.AdaptadorMovimiento;
import org.ingenia.adaptadores.AdaptadorTipoGato;
import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.ActividadVO;
import org.ingenia.comunes.vo.ArmaVO;
import org.ingenia.comunes.vo.ArmaduraVO;
import org.ingenia.comunes.vo.ColorVO;
import org.ingenia.comunes.vo.GatoVO;
import org.ingenia.comunes.vo.MovimientoVO;
import org.ingenia.comunes.vo.TipoGatoVO;
import org.ingenia.negocio.entidades.Actividad;
import org.ingenia.negocio.entidades.Arma;
import org.ingenia.negocio.entidades.Armadura;
import org.ingenia.negocio.entidades.Color;
import org.ingenia.negocio.entidades.Gato;
import org.ingenia.negocio.entidades.Juego;
import org.ingenia.negocio.entidades.Movimiento;
import org.ingenia.negocio.entidades.Tipogato;
import org.ingenia.negocio.igestor.*;

/**
 * Session Bean implementation class GestorMovimientos
 */
@Stateless
@LocalBean
public class GestorMovimientos implements IGestorMovimientosRemote, IGestorMovimientosLocal {

	@PersistenceContext(unitName = "ingeniaJPA")
	private EntityManager em;
	
	public GestorMovimientos() {
		// TODO Auto-generated constructor stub
	}
		
	@Override
	public MovimientoVO crearMovimiento(MovimientoVO movimientoVO) throws AdaptadorException
	{
		AdaptadorMovimiento adaptador = null;
		Movimiento movimiento = new Movimiento();
		Query q = em.createQuery("SELECT count(e) FROM Movimiento as e");   
		movimientoVO.setIdMovimiento(((Number) q.getResultList().get(0)).intValue()+1);
		adaptador = new AdaptadorMovimiento(movimientoVO);
		
		try {
			movimiento = adaptador.getMovimiento(); 
			em.persist(movimiento);
		} catch (AdaptadorException e) {
			e.printStackTrace();
		}
		
		return movimientoVO;
	}

	@Override
	public void modificarMovimiento(MovimientoVO movimientoVO) throws AdaptadorException
	{
		AdaptadorMovimiento adaptador = null;
		Movimiento movimiento = null;		        
		adaptador = new AdaptadorMovimiento(movimientoVO);
		
		try {
			movimiento = adaptador.getMovimiento();
			em.merge(movimiento);
		} catch (AdaptadorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public MovimientoVO consultarMovimientoVO(int idMovimiento) throws AdaptadorException
	{
		AdaptadorMovimiento adaptador = null;
		Movimiento movimiento = em.find(Movimiento.class,idMovimiento);
		adaptador = new AdaptadorMovimiento(movimiento);
		MovimientoVO movimientoVO = adaptador.getMovimientoVO();
		
		return movimientoVO;
	}

	@Override
	public List<MovimientoVO> consultarMovimientos(ActividadVO actividadVO) throws AdaptadorException
	{
		List<MovimientoVO> ListaMovimientoVO = new ArrayList<MovimientoVO>();
		MovimientoVO movimiento = new MovimientoVO();
		AdaptadorMovimiento adaptador = null;
		Query q = em.createQuery("SELECT object(t) FROM Movimiento AS t where t.actividad = :actividad");
		q.setParameter("actividad", actividadVO);
		List<Movimiento> listaMovimiento = q.getResultList();
 
        for (int i=0;listaMovimiento.size()>i;i++) {
    
            adaptador = new AdaptadorMovimiento(listaMovimiento.get(i));
            try {
            	movimiento = adaptador.getMovimientoVO();
			} catch (AdaptadorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            ListaMovimientoVO.add(movimiento);
		}
        
        return ListaMovimientoVO;
	}
}
