package org.ingenia.negocio.igestor;

import java.util.List;

import javax.ejb.Remote;

import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.ActividadVO;
import org.ingenia.comunes.vo.MovimientoVO;

@Remote
public interface IGestorMovimientosRemote {

	MovimientoVO crearMovimiento(MovimientoVO movimientoVO) throws AdaptadorException;

	void modificarMovimiento(MovimientoVO movimientoVO) throws AdaptadorException;

	MovimientoVO consultarMovimientoVO(int idMovimiento) throws AdaptadorException;

	List<MovimientoVO> consultarMovimientos(ActividadVO actividadVO) throws AdaptadorException;
}
