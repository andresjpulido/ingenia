package org.ingenia.negocio.gestor;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.ingenia.adaptadores.AdaptadorActividad;
import org.ingenia.adaptadores.AdaptadorEstructura;
import org.ingenia.adaptadores.AdaptadorJuego;
import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.ActividadVO;
import org.ingenia.comunes.vo.CursoActividadVO;
import org.ingenia.comunes.vo.CursoVO;
import org.ingenia.comunes.vo.EstructuraVO;
import org.ingenia.comunes.vo.JuegoVO;
import org.ingenia.negocio.entidades.Actividad;
import org.ingenia.negocio.entidades.Actividadcurso;
import org.ingenia.negocio.entidades.Curso;
import org.ingenia.negocio.entidades.Estructura;
import org.ingenia.negocio.entidades.EstructurasActiva;
import org.ingenia.negocio.entidades.Juego;
import org.ingenia.negocio.entidades.Usuario;
import org.ingenia.negocio.igestor.IGestorActividadesLocal;
import org.ingenia.negocio.igestor.IGestorActividadesRemote;

/**
 * Session Bean implementation class GestorActividades
 */
@Stateless
@LocalBean
public class GestorActividades implements IGestorActividadesRemote,
		IGestorActividadesLocal {

	@PersistenceContext(unitName = "ingeniaJPA")
	private EntityManager em;

	public GestorActividades() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void crearActividadCurso(CursoActividadVO cursoActividadVO) {

		AdaptadorActividad adaptador = null;
		Actividad actividad = new Actividad();
		Query q = em.createQuery("SELECT count(a) FROM Actividad as a");
		cursoActividadVO.getActividad().setIdactividad(
				((Number) q.getResultList().get(0)).intValue() + 1);
		Juego juego = em.find(Juego.class, cursoActividadVO.getActividad().getJuegoVO().getIdjuego());
		adaptador = new AdaptadorActividad(cursoActividadVO.getActividad());

		try {
			actividad = adaptador.getActividad();
			actividad.setJuego(juego);
			Curso curso = em.find(Curso.class, cursoActividadVO.getCurso()
					.getIdcurso());
			Query q2 = em.createQuery("SELECT ac.actividad FROM Actividadcurso as ac where ac.curso=:curso");
			q2.setParameter("curso", curso);
			List<Actividad> listaActividad = q2.getResultList();			
			Actividadcurso cursoActividad = new Actividadcurso();
			cursoActividad.setActividad(actividad);
			cursoActividad.setCurso(curso);
			cursoActividad.setPosicionActividad((listaActividad.size()+1));
			em.persist(actividad);
			em.persist(cursoActividad);
			for(int i=0;cursoActividadVO.getActividad().getEstructuras().size()>i;i++){
	    		Estructura estructura = em.find(Estructura.class, cursoActividadVO.getActividad().getEstructuras().get(i).getIdEstructura());
	    		EstructurasActiva estructurasActiva=new EstructurasActiva();
	    		estructurasActiva.setActividad(actividad);
	    		estructurasActiva.setEstructura(estructura);
	    		em.persist(estructurasActiva);
	    	}

		} catch (AdaptadorException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void modificarActividadVO(ActividadVO actividadVO) {

		AdaptadorActividad adaptador = null;
		Actividad actividad = null;
		adaptador = new AdaptadorActividad(actividadVO);
		try {
			actividad = adaptador.getActividad();
			Juego juego = em.find(Juego.class, actividadVO.getJuegoVO().getIdjuego());
			actividad.setJuego(juego);
			em.merge(actividad);
		} catch (AdaptadorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
		List<EstructuraVO> lista = new ArrayList<EstructuraVO>();
		AdaptadorActividad adaptador = null;
		Actividad actividad = em.find(Actividad.class,
				actividadVO.getIdactividad());

		adaptador = new AdaptadorActividad(actividad);
		actividadVO = adaptador.getActividadVO();
		AdaptadorJuego adap =new AdaptadorJuego(actividad.getJuego());
    	actividadVO.setJuegoVO(adap.getJuegoVO());
    	AdaptadorEstructura adaptadorest= null;
    	System.out.println("estruc "+actividad.getEstructuras().size());
    	 
    	for(int i=0;actividad.getEstructuras().size()>i;i++){
    		System.out.println("id estrucura "+actividad.getEstructuras().get(i).getIdestructura());
    		adaptadorest=new AdaptadorEstructura(actividad.getEstructuras().get(i));
    		
    		lista.add(adaptadorest.getEstructuraVO());
    	}
    	actividadVO.setEstructuras(lista);
		return actividadVO;
	}

	@Override
	public List<JuegoVO> consultarJuegosDisponibles() {

		List<JuegoVO> ListaJuegoVO = new ArrayList<JuegoVO>();
		
		JuegoVO JuegoVO = new JuegoVO();
		AdaptadorJuego adaptador = null;
		Query q = em.createQuery("SELECT object(t) FROM Juego AS t");
		List<Juego> listaJuego = q.getResultList();

		for (int i = 0; listaJuego.size() > i; i++) {

			adaptador = new AdaptadorJuego(listaJuego.get(i));
			try {
				JuegoVO = adaptador.getJuegoVO();
			} catch (AdaptadorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ListaJuegoVO.add(JuegoVO);
		}

		return ListaJuegoVO;
	}

	@Override
	public List<ActividadVO> consultarActividadesProfesor(int idprofesor) {

		List<ActividadVO> ListaActividadesVO = new ArrayList<ActividadVO>();
		List<Actividad> ListaActividades = new ArrayList<Actividad>();
		ActividadVO actividadVO = new ActividadVO();
		AdaptadorActividad adaptador;
		Usuario profesor = em.find(Usuario.class, idprofesor);
		Query q = em
				.createQuery("SELECT a FROM Actividad as a where a.usuario=:profesor");
		q.setParameter("profesor", profesor);
		ListaActividades = q.getResultList();

		for (Actividad actividad : ListaActividades) {
			adaptador = new AdaptadorActividad(actividad);
			Juego juego = em.find(Juego.class, actividad.getJuego()
					.getIdjuego());
			try {
				actividadVO = adaptador.getActividadVO();
				AdaptadorJuego adap =new AdaptadorJuego(juego);
		    	actividadVO.setJuegoVO(adap.getJuegoVO());
			} catch (AdaptadorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ListaActividadesVO.add(actividadVO);

		}
		return ListaActividadesVO;
	}

	@Override
	public void modificarActividadCurso(CursoActividadVO cursoActividadVO)
			throws AdaptadorException {

		Actividadcurso actividadCurso = new Actividadcurso();
		Actividad actividad = em.find(Actividad.class, cursoActividadVO
				.getActividad().getIdactividad());
		Curso curso = em.find(Curso.class, cursoActividadVO.getCurso()
				.getIdcurso());
		Query q = em
				.createQuery("SELECT ac FROM Actividadcurso as ac where ac.actividad=:actividad and ac.curso=:curso");
		q.setParameter("actividad", actividad);
		q.setParameter("curso", curso);
		List<Actividadcurso> listaCurso = q.getResultList();

		for (Actividadcurso resultado : listaCurso) {
			actividadCurso = resultado;
			actividadCurso.setPosicionActividad(cursoActividadVO.getPosicion());
			em.merge(actividadCurso);
		}

	}

	@Override
	public void crearActividad(ActividadVO actividadVO)
			throws AdaptadorException {
		AdaptadorActividad adaptador = null;
		Actividad actividad = new Actividad();
		Query q = em.createQuery("SELECT count(a) FROM Actividad as a");
		actividadVO.setIdactividad(((Number) q.getResultList().get(0))
				.intValue() + 1);
		Juego juego = em.find(Juego.class, actividadVO.getJuegoVO().getIdjuego());
		adaptador = new AdaptadorActividad(actividadVO);

		try {
			actividad = adaptador.getActividad();
			actividad.setJuego(juego);
			em.persist(actividad);
			for(int i=0;actividadVO.getEstructuras().size()>i;i++){
	    		Estructura estructura = em.find(Estructura.class, actividadVO.getEstructuras().get(i).getIdEstructura());
	    		EstructurasActiva estructurasActiva=new EstructurasActiva();
	    		estructurasActiva.setActividad(actividad);
	    		estructurasActiva.setEstructura(estructura);
	    		em.persist(estructurasActiva);
	    	}

		} catch (AdaptadorException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void modificarActividad(ActividadVO actividadVO)
			throws AdaptadorException {

		AdaptadorActividad adaptador = new AdaptadorActividad(actividadVO);
		Actividad actividad = adaptador.getActividad();
		Juego juego = em.find(Juego.class, actividadVO.getJuegoVO().getIdjuego());
		actividad.setJuego(juego);  
		em.merge(actividad);
		//hay que hacer un delete de estructurasActiva con respecto a actividad
		/*for(int i=0;actividadVO.getEstructuras().size()>i;i++){
    		Estructura estructura = em.find(Estructura.class, actividadVO.getEstructuras().get(i).getIdEstructura());
    		EstructurasActiva estructurasActiva=new EstructurasActiva();
    		estructurasActiva.setActividad(actividad);
    		estructurasActiva.setEstructura(estructura);
    		em.persist(estructurasActiva);
    	}*/

	}

	public ActividadVO consultarActividad(ActividadVO actividadVO)
			throws AdaptadorException {
		return null;
	}

	@Override
	public int consultarPosicion(CursoVO cursoVO, ActividadVO actividadVO)
			throws AdaptadorException {
		int posicion=0;
		Actividadcurso actividadCurso=new Actividadcurso();
		Actividad actividad = em.find(Actividad.class, actividadVO.getIdactividad());
		Curso curso = em.find(Curso.class, cursoVO.getIdcurso());
		Query q = em.createQuery("SELECT ac FROM Actividadcurso as ac where ac.actividad=:actividad and ac.curso=:curso");
		q.setParameter("actividad", actividad);
		q.setParameter("curso", curso);
		List<Actividadcurso> listaCurso = q.getResultList();
		for (Actividadcurso resultado : listaCurso) {
			//actividadCurso = resultado;		
			posicion=resultado.getPosicionActividad();
		}
		return posicion;
	}

	@Override
	public List<EstructuraVO> consultarestructuras() throws AdaptadorException {
	List<EstructuraVO> ListaEstructuraVO = new ArrayList<EstructuraVO>();
	System.out.println("consulta estruc");
	EstructuraVO EstructuraVO = new EstructuraVO();
		AdaptadorEstructura adaptador = null;
		Query q = em.createQuery("SELECT object(t) FROM Estructura AS t");
		List<Estructura> listaEstructura = q.getResultList();
		System.out.println(listaEstructura.size());
		for (int i = 0; listaEstructura.size() > i; i++) {

			adaptador = new AdaptadorEstructura(listaEstructura.get(i));
			try {
				EstructuraVO = adaptador.getEstructuraVO();
			} catch (AdaptadorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ListaEstructuraVO.add(EstructuraVO);
			System.out.println("tamaño estrruc "+ListaEstructuraVO.size());
		}
		return ListaEstructuraVO;
	}


}
