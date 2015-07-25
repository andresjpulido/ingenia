package org.ingenia.adaptadores;

import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.MovimientoVO;
import org.ingenia.negocio.entidades.Movimiento;


public class AdaptadorMovimiento extends IAdaptadorMovimiento{

	
	public AdaptadorMovimiento(Movimiento movimiento) {
		this.movimiento = movimiento;
	}

	public AdaptadorMovimiento(MovimientoVO movimientoVO) {
		this.movimientoVO = movimientoVO;
	}
	
	@Override
	public Movimiento getMovimiento() throws AdaptadorException {
		Movimiento movimiento = null;
		if (this.movimientoVO == null)
			return null;
		
		movimiento = new Movimiento();
		
		movimiento.setIdmovimientos(movimiento.getIdmovimientos());
		movimiento.setNombreMovimiento(movimiento.getNombreMovimiento());
				
		return movimiento;
	}

	@Override
	public MovimientoVO getMovimientoVO() throws AdaptadorException {
		MovimientoVO MovimientoVO = null;
		if (this.movimiento == null)
			return null;
		movimientoVO=new MovimientoVO();
		movimientoVO.setIdMovimiento(movimiento.getIdmovimientos());
		movimientoVO.setNombre(movimiento.getNombreMovimiento());
		
		return movimientoVO;
	}

}
