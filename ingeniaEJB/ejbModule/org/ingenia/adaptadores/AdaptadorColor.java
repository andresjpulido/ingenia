package org.ingenia.adaptadores;

import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.ColorVO;
import org.ingenia.negocio.entidades.Color;


public class AdaptadorColor extends IAdaptadorColor{

	
	public AdaptadorColor(Color color) {
		this.color = color;
	}

	public AdaptadorColor(ColorVO colorVO) {
		this.colorVO = colorVO;
	}
	
	@Override
	public Color getColor() throws AdaptadorException {
		Color color = null;
		if (colorVO == null)
			return null;
		color = new Color();
		color.setIdcolor(colorVO.getIdcolor());
		color.setNombre(colorVO.getNombre());
		return color;
	}

	@Override
	public ColorVO getColorVO() throws AdaptadorException {
		ColorVO colorVO = null;
		if (color == null)
			return null;
		colorVO = new ColorVO();
		colorVO.setIdcolor(color.getIdcolor());
		colorVO.setNombre(color.getNombre());
		return colorVO;
	}

}
