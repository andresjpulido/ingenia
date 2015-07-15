package org.ingenia.negocio.igestor;

import java.util.List;

import javax.ejb.Local;

import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.ActividadVO;
import org.ingenia.comunes.vo.EstructuraVO;

@Local
public interface IGestorEstructurasLocal {

	EstructuraVO crearEstructura(EstructuraVO EstructuraVO) throws AdaptadorException;

	void modificarEstructura(EstructuraVO EstructuraVO) throws AdaptadorException;

	EstructuraVO consultarEstructuraVO(int idEstructura) throws AdaptadorException;

	List<EstructuraVO> consultarEstructuras(ActividadVO actividadVO) throws AdaptadorException;
}
