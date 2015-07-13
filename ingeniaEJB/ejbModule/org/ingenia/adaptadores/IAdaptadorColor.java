package org.ingenia.adaptadores;

import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.ColorVO;
import org.ingenia.negocio.entidades.Color;


public abstract class IAdaptadorColor {
	
	Color color;
	ColorVO colorVO;
	
	public abstract Color getColor() throws AdaptadorException;

	public abstract ColorVO getColorVO() throws AdaptadorException;

}
