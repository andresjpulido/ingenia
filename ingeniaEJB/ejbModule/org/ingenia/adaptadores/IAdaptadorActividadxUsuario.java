package org.ingenia.adaptadores;

import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.ActividadVO;
import org.ingenia.comunes.vo.ActividadxUsuarioVO;
import org.ingenia.negocio.entidades.Actividad;
import org.ingenia.negocio.entidades.Actividadusuario;


public abstract class IAdaptadorActividadxUsuario {
	
	Actividadusuario actividadxUsuario;
	ActividadxUsuarioVO actividadxUsuarioVO;
	
	public abstract Actividadusuario getActividadxUsuario() throws AdaptadorException;

	public abstract ActividadxUsuarioVO getActividadxUsuarioVO() throws AdaptadorException;

}
