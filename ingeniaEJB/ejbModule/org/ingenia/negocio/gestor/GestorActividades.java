package org.ingenia.negocio.gestor;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.ingenia.adaptadores.AdaptadorActividad;
import org.ingenia.adaptadores.AdaptadorJuego;
import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.ActividadVO;
import org.ingenia.comunes.vo.CursoActividadVO;
import org.ingenia.comunes.vo.JuegoVO;
import org.ingenia.negocio.entidades.Actividad;
import org.ingenia.negocio.entidades.Actividadcurso;
import org.ingenia.negocio.entidades.Curso;
import org.ingenia.negocio.entidades.Juego;
import org.ingenia.negocio.igestor.IGestorActividadesLocal;
import org.ingenia.negocio.igestor.IGestorActividadesRemote;

/**
 * Session Bean implementation class GestorActividades
 */
@Stateless
@LocalBean
public class GestorActividades implements IGestorActividadesRemote, IGestorActividadesLocal {

	@PersistenceContext(unitName = "ingeniaJPA")
	private EntityManager em;
	
	public GestorActividades() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void crearActividadVO(CursoActividadVO cursoActividadVO) {

		AdaptadorActividad adaptador = null;
		Actividad actividad = new Actividad();
		 Query q = em.createQuery("SELECT count(a) FROM Actividad as a");   
		 cursoActividadVO.getActividad().setIdactividad(((Number) q.getResultList().get(0)).intValue()+1);
     	Juego juego = em.find(Juego.class,cursoActividadVO.getActividad().getId_Juego());
		adaptador = new AdaptadorActividad(cursoActividadVO.getActividad());
           
		try {
			actividad = adaptador.getActividad(); 
			actividad.setJuego(juego);
			Curso curso = em.find(Curso.class,cursoActividadVO.getCurso().getIdcurso());
			Actividadcurso cursoActividad = new Actividadcurso();
			cursoActividad.setActividad(actividad);
			cursoActividad.setCurso(curso);
			cursoActividad.setposicionactividad(cursoActividadVO.getPosicion());
			em.persist(actividad);
			em.persist(cursoActividad);
			

		} catch (AdaptadorException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ActividadVO modificarActividadVO(ActividadVO actividadVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminarActividadVO(ActividadVO actividadVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ActividadVO> consultarActividadDisponibles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActividadVO consultarActividadVO(ActividadVO actividadVO)
			throws AdaptadorException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<JuegoVO> consultarJuegosDisponibles() {

		List<JuegoVO> ListaJuegoVO = new ArrayList<JuegoVO>();;
		JuegoVO JuegoVO=new JuegoVO();
		AdaptadorJuego adaptador = null;
		Query q = em.createQuery("SELECT object(t) FROM Juego AS t");
		List<Juego> listaJuego= q.getResultList();
 
        for (int i=0;listaJuego.size()>i;i++) {
    
            adaptador = new AdaptadorJuego(listaJuego.get(i));
            try {
            	JuegoVO=adaptador.getJuegoVO();
			} catch (AdaptadorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            ListaJuegoVO.add(JuegoVO);
		}
        
        return ListaJuegoVO;
	}


   

}
