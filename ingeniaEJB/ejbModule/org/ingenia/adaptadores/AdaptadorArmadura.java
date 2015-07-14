package org.ingenia.adaptadores;

import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.ArmaduraVO;
import org.ingenia.negocio.entidades.Armadura;


public class AdaptadorArmadura extends IAdaptadorArmadura{

	
	public AdaptadorArmadura(Armadura armadura) {
		this.armadura = armadura;
	}

	public AdaptadorArmadura(ArmaduraVO armaduraVO) {
		this.armaduraVO = armaduraVO;
	}
	
	@Override
	public Armadura getArmadura() throws AdaptadorException {
		Armadura armadura = null;
		if (armaduraVO == null)
			return null;
		armadura = new Armadura();
		armadura.setIdarmadura(armaduraVO.getIdarmadura());
		armadura.setTipoArmadura(armaduraVO.getNombre());
		return armadura;
	}

	@Override
	public ArmaduraVO getArmaduraVO() throws AdaptadorException {
		ArmaduraVO armaduraVO = null;
		if (armadura == null)
			return null;
		armaduraVO = new ArmaduraVO();
		armaduraVO.setIdarmadura(armadura.getIdarmadura());
		armaduraVO.setNombre(armadura.getTipoArmadura());
		return armaduraVO;
	}

}
