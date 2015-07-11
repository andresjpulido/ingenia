package org.ingenia.adaptadores;

import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.ArmaVO;
import org.ingenia.negocio.entidades.Arma;



public abstract class IAdaptadorArma {
	
	Arma arma;
	ArmaVO armaVO;
	
	public abstract Arma getArma() throws AdaptadorException;

	public abstract ArmaVO getArmaVO() throws AdaptadorException;

}
