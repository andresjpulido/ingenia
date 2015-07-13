package org.ingenia.adaptadores;

import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.TipoGatoVO;
import org.ingenia.negocio.entidades.Tipogato;

public class AdaptadorTipoGato extends IAdaptadorTipoGato{

	
	public AdaptadorTipoGato(Tipogato tipoGato) {
		this.tipoGato = tipoGato;
	}

	public AdaptadorTipoGato(TipoGatoVO tipoGatoVO) {
		this.tipoGatoVO = tipoGatoVO;
	}
	
	@Override
	public Tipogato getTipogato() throws AdaptadorException {
		Tipogato tipoGato = null;
		if (tipoGatoVO == null)
			return null;
		tipoGato = new Tipogato();
		tipoGato.setIdtipogato(tipoGatoVO.getIdTipoGato());
		//tipoGato.setNombre(tipoGatoVO.getNombre());
		return tipoGato;
	}

	@Override
	public TipoGatoVO getTipogatoVO() throws AdaptadorException {
		TipoGatoVO tipoGatoVo = null;
		if (tipoGato == null)
			return null;
		tipoGatoVo = new TipoGatoVO();
		tipoGatoVo.setIdTipoGato(tipoGato.getIdtipogato());
		//tipoGatoVo.setNombre(tipoGato.getNombre());
		return tipoGatoVo;
	}

}
