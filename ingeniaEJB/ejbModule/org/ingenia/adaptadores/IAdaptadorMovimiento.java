package org.ingenia.adaptadores;

import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.MovimientoVO;
import org.ingenia.negocio.entidades.Movimiento;


public abstract class IAdaptadorMovimiento {
	
	Movimiento movimiento;
	MovimientoVO movimientoVO;
	
	public abstract Movimiento getMovimiento() throws AdaptadorException;

	public abstract MovimientoVO getMovimientoVO() throws AdaptadorException;

}
