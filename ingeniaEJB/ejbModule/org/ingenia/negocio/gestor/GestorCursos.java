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
import org.ingenia.adaptadores.AdaptadorActividadxUsuario;
import org.ingenia.adaptadores.AdaptadorCurso;
import org.ingenia.adaptadores.AdaptadorUsuario;
import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.ActividadVO;
import org.ingenia.comunes.vo.ActividadxUsuarioVO;
import org.ingenia.comunes.vo.CursoActividadVO;
import org.ingenia.comunes.vo.CursoVO;
import org.ingenia.comunes.vo.EstudianteVO;
import org.ingenia.comunes.vo.UsuarioVO;
import org.ingenia.negocio.entidades.Actividad;
import org.ingenia.negocio.entidades.Actividadcurso;
import org.ingenia.negocio.entidades.Actividadusuario;
import org.ingenia.negocio.entidades.Curso;
import org.ingenia.negocio.entidades.Estudiantecurso;
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
	public List<CursoVO> consultarCursosProfesor(UsuarioVO profesorVO) throws AdaptadorException {
		
		List<CursoVO> listaCursoVO = new ArrayList<CursoVO>();;
		CursoVO cursoVO=new CursoVO();
		AdaptadorCurso adaptador = null;
		Usuario profesor=new Usuario();
		AdaptadorUsuario adapprofe=new AdaptadorUsuario(profesorVO);
		profesor=adapprofe.getUsuario();
		 //profesor = em.find(Usuario.class,idprofesor);
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
		AdaptadorUsuario adap_est=null;
		List<Actividad> listaactividades = new ArrayList<Actividad>();
		List<ActividadVO> listaactividadesVO = new ArrayList<ActividadVO>();;
		List<Estudiantecurso> listacursousuario =  new ArrayList<Estudiantecurso>();;
		List<EstudianteVO> listaestudianteVO = new ArrayList<EstudianteVO>();
		EstudianteVO estudiante = new EstudianteVO();
		ActividadVO actividad=new ActividadVO();

		Curso curso = new Curso();
		curso = em.find(Curso.class,cursoVO.getIdcurso());
		
			
		Query q = em.createQuery("SELECT c.actividad FROM Actividadcurso AS c where c.curso=:curso order by c.posicionActividad");
		 q.setParameter("curso", curso);
		 listaactividades= q.getResultList();	
		 
		 for (int i=0;listaactividades.size()>i;i++) {
	        adaptadorA = new AdaptadorActividad(listaactividades.get(i));
	       actividad = adaptadorA.getActividadVO();
	        listaactividadesVO.add(actividad);	        
		 }
		 
		Query q2 = em.createQuery("SELECT c FROM Estudiantecurso AS c where c.curso=:curso order by c.puntaje desc");
		q2.setParameter("curso", curso);
		listacursousuario= q2.getResultList();	
			 
		 for (int i=0;listacursousuario.size()>i;i++) {
			 adap_est=new AdaptadorUsuario(listacursousuario.get(i).getUsuario());
			 estudiante=adap_est.getEstudianteVO();
			 estudiante.setPuntaje(listacursousuario.get(i).getPuntaje());
			 listaestudianteVO.add(estudiante);			 	 
		 }
		 
		 adaptador = new AdaptadorCurso(curso);	
		 cursoVO =adaptador.getCursoVO();
		 cursoVO.setActividades(listaactividadesVO);
		 cursoVO.setEstudiantes(listaestudianteVO);

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
	public List<ActividadVO> consultarActividadesDisponibles(CursoVO cursoVO, UsuarioVO profesorVO) {

		List<ActividadVO> ListaActividadVO = new ArrayList<ActividadVO>();;
		ActividadVO ActividadVO=new ActividadVO();
		AdaptadorActividad adaptador = null;
		Curso curso=em.find(Curso.class,cursoVO.getIdcurso());
		Usuario profesor = em.find(Usuario.class,profesorVO.getId());
		Query q = em.createQuery("SELECT c.actividad FROM Actividadcurso AS c where c.curso=:curso");
		q.setParameter("curso", curso);
		List<Actividad> listaActividadactuales= q.getResultList();
	
		Query q2 = em.createQuery("SELECT object(a) FROM Actividad AS a WHERE a.usuario=:profesor");
		q2.setParameter("profesor", profesor);
		List<Actividad> totallistaActividades= q2.getResultList();
		List<Actividad> listaactualizada = new ArrayList<Actividad>();
		listaactualizada=totallistaActividades;
		 for (int i=0;totallistaActividades.size()>i;i++) {
			 System.out.println("es esta "+totallistaActividades.get(i).getIdactividad());		 
		 }
        for (int i=0;totallistaActividades.size()>i;i++) {
        	System.out.println("tam�om" +totallistaActividades.size());
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

    	try {
			ActividadVO=adaptador.getActividadVO();

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

	@Override
	public List<ActividadxUsuarioVO> consultarActividadesCursoEstudiante(CursoVO cursoVO,
			EstudianteVO estudianteVO) throws AdaptadorException {
		List<ActividadxUsuarioVO> listaActividadesEstudianteVO= new ArrayList<ActividadxUsuarioVO>();
		List<Actividad> listaActividades = new ArrayList<Actividad>();
		Actividadusuario actividadUsuario = new Actividadusuario();
		ActividadxUsuarioVO actividadUsuarioVO = new ActividadxUsuarioVO();
		AdaptadorActividadxUsuario adaptador ;
		Curso curso=em.find(Curso.class,cursoVO.getIdcurso());
		 for (int i=0;curso.getActividadcursos().size()>i;i++) {
			 listaActividades.add(curso.getActividadcursos().get(i).getActividad());
		 }
		 for (int i=0;curso.getEstudiantecursos().size()>i;i++) {
			 if(curso.getEstudiantecursos().get(i).getUsuario().getIdusuario()==estudianteVO.getId()){
			 for (int j=0;listaActividades.size()>j;j++) {
				 Query q = em.createQuery("SELECT object(ac) FROM Actividadusuario as ac where ac.usuario=:usuario and ac.actividad=:actividad");
					q.setParameter("usuario", curso.getEstudiantecursos().get(i).getUsuario());
					q.setParameter("actividad", listaActividades.get(j));
					List<Actividadusuario> resultados = q.getResultList();	
					for (Actividadusuario resultado : resultados) {
						actividadUsuario = resultado;
						adaptador = new AdaptadorActividadxUsuario(actividadUsuario);
						actividadUsuarioVO=adaptador.getActividadxUsuarioVO();
						listaActividadesEstudianteVO.add(actividadUsuarioVO);
					}
			     }
			 }
		 }
		 return listaActividadesEstudianteVO;
	}
	
	@Override
	public List<CursoVO> consultarCursosEstudiante(UsuarioVO estudianteVO) throws AdaptadorException {
		
		List<CursoVO> listaCursoVO = new ArrayList<CursoVO>();;
		CursoVO cursoVO=new CursoVO();
		AdaptadorCurso adaptador = null;
		Usuario estudiante=new Usuario();
		AdaptadorUsuario adapprofe=new AdaptadorUsuario(estudianteVO);
		estudiante=adapprofe.getUsuario();
		 //profesor = em.find(Usuario.class,idprofesor);
		Query q = em.createQuery("SELECT c.curso FROM Estudiantecurso AS c where c.usuario=:estudiante");
		 q.setParameter("estudiante", estudiante);
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
	public List<CursoVO> consultarCursosDisponibleEstudiante(List<CursoVO> listaCursosest) throws AdaptadorException {
		
		List<CursoVO> listaCursoVO = new ArrayList<CursoVO>();
		CursoVO cursoVO=new CursoVO();
		Query q = em.createQuery("SELECT Object(c) FROM Curso AS c");
		List<Curso> listaCurso= q.getResultList();
 		AdaptadorCurso adaptador = null;

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
         
         for (int i=0;listaCursosest.size()>i;i++) {
         
        	 for (int j=0;listaCursoVO.size()>j;j++) {
        		 
        		 if(listaCursosest.get(i).equals(listaCursosest.get(j))){
        			 listaCursoVO.remove(j);
        			 j=listaCursoVO.size()+1;
        		 }
             }
         }

         System.out.println("tam "+ listaCursoVO.size());

		return listaCursoVO;
	}

	@Override
	public void inscribirCurso(UsuarioVO usuarioVO, CursoVO cursoVO)
			throws AdaptadorException {
		// TODO Auto-generated method stub
		Curso curso = em.find(Curso.class, cursoVO.getIdcurso());
		Usuario usuario = em.find(Usuario.class, usuarioVO.getId());
        Estudiantecurso Estudiantecurso= new Estudiantecurso();
        Estudiantecurso.setCurso(curso);
        Estudiantecurso.setUsuario(usuario);
		em.persist(Estudiantecurso);
	}

	@Override
	public List<CursoVO> consultarCursosEstudiantePorNombre(CursoVO cursoVO,UsuarioVO usuarioVO) throws AdaptadorException {
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
			List<CursoVO> listaCursoVO= consultarCursosEstudiante(usuarioVO);

			System.out.println(listaCursoVO.size()+" estudiacurso");
			if (resultados != null) {
				resultadosVO = new ArrayList<CursoVO>();
				for (Curso cursoResultado : resultados) {
					for (CursoVO cursoLista : listaCursoVO) {
					if (cursoResultado.getIdcurso()==cursoLista.getIdcurso()){
					adaptador = new AdaptadorCurso(cursoResultado);
					resultadosVO.add(adaptador.getCursoVO());}
					}
					}
					
			}

		} catch (AdaptadorException e) {
			e.printStackTrace();
		}

		return resultadosVO;
		
	}
	
	@Override
	public List<CursoVO> consultarCursosDisponiblesEstudiantePorNombre(CursoVO cursoVO,UsuarioVO usuarioVO) throws AdaptadorException {
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

		List<CursoVO> listaCursoVO= consultarCursosEstudiante(usuarioVO);
		List<CursoVO> listaCursoDisponibleVO = consultarCursosDisponibleEstudiante(listaCursoVO);
			System.out.println(listaCursoDisponibleVO.size()+" estudiacurso");
			if (resultados != null) {
				resultadosVO = new ArrayList<CursoVO>();
				for (Curso cursoResultado : resultados) {
					for (CursoVO cursoLista : listaCursoDisponibleVO) {
					if (cursoResultado.getIdcurso()==cursoLista.getIdcurso()){
					adaptador = new AdaptadorCurso(cursoResultado);
					resultadosVO.add(adaptador.getCursoVO());}
					}
					}
					
			}

		} catch (AdaptadorException e) {
			e.printStackTrace();
		}
		System.out.println(resultadosVO.size()+" final");

		return resultadosVO;
		
	}

}
