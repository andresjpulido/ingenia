package org.ingenia.adaptadores;

import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.ArmaVO;
import org.ingenia.negocio.entidades.Arma;


public class AdaptadorArma extends IAdaptadorArma{

	
	public AdaptadorArma(Arma arma) {
		this.arma = arma;
	}

	public AdaptadorArma(ArmaVO armaVO) {
		this.armaVO = armaVO;
	}
	
	@Override
	public Arma getArma() throws AdaptadorException {
		Arma arma = null;
		if (armaVO == null)
			return null;
		arma = new Arma();
		arma.setIdarma(armaVO.getIdarma());
		arma.setNombreArma(armaVO.getNombre());
		return arma;
	}

	@Override
	public ArmaVO getArmaVO() throws AdaptadorException {
		ArmaVO armaVO = null;
		if (arma == null)
			return null;
		armaVO = new ArmaVO();
		armaVO.setIdarma(arma.getIdarma());
		armaVO.setNombre(arma.getNombreArma());
		return armaVO;
	}

}
