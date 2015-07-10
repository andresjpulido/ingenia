package org.ingenia.adaptadores;

import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.TipoGatoVO;
import org.ingenia.negocio.entidades.Tipogato;


public abstract class IAdaptadorTipoGato {
	
	Tipogato tipoGato;
	TipoGatoVO tipoGatoVO;
	
	public abstract Tipogato getTipogato() throws AdaptadorException;

	public abstract TipoGatoVO getTipogatoVO() throws AdaptadorException;

}
