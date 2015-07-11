package org.ingenia.adaptadores;

import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.GatoVO;
import org.ingenia.negocio.entidades.Gato;


public abstract class IAdaptadorGato {
	
	Gato gato;
	GatoVO gatoVO;
	
	public abstract Gato getGato() throws AdaptadorException;

	public abstract GatoVO getGatoVO() throws AdaptadorException;

}
