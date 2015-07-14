package org.ingenia.adaptadores;

import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.ArmaduraVO;
import org.ingenia.negocio.entidades.Armadura;




public abstract class IAdaptadorArmadura {
	
	Armadura armadura;
	ArmaduraVO armaduraVO;
	
	public abstract Armadura getArmadura() throws AdaptadorException;

	public abstract ArmaduraVO getArmaduraVO() throws AdaptadorException;

}
