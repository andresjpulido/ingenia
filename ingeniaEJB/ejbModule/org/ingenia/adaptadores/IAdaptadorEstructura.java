package org.ingenia.adaptadores;

import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.EstructuraVO;
import org.ingenia.negocio.entidades.Estructura;


public abstract class IAdaptadorEstructura {
	EstructuraVO EstructuraVO=null;
	Estructura Estructura=null;
	
	public abstract Estructura getEstructura() throws AdaptadorException;

	public abstract EstructuraVO getEstructuraVO() throws AdaptadorException;
}
