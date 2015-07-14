package org.ingenia.adaptadores;

import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.EstructuraVO;
import org.ingenia.negocio.entidades.Estructura;

public class AdaptadorEstructura extends IAdaptadorEstructura{

	
	public AdaptadorEstructura(Estructura Estructura) {
		this.Estructura = Estructura;
	}

	public AdaptadorEstructura(EstructuraVO EstructuraVO) {
		this.EstructuraVO = EstructuraVO;
	}
	
	@Override
	public Estructura getEstructura() throws AdaptadorException {
		Estructura Estructura = null;
		if (EstructuraVO == null)
			return null;
		Estructura = new Estructura();
		Estructura.setIdestructura(EstructuraVO.getIdEstructura());
		Estructura.setNombre(EstructuraVO.getNombre());
		return Estructura;
	}

	@Override
	public EstructuraVO getEstructuraVO() throws AdaptadorException {
		EstructuraVO EstructuraVO = null;
		if (Estructura == null)
			return null;
		EstructuraVO = new EstructuraVO();
		EstructuraVO.setIdEstructura(Estructura.getIdestructura());
		EstructuraVO.setNombre(Estructura.getNombre());
		return EstructuraVO;
	}

}
