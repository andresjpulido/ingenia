package org.ingenia.negocio.igestor;

import java.util.List;

import javax.ejb.Local;

import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.ActividadVO;
import org.ingenia.comunes.vo.CursoActividadVO;
import org.ingenia.comunes.vo.JuegoVO;

@Local
public interface IGestorActividadesLocal {
	public void crearActividadCurso(CursoActividadVO cursoActividadVO)
			throws AdaptadorException;

	public void modificarActividadVO(ActividadVO actividadVO)
			throws AdaptadorException;

	public void eliminarActividadVO(ActividadVO actividadVO)
			throws AdaptadorException;

	public List<ActividadVO> consultarActividadDisponibles()
			throws AdaptadorException;
	
	public ActividadVO consultarActividadVO(ActividadVO actividadVO)
			throws AdaptadorException;	
	public List<JuegoVO> consultarJuegosDisponibles() 
			throws AdaptadorException;	
	
	public List<ActividadVO> consultarActividadesProfesor(int idprofesor) 
			throws AdaptadorException;

	public void modificarActividadCurso(CursoActividadVO cursoActividadVO) throws AdaptadorException;

	public void crearActividad(ActividadVO actividadVO) throws AdaptadorException;

	public void modificarActividad(ActividadVO actividadVO)throws AdaptadorException;
}
