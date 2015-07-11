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

		gato.setDefensa(gatoVO.getDefensa());
		gato.setIdgato(gatoVO.getIdgato());

		return gato;
	}

	@Override
	public GatoVO getGatoVO() throws AdaptadorException {
		GatoVO gatoVO = null;
		if (gato == null)
			return null;
		gatoVO = new GatoVO();
		gatoVO.setDefensa(gato.getDefensa());
		gatoVO.setIdgato(gato.getIdgato());
		gatoVO.setOrden(gato.getOrden());
		gatoVO.setIdarma(gato.getArma().getIdarma());
		gatoVO.setIdcolor(gato.getColor().getIdcolor());
		gatoVO.setIdtipogato(gato.getTipogato().getIdtipogato());
		
		return gatoVO;
	}

}
