package org.ingenia.negocio.igestor;

import java.util.List;

import javax.ejb.Remote;

import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.ActividadxUsuarioVO;

@Remote
public interface IGestorActividadxUsuarioRemote {

	ActividadxUsuarioVO crearActividadxUsuario(ActividadxUsuarioVO actividadXUsuario) throws AdaptadorException;

	void modificarActividadxUsuario(ActividadxUsuarioVO actividadxUsuario) throws AdaptadorException;

	ActividadxUsuarioVO consultarActividadxUsuario(ActividadxUsuarioVO actividadXUsuario) throws AdaptadorException;

	List<ActividadxUsuarioVO> consultarActividadesxUsuario(ActividadxUsuarioVO actividadXUsuario) throws AdaptadorException;
}
