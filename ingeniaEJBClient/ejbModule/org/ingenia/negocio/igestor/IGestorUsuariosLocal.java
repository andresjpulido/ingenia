package org.ingenia.negocio.igestor;

import java.util.List;

import javax.ejb.Local;

import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.EstudianteVO;
import org.ingenia.comunes.vo.MensajeVO;
import org.ingenia.comunes.vo.OpcionVO;
import org.ingenia.comunes.vo.RolVO;
import org.ingenia.comunes.vo.UsuarioVO;

@Local
public interface IGestorUsuariosLocal {

	public List<UsuarioVO> consultarUsuarios(UsuarioVO usuario);

	public UsuarioVO modificarUsuario(UsuarioVO usuario)
			throws AdaptadorException;

	public void crearUsuario(UsuarioVO usuario) throws AdaptadorException;

	public List<RolVO> consultarRoles(RolVO rol);

	public RolVO consultarRol(RolVO rol);

	public RolVO modificarRol(RolVO rol) throws AdaptadorException;

	public void CrearRol(RolVO rol) throws AdaptadorException;

	public List<OpcionVO> consultarOpciones() throws AdaptadorException;

	public UsuarioVO consultarUsuarioPorId(Integer idUsuario)
			throws AdaptadorException;

	public List<OpcionVO> consultarOpcionVOPorIdRol(int idRol);

	public List<RolVO> consultarRolVOPorIdUsuario(int idUsuario);
	
	public UsuarioVO consultarUsuario(UsuarioVO usuarioVO) throws AdaptadorException ;

	public void enviarMensaje(UsuarioVO destinatariovo, UsuarioVO emisorvo,String mensaje)throws AdaptadorException ;

	public List<MensajeVO> consultarMensajesRecibidos(UsuarioVO usuariovo) throws AdaptadorException ;

	public List<MensajeVO> consultarMensajesEnviados(UsuarioVO usuariovo) throws AdaptadorException ;

}
