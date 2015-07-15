package org.ingenia.negocio.igestor;

import java.util.List;

import javax.ejb.Local;

import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.ActividadVO;
import org.ingenia.comunes.vo.MovimientoVO;

@Local
public interface IGestorMovimientosLocal {

	MovimientoVO crearMovimiento(MovimientoVO movimientoVO) throws AdaptadorException;

	void modificarMovimiento(MovimientoVO movimientoVO) throws AdaptadorException;

	MovimientoVO consultarMovimientoVO(int idMovimiento) throws AdaptadorException;

	List<MovimientoVO> consultarMovimientos(ActividadVO actividadVO) throws AdaptadorException;
}
