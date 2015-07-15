package org.ingenia.negocio.igestor;

import java.util.List;

import javax.ejb.Remote;

import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.ActividadxUsuarioVO;

@Remote
public interface IGestorActividadxUsuarioRemote {

	ActividadxUsuarioVO crearActividadXUsuario(ActividadxUsuarioVO actividadXUsuario) throws AdaptadorException;

	void modificarActividadXUsuario(ActividadxUsuarioVO actividadXUsuario) throws AdaptadorException;

	ActividadxUsuarioVO consultarActividadXUsuario(ActividadxUsuarioVO actividadXUsuario) throws AdaptadorException;

	List<ActividadxUsuarioVO> consultarActividadesXUsuario(ActividadxUsuarioVO actividadXUsuario) throws AdaptadorException;
}
