package org.ingenia.negocio.igestor;

import java.util.List;

import javax.ejb.Local;

import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.ActividadxUsuarioVO;

@Local
public interface IGestorActividadxUsuarioLocal {

	ActividadxUsuarioVO crearActividadxUsuario(ActividadxUsuarioVO actividadXUsuario) throws AdaptadorException;

	void modificarActividadxUsuario(ActividadxUsuarioVO actividadXUsuario) throws AdaptadorException;

	ActividadxUsuarioVO consultarActividadxUsuario(ActividadxUsuarioVO actividadXUsuario) throws AdaptadorException;

	List<ActividadxUsuarioVO> consultarActividadesxUsuario(ActividadxUsuarioVO actividadXUsuario) throws AdaptadorException;
}
