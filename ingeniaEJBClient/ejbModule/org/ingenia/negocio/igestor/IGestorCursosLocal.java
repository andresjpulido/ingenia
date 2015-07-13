package org.ingenia.negocio.igestor;

import java.util.List;

import javax.ejb.Local;

import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.ActividadVO;
import org.ingenia.comunes.vo.CursoActividadVO;
import org.ingenia.comunes.vo.CursoVO;
import org.ingenia.comunes.vo.UsuarioVO;

@Local
public interface IGestorCursosLocal {

	public List<CursoVO> consultarCursosProfesor(int idprofesor) throws AdaptadorException;

	public List<CursoVO> consultarCursosPorNombre(CursoVO cursoVO)
			throws AdaptadorException;

	public CursoVO consultarCursoVO(CursoVO cursoVO) throws AdaptadorException;

	public void modificarCursoVO(CursoVO cursoVO) throws AdaptadorException;

	public void eliminarCursoVO(CursoVO cursoVO) throws AdaptadorException;

	public void crearCursoVO(CursoVO cursoVO) throws AdaptadorException;
	
	public List<ActividadVO> consultarActividadesDisponibles(CursoVO cursoVO) throws AdaptadorException;

	public void asociarActividad(CursoActividadVO cursoActividadVO) throws AdaptadorException;
}
