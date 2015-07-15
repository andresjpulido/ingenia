package org.ingenia.negocio.igestor;

import java.util.List;

import javax.ejb.Local;

import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.ActividadxUsuarioVO;

@Local
public interface IGestorActividadXUsuarioLocal {

	ActividadxUsuarioVO crearActividadXUsuario(ActividadxUsuarioVO actividadXUsuario) throws AdaptadorException;

	void modificarActividadXUsuario(ActividadxUsuarioVO actividadXUsuario) throws AdaptadorException;

	ActividadxUsuarioVO consultarActividadXUsuario(ActividadxUsuarioVO actividadXUsuario) throws AdaptadorException;

	List<ActividadxUsuarioVO> consultarActividadesXUsuario(ActividadxUsuarioVO actividadXUsuario) throws AdaptadorException;
}
