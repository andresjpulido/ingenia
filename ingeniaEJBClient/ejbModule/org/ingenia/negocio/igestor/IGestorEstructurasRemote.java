package org.ingenia.negocio.igestor;

import java.util.List;

import javax.ejb.Remote;

import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.ActividadVO;
import org.ingenia.comunes.vo.EstructuraVO;

@Remote
public interface IGestorEstructurasRemote {

	EstructuraVO crearEstructura(EstructuraVO EstructuraVO) throws AdaptadorException;

	void modificarEstructura(EstructuraVO EstructuraVO) throws AdaptadorException;

	EstructuraVO consultarEstructuraVO(int idEstructura) throws AdaptadorException;

	List<EstructuraVO> consultarEstructuras(ActividadVO actividadVO) throws AdaptadorException;
}
