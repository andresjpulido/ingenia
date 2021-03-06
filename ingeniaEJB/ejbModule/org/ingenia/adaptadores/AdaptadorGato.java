package org.ingenia.adaptadores;

import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.GatoVO;
import org.ingenia.negocio.entidades.Gato;

public class AdaptadorGato extends IAdaptadorGato{

	
	public AdaptadorGato(Gato gato) {
		this.gato = gato;
	}

	public AdaptadorGato(GatoVO gatoVO) {
		this.gatoVO = gatoVO;
	}
	
	@Override
	public Gato getGato() throws AdaptadorException {
		Gato gato = null;
		if (gatoVO == null)
			return null;
		gato = new Gato();
		AdaptadorArma AdaptadorArma = new AdaptadorArma(gatoVO.getArma());
		gato.setArma(AdaptadorArma.getArma());
		AdaptadorColor AdaptadorColor = new AdaptadorColor(gatoVO.getColor());
		gato.setColor(AdaptadorColor.getColor());
		AdaptadorTipoGato AdaptadorTipoGato= new AdaptadorTipoGato(gatoVO.getTipogato());
		gato.setTipogato(AdaptadorTipoGato.getTipogato());
		AdaptadorArmadura AdaptadorArmadura = new AdaptadorArmadura(gatoVO.getArmadura());
		gato.setArmadura(AdaptadorArmadura.getArmadura());
		gato.setOrden(gatoVO.getOrden());
		gato.setIdgato(gatoVO.getIdgato());
		gato.setArmado(gatoVO.getArmado());

		return gato;
	}

	@Override
	public GatoVO getGatoVO() throws AdaptadorException {
		GatoVO gatoVO = null;
		if (gato == null)
			return null;
		gatoVO = new GatoVO();
		AdaptadorArma AdaptadorArma = new AdaptadorArma(gato.getArma());
		gatoVO.setArma(AdaptadorArma.getArmaVO());
		AdaptadorColor AdaptadorColor = new AdaptadorColor(gato.getColor());
		gatoVO.setColor(AdaptadorColor.getColorVO());
		AdaptadorTipoGato AdaptadorTipoGato= new AdaptadorTipoGato(gato.getTipogato());
		gatoVO.setTipogato(AdaptadorTipoGato.getTipogatoVO());
		AdaptadorArmadura AdaptadorArmadura = new AdaptadorArmadura(gato.getArmadura());
		gatoVO.setArmadura(AdaptadorArmadura.getArmaduraVO());
		gatoVO.setIdgato(gato.getIdgato());
		gatoVO.setOrden(gato.getOrden());
		gatoVO.setArmado(gato.getArmado());
		return gatoVO;
	}

}
