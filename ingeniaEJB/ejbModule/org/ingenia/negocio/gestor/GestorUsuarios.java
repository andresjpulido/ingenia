package org.ingenia.negocio.gestor;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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

import org.ingenia.adaptadores.AdaptadorOpcion;
import org.ingenia.adaptadores.AdaptadorRol;
import org.ingenia.adaptadores.AdaptadorUsuario;
import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.OpcionVO;
import org.ingenia.comunes.vo.RolVO;
import org.ingenia.comunes.vo.UsuarioVO;
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

	public UsuarioVO consultarUsuario(UsuarioVO usuarioVO) throws AdaptadorException {
		AdaptadorUsuario adaptador = null;
		Usuario usuario = null;
		
		try {
			 
			usuario = em.find(Usuario.class, usuarioVO.getId());
			if(usuario!=null){
				adaptador = new AdaptadorUsuario(usuario);
				usuarioVO = adaptador.getUsuarioVO();
			}
			

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
 
		adaptador = new AdaptadorUsuario(usuarioVO);

		try {
			usuario = em.find(Usuario.class, usuarioVO.getId());
			
			if(usuario == null){
				usuario = adaptador.getUsuario();
				usuarioVO.setFechaUltimoIngreso(new Date());
				usuarioVO.setFechaCreacion(new Date());			
				em.persist(usuario);
			}else{
				usuario = adaptador.getUsuario();
				em.merge(usuario);
			}
	 

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
			
			rol = em.find(Rol.class, rolVO.getIdRol());
			
			if( rol == null){
				rol = adaptador.getRol();
				em.persist(rol);
				
			}else{
				rol = adaptador.getRol();
				em.merge(rol);
			}
			

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
		AdaptadorOpcion adaptador = null;
		OpcionVO opcionVO = null;
		List<OpcionVO> listaOpciones = null;
		Iterator iter = null;

		@SuppressWarnings({ "unchecked", "unused" })
		List<Object[]> opciones = (List<Object[]>) em
				.createNativeQuery(
						"SELECT op.idopcion, op.nombre, op.descripcion, op.codigo, t.idopcion as seleccionado FROM Opcion AS op left join (select * from Opcionrol opr where opr.idrol = "
								+ idRol + ") as t on op.idopcion = t.idopcion ")
				.getResultList();

		if (opciones == null) {
			return null;
		}

		listaOpciones = new ArrayList<OpcionVO>();
		iter = opciones.iterator();
		while (iter.hasNext()) {
			Object[] row = (Object[]) iter.next();
			opcionVO = new OpcionVO();
			opcionVO.setCodigo(row[3].toString());
			opcionVO.setDescripcion(row[2].toString());
			opcionVO.setIdopcion(Integer.parseInt(row[0].toString()));
			opcionVO.setNombre(row[1].toString());
			opcionVO.setSeleccionado(row[4] == null ? false : true);
			listaOpciones.add(opcionVO);
		}

		return listaOpciones;
	}

	public List<RolVO> consultarRolVOPorIdUsuario(int idUsuario) {
		AdaptadorOpcion adaptador = null;
		RolVO rolVO = null;
		List<RolVO> listaRoles = null;
		Iterator iter = null;
		List<Object[]> roles = null;

		roles = (List<Object[]>) em
				.createNativeQuery(
						"SELECT r.idrol, r.nombre,r.descripcion, r.estado, t.idrol seleccionado FROM rol AS r left join (select * from rolusuario ru where ru.idusuario = "
								+ idUsuario + ") as t on r.idrol = t.idrol ")
				.getResultList();

		if (roles == null) {
			return null;
		}

		listaRoles = new ArrayList<RolVO>();
		iter = roles.iterator();
		while (iter.hasNext()) {
			Object[] row = (Object[]) iter.next();
			rolVO = new RolVO();
			rolVO.setDescripcion(row[2].toString());
			if (row[3] != null) {
				rolVO.setEstado(row[3].toString().equals("S") ? true : false);
			}
			rolVO.setIdRol(Integer.parseInt(row[0].toString()));
			rolVO.setNombre(row[1].toString());
			rolVO.setSeleccionado(row[4] == null ? false : true);
			listaRoles.add(rolVO);
		}

		return listaRoles;
	}

}
