package org.ingenia.negocio.gestor;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.ingenia.adaptadores.AdaptadorActividadxUsuario;
import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.ActividadxUsuarioVO;
import org.ingenia.negocio.entidades.Actividadusuario;
import org.ingenia.negocio.entidades.ActividadusuarioPK;
import org.ingenia.negocio.igestor.IGestorActividadxUsuarioLocal;
import org.ingenia.negocio.igestor.IGestorActividadxUsuarioRemote;

/**
 * Session Bean implementation class GestorActividadxUsuario
 */
@Stateless
@LocalBean
public class GestorActividadxUsuario implements IGestorActividadxUsuarioRemote, IGestorActividadxUsuarioLocal {

	@PersistenceContext(unitName = "ingeniaJPA")
	private EntityManager em;
	
	public GestorActividadxUsuario() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ActividadxUsuarioVO crearActividadxUsuario(ActividadxUsuarioVO actividadxUsuarioVO) throws AdaptadorException
	{
		AdaptadorActividadxUsuario adaptador = null;
		Actividadusuario actividadxUsuario = new Actividadusuario();
		
		ActividadusuarioPK actividadxUsuariopk = new ActividadusuarioPK();
		actividadxUsuariopk.setActividadIdactividad(actividadxUsuarioVO.getIdActividad());
		actividadxUsuariopk.setIdCurso(actividadxUsuarioVO.getIdCurso());
		actividadxUsuariopk.setUsuarioIdusuario(actividadxUsuarioVO.getIdUsuario());
		Actividadusuario actividadxUsuarioQ = em.find(Actividadusuario.class,actividadxUsuariopk);
		
		adaptador = new AdaptadorActividadxUsuario(actividadxUsuarioVO);
			
		
		try {
			actividadxUsuario = adaptador.getActividadxUsuario(); 
			UserTransaction transaction = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			transaction.begin();
			if(actividadxUsuarioQ==null)
			em.persist(actividadxUsuario);
			else 
			{
				actividadxUsuarioQ.setFecha(actividadxUsuarioVO.getFecha());
				actividadxUsuarioQ.setPuntos(actividadxUsuarioVO.getPuntos());
				actividadxUsuarioQ.setNumeroIntento(actividadxUsuarioQ.getNumeroIntento()+actividadxUsuarioVO.getNumeroIntento());
				actividadxUsuarioQ.setNumeroMovimientos(actividadxUsuarioVO.getNumeroMovimientos());
			}
			em.joinTransaction();
			transaction.commit();
		} catch (AdaptadorException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (NotSupportedException e) {
			e.printStackTrace();
		} catch (SystemException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (RollbackException e) {
			e.printStackTrace();
		} catch (HeuristicMixedException e) {
			e.printStackTrace();
		} catch (HeuristicRollbackException e) {
			e.printStackTrace();
		}
		
		return actividadxUsuarioVO;
	}

	@Override
	public void modificarActividadxUsuario(ActividadxUsuarioVO actividadxUsuarioVO) throws AdaptadorException
	{
		AdaptadorActividadxUsuario adaptador = null;
		Actividadusuario actividadxUsuario = null;		        
		adaptador = new AdaptadorActividadxUsuario(actividadxUsuarioVO);
		
		try {
			actividadxUsuario = adaptador.getActividadxUsuario();
			actividadxUsuario.setFecha(actividadxUsuarioVO.getFecha());
			actividadxUsuario.setNumeroIntento(actividadxUsuarioVO.getNumeroIntento());
			actividadxUsuario.setPuntos(actividadxUsuarioVO.getPuntos());			
			em.merge(actividadxUsuario);
		} catch (AdaptadorException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ActividadxUsuarioVO consultarActividadxUsuario(ActividadxUsuarioVO actividadxUsuario) throws AdaptadorException
	{
		AdaptadorActividadxUsuario adaptador = null;
		Actividadusuario actividadxUsuarioQ = em.find(Actividadusuario.class,actividadxUsuario);
		adaptador = new AdaptadorActividadxUsuario(actividadxUsuarioQ);
		ActividadxUsuarioVO actividadxUsuarioVO = adaptador.getActividadxUsuarioVO();
		
		return actividadxUsuarioVO;
	}

	@Override
	public List<ActividadxUsuarioVO> consultarActividadesxUsuario(ActividadxUsuarioVO actividadVO) throws AdaptadorException
	{
		List<ActividadxUsuarioVO> ListaActividadxUsuarioVO = new ArrayList<ActividadxUsuarioVO>();
		ActividadxUsuarioVO actividadxUsuario = new ActividadxUsuarioVO();
		AdaptadorActividadxUsuario adaptador = null;
		Query q = em.createQuery("SELECT object(t) FROM Actividadusuario AS t where t.actividad = :actividad");
		q.setParameter("actividad", actividadVO);
		List<Actividadusuario> listaActividadxUsuario = q.getResultList();
 
        for (int i=0;listaActividadxUsuario.size()>i;i++) {
    
            adaptador = new AdaptadorActividadxUsuario(listaActividadxUsuario.get(i));
            try {
            	actividadxUsuario = adaptador.getActividadxUsuarioVO();
			} catch (AdaptadorException e) {
				e.printStackTrace();
			}
            ListaActividadxUsuarioVO.add(actividadxUsuario);
		}
        
        return ListaActividadxUsuarioVO;
	}

	public GestorActividadxUsuario(EntityManager em) {
		this.em = em;
	}
}
