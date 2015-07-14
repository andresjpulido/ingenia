package org.ingenia.negocio.gestor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.ingenia.adaptadores.AdaptadorOpcion;
import org.ingenia.adaptadores.AdaptadorRol;
import org.ingenia.adaptadores.AdaptadorUsuario;
import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.OpcionVO;
import org.ingenia.comunes.vo.RolVO;
import org.ingenia.comunes.vo.UsuarioVO;
import org.ingenia.negocio.entidades.Juego;
import org.ingenia.negocio.entidades.Opcion;
import org.ingenia.negocio.entidades.Rol;
import org.ingenia.negocio.entidades.Usuario;
import org.ingenia.negocio.igestor.IGestorUsuariosLocal;
import org.ingenia.negocio.igestor.IGestorUsuariosRemote;

@Stateless
@LocalBean
public class GestorUsuarios implements IGestorUsuariosRemote,
		IGestorUsuariosLocal {

	@PersistenceContext(unitName = "ingeniaJPA")
	private EntityManager em;

	public GestorUsuarios() {
	}

	@Override
	public List<UsuarioVO> consultarUsuarios(UsuarioVO usuarioVO) {
		AdaptadorUsuario adaptador = null;
		List<UsuarioVO> resultadosVO = null;
		CriteriaBuilder cb = null;
		CriteriaQuery<Usuario> cq = null;
		Root<Usuario> usuario = null;
		List<Predicate> listaPredicados = null;
		List<Usuario> resultados = null;

		try {

			cb = em.getCriteriaBuilder();
			cq = cb.createQuery(Usuario.class);
			usuario = cq.from(Usuario.class);
			listaPredicados = new ArrayList<Predicate>();
			cq.select(usuario);

			if (usuarioVO.getNombre() != null
					&& usuarioVO.getNombre().length() != 0) {
				listaPredicados.add(cb.like(
						usuario.get("nombre").as(String.class),
						"%" + usuarioVO.getNombre() + "%"));
			}

			if (usuarioVO.getAlias() != null
					&& usuarioVO.getAlias().length() != 0) {
				listaPredicados.add(cb.equal(
						usuario.get("alias").as(String.class),
						usuarioVO.getAlias()));
			}

			if (usuarioVO.getClave() != null
					&& usuarioVO.getClave().length() != 0) {
				listaPredicados.add(cb.equal(
						usuario.get("clave").as(String.class),
						usuarioVO.getClave()));
			}

			if (listaPredicados.size() > 0) {
				Predicate[] predicados = new Predicate[listaPredicados.size()];
				listaPredicados.toArray(predicados);
				cq.where(predicados);
			}

			cq.orderBy(cb.asc(usuario.get("apellido")));
			TypedQuery<Usuario> tq = em.createQuery(cq);
			resultados = tq.getResultList();

			if (resultados != null) {
				resultadosVO = new ArrayList<UsuarioVO>();
				for (Usuario usuarioResultado : resultados) {
					adaptador = new AdaptadorUsuario(usuarioResultado);
					resultadosVO.add(adaptador.getUsuarioVO());
				}
			}

		} catch (AdaptadorException e) {
			e.printStackTrace();
		}

		return resultadosVO;
	}

	@Override
	public UsuarioVO modificarUsuario(UsuarioVO usuarioVO) {
		AdaptadorUsuario adaptador = null;
		Usuario usuario = new Usuario();
		adaptador = new AdaptadorUsuario(usuarioVO);

		try {
			usuario = adaptador.getUsuario();
			usuario = em.merge(usuario);
			adaptador = new AdaptadorUsuario(usuario);
			usuarioVO = adaptador.getUsuarioVO();

		} catch (AdaptadorException e) {
			e.printStackTrace();
		}
		return usuarioVO;
	}

	@Override
	public List<RolVO> consultarRoles(RolVO rolVO) {
		AdaptadorRol adaptador = null;
		List<RolVO> resultadosVO = null;
		CriteriaBuilder cb = null;
		CriteriaQuery<Rol> cq = null;
		Root<Rol> rol = null;
		List<Predicate> listaPredicados = null;
		List<Rol> resultados = null;

		try {

			cb = em.getCriteriaBuilder();
			cq = cb.createQuery(Rol.class);
			rol = cq.from(Rol.class);
			listaPredicados = new ArrayList<Predicate>();
			cq.select(rol);

			if (rolVO.getNombre() != null && rolVO.getNombre().length() != 0) {
				listaPredicados.add(cb.like(rol.get("nombre").as(String.class),
						"%" + rolVO.getNombre() + "%"));
			}

			if (listaPredicados.size() > 0) {
				Predicate[] predicados = new Predicate[listaPredicados.size()];
				listaPredicados.toArray(predicados);
				cq.where(predicados);
			}

			cq.orderBy(cb.asc(rol.get("nombre")));
			TypedQuery<Rol> tq = em.createQuery(cq);
			resultados = tq.getResultList();

			if (resultados != null) {
				resultadosVO = new ArrayList<RolVO>();
				for (Rol rolResultado : resultados) {
					adaptador = new AdaptadorRol(rolResultado);
					resultadosVO.add(adaptador.getRolVO());
				}
			}

		} catch (AdaptadorException e) {
			e.printStackTrace();
		}

		return resultadosVO;
	}

	@Override
	public RolVO consultarRol(RolVO rolVO) {
		AdaptadorRol adaptador = null;
		Rol rol = em.find(Rol.class, rolVO.getIdRol());

		adaptador = new AdaptadorRol(rol);

		try {
			rolVO = adaptador.getRolVO();

		} catch (AdaptadorException e) {
			e.printStackTrace();
		}

		return rolVO;
	}

	@Override
	public RolVO modificarRol(RolVO rolVO) {
		AdaptadorRol adaptador = null;
		Rol rol = new Rol();
		adaptador = new AdaptadorRol(rolVO);

		try {
			rol = adaptador.getRol();
			rol = em.merge(rol);
			adaptador = new AdaptadorRol(rol);
			rolVO = adaptador.getRolVO();

		} catch (AdaptadorException e) {
			e.printStackTrace();
		}
		return rolVO;
	}

	@Override
	public void crearUsuario(UsuarioVO usuarioVO) throws AdaptadorException {
		AdaptadorUsuario adaptador = null;
		Usuario usuario = null;
		usuarioVO.setFechaUltimoIngreso(new Date());
		usuarioVO.setFechaCreacion(new Date());
		adaptador = new AdaptadorUsuario(usuarioVO);

		try {
			usuario = adaptador.getUsuario();
			em.persist(usuario);

		} catch (AdaptadorException e) {
			throw e;

		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void CrearRol(RolVO rolVO) {
		AdaptadorRol adaptador = null;
		Rol rol = new Rol();
		adaptador = new AdaptadorRol(rolVO);

		try {
			rol = adaptador.getRol();
			em.persist(rol);

		} catch (AdaptadorException e) {
			e.printStackTrace();
		}
	}

	public List<OpcionVO> consultarOpciones() throws AdaptadorException {

		List<OpcionVO> listaOpcionVO = null;
		OpcionVO JuegoVO = null;
		AdaptadorOpcion adaptador = null;

		Query q = em.createQuery("SELECT object(t) FROM Juego AS t");
		List<OpcionVO> listaOpcion = q.getResultList();

		if (listaOpcion != null && !listaOpcion.isEmpty()) {
			listaOpcionVO = new ArrayList<OpcionVO>();

			for (int i = 0; listaOpcion.size() > i; i++) {

				adaptador = new AdaptadorOpcion(listaOpcion.get(i));
				try {
					JuegoVO = adaptador.getOpcionVO();
				} catch (AdaptadorException e) {
					e.printStackTrace();
				}
				listaOpcionVO.add(JuegoVO);
			}
		}

		return listaOpcion;
	}

	public UsuarioVO consultarUsuarioPorId(Integer idUsuario) {
		UsuarioVO usuarioVO = null;
		Usuario usuario = em.find(Usuario.class, idUsuario);
		AdaptadorUsuario adaptador = new AdaptadorUsuario(usuario);
		try {
			usuarioVO = adaptador.getUsuarioVO();
		} catch (AdaptadorException e) {
			e.printStackTrace();
		}
		return usuarioVO;
	}

	public List<OpcionVO> consultarOpcionVOPorIdRol(int idRol) {

		Query q = em
				.createQuery("SELECT op FROM Opcion AS op left join (select opr from Opcionrol opr where opr.idrol = 1) as t on op.idopcion = t.opcion.idopcion ");
		List<Opcion> listaJuego = q.getResultList();
		
		System.out.println(listaJuego);
		
		for (Opcion opcion : listaJuego) {
			System.out.println(opcion.getNombre());
		}
		// AdaptadorOpcion adaptador = new AdaptadorOpcion(rol)

		return null;
	}
}
