package org.ingenia.negocio.igestor;

import java.util.List;

import javax.ejb.Local;

import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.ActividadVO;
import org.ingenia.comunes.vo.ActividadxUsuarioVO;
import org.ingenia.comunes.vo.CursoActividadVO;
import org.ingenia.comunes.vo.CursoVO;
import org.ingenia.comunes.vo.EstudianteVO;
import org.ingenia.comunes.vo.UsuarioVO;


@Local
public interface IGestorCursosLocal {

	public List<CursoVO> consultarCursosProfesor(UsuarioVO profesor) throws AdaptadorException;

	public List<CursoVO> consultarCursosPorNombre(CursoVO cursoVO)
			throws AdaptadorException;

	public CursoVO consultarCursoVO(CursoVO cursoVO) throws AdaptadorException;

	public void modificarCursoVO(CursoVO cursoVO) throws AdaptadorException;

	public void eliminarCursoVO(CursoVO cursoVO) throws AdaptadorException;

	public void crearCursoVO(CursoVO cursoVO) throws AdaptadorException;
	
	public List<ActividadVO> consultarActividadesDisponibles(CursoVO cursoVO,UsuarioVO profesorVO) throws AdaptadorException;

	public void asociarActividad(CursoActividadVO cursoActividadVO) throws AdaptadorException;

	public List<ActividadxUsuarioVO> consultarActividadesCursoEstudiante(CursoVO cursoVO,EstudianteVO estudianteVO) throws AdaptadorException;

	public List<CursoVO> consultarCursosEstudiante(UsuarioVO usuarioVO) throws AdaptadorException;

	public List<CursoVO> consultarCursosDisponibleEstudiante(List<CursoVO> listaCursosest) throws AdaptadorException;

	public void inscribirCurso(UsuarioVO usuarioVO,CursoVO cursoVO)throws AdaptadorException;

	public List<CursoVO> consultarCursosEstudiantePorNombre(CursoVO cursoVO,UsuarioVO usuarioVO)throws AdaptadorException;

	public List<CursoVO> consultarCursosDisponiblesEstudiantePorNombre(CursoVO cursoVO,UsuarioVO usuarioVO)throws AdaptadorException;





}
