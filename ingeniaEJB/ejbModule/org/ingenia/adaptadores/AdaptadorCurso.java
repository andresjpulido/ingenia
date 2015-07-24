package org.ingenia.adaptadores;

import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.CursoVO;
import org.ingenia.negocio.entidades.Curso;


public class AdaptadorCurso extends IAdaptadorCurso {

	public AdaptadorCurso(Curso curso) {
		this.curso = curso;
	}

	public AdaptadorCurso(CursoVO cursoVO) {
		this.cursoVO = cursoVO;
	}

	@Override
	public Curso getCurso() throws AdaptadorException {
		Curso curso = null;
		if (cursoVO == null)
			return null;
		curso = new Curso();

        curso.setIdcurso(cursoVO.getIdcurso());        
        AdaptadorUsuario adaptador = new AdaptadorUsuario(cursoVO.getProfesor());
        curso.setUsuario(adaptador.getUsuario());
		curso.setNombre(cursoVO.getNombre());
		curso.setEstado(cursoVO.getEstado());
		curso.setDescripcion(cursoVO.getDescripcion());
		curso.setActivo(cursoVO.getActivo());
		curso.setLimiteActividades(cursoVO.getLimite_actividades());
		return curso;
	}

	@Override
	public CursoVO getCursoVO() throws AdaptadorException {
		CursoVO cursoVO = null;

		if (curso == null)
			return null;
		
		cursoVO = new CursoVO();
		cursoVO.setIdcurso(curso.getIdcurso());
		cursoVO.setNombre(curso.getNombre());
		cursoVO.setEstado(curso.getEstado());
		cursoVO.setDescripcion(curso.getDescripcion());
		AdaptadorUsuario adaptador = new AdaptadorUsuario(curso.getUsuario());
		cursoVO.setProfesor(adaptador.getUsuarioVO());
		cursoVO.setActivo(curso.getActivo());
		cursoVO.setLimite_actividades(curso.getLimiteActividades());
		
	
		return cursoVO;
	}

}
