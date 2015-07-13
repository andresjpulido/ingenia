package org.ingenia.adaptadores;


import java.util.List;
import org.ingenia.comunes.vo.UsuarioVO;
import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.CursoVO;
import org.ingenia.comunes.vo.EstudianteVO;
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
		curso.setLimite_actividades(cursoVO.getLimite_actividades());
		return curso;
	}

	@Override
	public CursoVO getCursoVO() throws AdaptadorException {
		CursoVO cursoVO = null;
		List<EstudianteVO> listaEstudiantes;
		if (curso == null)
			return null;
		UsuarioVO profesor= new UsuarioVO();
		cursoVO = new CursoVO();

		cursoVO.setIdcurso(curso.getIdcurso());
		cursoVO.setNombre(curso.getNombre());
		cursoVO.setEstado(curso.getEstado());
		cursoVO.setDescripcion(curso.getDescripcion());
		  AdaptadorUsuario adaptador = new AdaptadorUsuario(curso.getUsuario());
		cursoVO.setProfesor(adaptador.getUsuarioVO());
		cursoVO.setActivo(curso.getActivo());
		cursoVO.setLimite_actividades(curso.getLimite_actividades());
		
		/*if (curso.getCursoEstudiantes() != null) {
			listaEstudiantes = new ArrayList<EstudianteVO>();
			for (CursoEstudiante estudiante : curso.getCursoEstudiantes()) {
								
				EstudianteVO temp=new EstudianteVO();
				temp.setIdestudiante(estudiante.getUsuario().getIdusuario());
				
				//temp.setCursos(estudiante.getCurso());

				listaEstudiantes.add(temp);
			}
			cursoVO.setEstudiantes(listaEstudiantes);
		}*/
	
		return cursoVO;
	}

}
