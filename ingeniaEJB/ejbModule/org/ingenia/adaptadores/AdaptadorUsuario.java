package org.ingenia.adaptadores;

import java.util.ArrayList;
import java.util.List;

import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.EstudianteVO;
import org.ingenia.comunes.vo.OpcionVO;
import org.ingenia.comunes.vo.RolVO;
import org.ingenia.comunes.vo.UsuarioVO;
import org.ingenia.negocio.entidades.Opcion;
import org.ingenia.negocio.entidades.Rol;
import org.ingenia.negocio.entidades.Usuario;

public class AdaptadorUsuario extends IAdaptadorUsuario {

	public AdaptadorUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public AdaptadorUsuario(UsuarioVO usuarioVO) {
		this.usuarioVO = usuarioVO;
	}

	@Override
	public Usuario getUsuario() throws AdaptadorException {
		
		if (usuarioVO == null)
			return null;
		
		Usuario usuario = null;
		AdaptadorOpcion adaptadorOpcion = null;
		AdaptadorRol adaptadorRol = null;
		Opcion opcion = null;
		List<Rol> listaRoles = null;
		List<Opcion> listaOpciones = null;
		Rol rol = null;
		
		usuario = new Usuario();
		usuario.setApellido(usuarioVO.getApellido());
		usuario.setCorreo(usuarioVO.getCorreo());
		usuario.setFechaCreacion(usuarioVO.getFechaCreacion());
		usuario.setFechaUltimoIngreso(usuarioVO.getFechaUltimoIngreso());
		usuario.setGenero(null);
		usuario.setIdusuario(usuarioVO.getId());
		usuario.setNombre(usuarioVO.getNombre());
		usuario.setClave(usuarioVO.getClave());
		usuario.setAlias(usuarioVO.getAlias());
		usuario.setIdentificacion(usuarioVO.getIdentificacion());
		usuario.setActivo(usuarioVO.isActivo()?1:0);
		usuario.setIdusuario(usuarioVO.getId());

		if (usuarioVO.getListaRoles() != null && !usuarioVO.getListaRoles().isEmpty()) {
			listaRoles = new ArrayList<Rol>();

			for (RolVO rolVO : usuarioVO.getListaRoles()) {
				adaptadorRol = new AdaptadorRol(rolVO);				
				rol = adaptadorRol.getRol();
				
				if (rolVO.getOpcions() != null && !rolVO.getOpcions().isEmpty()) {
					listaOpciones = new ArrayList<Opcion>();

					for (OpcionVO opcionVO : rolVO.getOpcions()) {
						adaptadorOpcion = new AdaptadorOpcion(opcionVO);
						opcion = adaptadorOpcion.getOpcion();
						listaOpciones.add(opcion);
					}
					rol.setOpcions(listaOpciones);
				}
				listaRoles.add(rol);
			}
		}
		usuario.setRols(listaRoles);
		
		return usuario;
	}

	@Override
	public UsuarioVO getUsuarioVO() throws AdaptadorException {

		if (usuario == null)
			return null;

		UsuarioVO usuarioVO = null;
		AdaptadorOpcion adaptadorOpcion = null;
		AdaptadorRol adaptadorRol = null;
		OpcionVO opcionVO = null;
		List<RolVO> listaRoles = null;
		List<OpcionVO> listaOpciones = null;
		RolVO rolvO = null;
		
		usuarioVO = new UsuarioVO();
		usuarioVO.setApellido(usuario.getApellido());
		usuarioVO.setCorreo(usuario.getCorreo());
		usuarioVO.setFechaCreacion(usuario.getFechaCreacion());
		usuarioVO.setFechaUltimoIngreso(usuario.getFechaUltimoIngreso());
		usuarioVO.setGenero(null);
		usuarioVO.setId(usuario.getIdusuario());
		usuarioVO.setNombre(usuario.getNombre());
		usuarioVO.setClave(usuario.getClave());
		usuarioVO.setAlias(usuario.getAlias());
		usuarioVO.setIdentificacion(usuario.getIdentificacion());
		usuarioVO.setActivo(usuario.getActivo() == 1?true:false);
		usuarioVO.setId(usuario.getIdusuario());
		
		if (usuario.getRols() != null && !usuario.getRols().isEmpty()) {
			listaRoles = new ArrayList<RolVO>();

			for (Rol rol : usuario.getRols()) {
				adaptadorRol = new AdaptadorRol(rol);				
				rolvO = adaptadorRol.getRolVO();
				
				if (rol.getOpcions() != null && !rol.getOpcions().isEmpty()) {
					listaOpciones = new ArrayList<OpcionVO>();

					for (Opcion opcion : rol.getOpcions()) {
						adaptadorOpcion = new AdaptadorOpcion(opcion);
						opcionVO = adaptadorOpcion.getOpcionVO();
						listaOpciones.add(opcionVO);
					}
					rolvO.setOpcions(listaOpciones);
				}
				listaRoles.add(rolvO);
			}
		}
		usuarioVO.setListaRoles(listaRoles);

		return usuarioVO;
	}
	

	public EstudianteVO getEstudianteVO() throws AdaptadorException {

		if (usuario == null)
			return null;

		EstudianteVO estudianteVO = null;
		
		estudianteVO = new EstudianteVO();
		estudianteVO.setApellido(usuario.getApellido());
		estudianteVO.setCorreo(usuario.getCorreo());
		estudianteVO.setId(usuario.getIdusuario());
		estudianteVO.setNombre(usuario.getNombre());
		estudianteVO.setIdentificacion(usuario.getIdentificacion());

		System.out.println("si lo guardo "+estudianteVO.getIdentificacion());
		return estudianteVO;
	}
}
