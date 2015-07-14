package org.ingenia.negocio.gestor;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.ingenia.adaptadores.AdaptadorActividad;
import org.ingenia.adaptadores.AdaptadorCurso;
import org.ingenia.adaptadores.AdaptadorJuego;
import org.ingenia.adaptadores.AdaptadorUsuario;
import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.ActividadVO;
import org.ingenia.comunes.vo.CursoActividadVO;
import org.ingenia.comunes.vo.CursoVO;
import org.ingenia.comunes.vo.JuegoVO;
import org.ingenia.comunes.vo.RolVO;
import org.ingenia.comunes.vo.CursoVO;
import org.ingenia.comunes.vo.UsuarioVO;
import org.ingenia.negocio.entidades.Actividad;
import org.ingenia.negocio.entidades.Actividadcurso;
import org.ingenia.negocio.entidades.Curso;
import org.ingenia.negocio.entidades.Juego;
import org.ingenia.negocio.entidades.Usuario;
import org.ingenia.negocio.igestor.IGestorCursosLocal;
import org.ingenia.negocio.igestor.IGestorCursosRemote;

@Stateless
@LocalBean
public class GestorCursos implements IGestorCursosRemote,
		IGestorCursosLocal {

	@PersistenceContext(unitName = "ingeniaJPA")
	private EntityManager em;
	
	
	public GestorCursos() {

	}

	@Override
	public List<CursoVO> consultarCursosProfesor(int idprofesor) throws AdaptadorException {
		
		List<CursoVO> listaCursoVO = new ArrayList<CursoVO>();;
		CursoVO cursoVO=new CursoVO();
		AdaptadorCurso adaptador = null;
		Usuario profesor=new Usuario();
		 profesor = em.find(Usuario.class,idprofesor);
		Query q = em.createQuery("SELECT object(c) FROM Curso AS c where c.usuario=:profesor");
		 q.setParameter("profesor", profesor);
		List<Curso> listaCurso= q.getResultList();
 
        for (int i=0;listaCurso.size()>i;i++) {
        
            adaptador = new AdaptadorCurso(listaCurso.get(i));
            try {
				cursoVO =adaptador.getCursoVO();

			} catch (AdaptadorException e) {
				// T ODO Auto-generated catch block
				e.printStackTrace();
			}
			listaCursoVO.add(cursoVO);
		}
        
        return listaCursoVO;
	}

	@Override
	public List<CursoVO> consultarCursosPorNombre(CursoVO cursoVO)
			throws AdaptadorException {

		AdaptadorCurso adaptador = null;
		List<CursoVO> resultadosVO = null;
		CriteriaBuilder cb = null;
		CriteriaQuery<Curso> cq = null;
		Root<Curso> curso= null;
		List<Predicate> listaPredicados = null;
		List<Curso> resultados = null;

		try {

			cb = em.getCriteriaBuilder();
			cq = cb.createQuery(Curso.class);
			curso = cq.from(Curso.class);
			listaPredicados = new ArrayList<Predicate>();
			cq.select(curso);

			if (cursoVO.getNombre() != null
					&& cursoVO.getNombre().length() != 0) {
				listaPredicados.add(cb.like(
						curso.get("nombre").as(String.class),
						"%" + cursoVO.getNombre() + "%"));
			}

			if (listaPredicados.size() > 0) {
				Predicate[] predicados = new Predicate[listaPredicados.size()];
				listaPredicados.toArray(predicados);
				cq.where(predicados);
			}

	
			TypedQuery<Curso> tq = em.createQuery(cq);
			resultados = tq.getResultList();

			if (resultados != null) {
				resultadosVO = new ArrayList<CursoVO>();
				for (Curso cursoResultado : resultados) {
					if (cursoResultado.getUsuario().getIdusuario()==cursoVO.getProfesor().getId()){
					adaptador = new AdaptadorCurso(cursoResultado);
					resultadosVO.add(adaptador.getCursoVO());}
				}
			}

		} catch (AdaptadorException e) {
			e.printStackTrace();
		}

		return resultadosVO;
		
		
	}

	@Override
	public CursoVO consultarCursoVO(CursoVO cursoVO) throws AdaptadorException {
		// TODO Auto-generated method stub
		AdaptadorCurso adaptador = null;
		AdaptadorActividad adaptadorA = null;
		List<ActividadVO> listaactividadesVO = null;
		Curso curso = new Curso();
		curso = em.find(Curso.class,cursoVO.getIdcurso());
		adaptador = new AdaptadorCurso(curso);
		listaactividadesVO = new ArrayList<ActividadVO>();
		Query q = em.createQuery("SELECT c.actividad FROM Actividadcurso AS c where c.curso=:curso order by c.posicionActividad");
		 q.setParameter("curso", curso);
		 listaactividadesVO= q.getResultList();		
		 cursoVO =adaptador.getCursoVO();
		 cursoVO.setActividades(listaactividadesVO);

		return cursoVO;
		
	}

	@Override
	public void modificarCursoVO(CursoVO cursoVO) throws AdaptadorException {
		// TODO Auto-generated method stub
		AdaptadorCurso adaptador = null;
		AdaptadorActividad adaptadorA = null;
		//List<Actividad> listaactividades = null;
		//Curso curso = em.find(Curso.class,cursoVO.getIdcurso());
		Curso curso = null;		        
		adaptador = new AdaptadorCurso(cursoVO);
		
		/*listaactividades = new ArrayList<Actividad>();
		for (ActividadVO actividadVO : cursoVO.getActividades()) {
			adaptadorA = new AdaptadorActividad(actividadVO);
			listaactividades.add(adaptadorA.getActividad());
			}	*/
		curso =adaptador.getCurso();
		//curso.setActividads(listaactividades);
		 em.merge(curso);
	}

	
	@Override
	public void eliminarCursoVO(CursoVO cursoVO) throws AdaptadorException {
		// TODO Auto-generated method stub

	}

	@Override
	public void crearCursoVO(CursoVO cursoVO) throws AdaptadorException {
		
		AdaptadorCurso adaptador = null;
		Curso curso = new Curso();
		 Query q = em.createQuery("SELECT count(c) FROM Curso as c");   
         cursoVO.setIdcurso(((Number) q.getResultList().get(0)).intValue()+1);
		adaptador = new AdaptadorCurso(cursoVO);

		try {
			
			curso = adaptador.getCurso();		  
            em.persist(curso);
	
		} catch (AdaptadorException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public List<ActividadVO> consultarActividadesDisponibles(CursoVO cursoVO) {

		List<ActividadVO> ListaActividadVO = new ArrayList<ActividadVO>();;
		ActividadVO ActividadVO=new ActividadVO();
		AdaptadorActividad adaptador = null;
		Curso curso=em.find(Curso.class,cursoVO.getIdcurso());
		
		Query q = em.createQuery("SELECT c.actividad FROM Actividadcurso AS c where c.curso=:curso");
		q.setParameter("curso", curso);
		List<Actividad> listaActividadactuales= q.getResultList();
		//System.out.println("tamñom" +listaActividadactuales.size());		
		Query q2 = em.createQuery("SELECT object(a) FROM Actividad AS a ");
		List<Actividad> totallistaActividades= q2.getResultList();
		List<Actividad> listaactualizada = new ArrayList<Actividad>();
		listaactualizada=totallistaActividades;
		 for (int i=0;totallistaActividades.size()>i;i++) {
			 System.out.println("es esta "+totallistaActividades.get(i).getIdactividad());		 
		 }
        for (int i=0;totallistaActividades.size()>i;i++) {
        	System.out.println("tamñom" +totallistaActividades.size());
        	 for (int j=0;listaActividadactuales.size()>j;j++) {        	   
      	System.out.println("compara "+totallistaActividades.get(i).getIdactividad()+" con "+listaActividadactuales.get(j).getIdactividad());
              	if((listaActividadactuales.get(j).equals(totallistaActividades.get(i)))){
            		int temp=listaActividadactuales.size()+1;
            		System.out.println("elimino "+totallistaActividades.get(i).getIdactividad());
            		listaactualizada.remove(i);
            		j=temp;           
            		i=-1;            		
            }
                 }
           }   	 
            for (int i=0;listaactualizada.size()>i;i++) {
 
        adaptador = new AdaptadorActividad(listaactualizada.get(i));
        Juego juego = em.find(Juego.class,listaactualizada.get(i).getJuego().getIdjuego());
        AdaptadorJuego adap =new AdaptadorJuego(juego);
    	try {
			ActividadVO=adaptador.getActividadVO();
	    	ActividadVO.setJuegoVO(adap.getJuegoVO());

		} catch (AdaptadorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ListaActividadVO.add(ActividadVO);
       
            }
		
        return ListaActividadVO;
	}

	@Override
	public void asociarActividad(CursoActividadVO cursoActividadVO) throws AdaptadorException {
		Actividadcurso cursoActividad = new Actividadcurso();
		Curso curso=em.find(Curso.class,cursoActividadVO.getCurso().getIdcurso());
		Actividad actividad=em.find(Actividad.class,cursoActividadVO.getActividad().getIdactividad());
		cursoActividad.setActividad(actividad);
		cursoActividad.setCurso(curso);
		cursoActividad.setPosicionActividad(cursoActividadVO.getPosicion());
		em.persist(cursoActividad);
	}

}
