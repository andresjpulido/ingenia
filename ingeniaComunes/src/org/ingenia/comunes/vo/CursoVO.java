package org.ingenia.comunes.vo;

import java.io.Serializable;
import java.util.List;

public class CursoVO implements Serializable {

	private static final long serialVersionUID = -1313968244732289628L;

	private int idcurso;

	private List<EstudianteVO> estudiantes;
	private List<CursoActividadVO> cursoActividads;

	private int activo;

	private String descripcion;

	private String estado;

	private String nombre;
	
	private int limite_actividades;
	
	private UsuarioVO profesor;

	public CursoVO() {
	}

	public int getIdcurso() {
		return this.idcurso;
	}

	public void setIdcurso(int idcurso) {
		this.idcurso = idcurso;
	}

	public List<EstudianteVO> getEstudiantes() {
		return this.estudiantes;
	}

	public void setEstudiantes(List<EstudianteVO> estudiantes) {
		this.estudiantes = estudiantes;
	}

	public List<CursoActividadVO> getCursoActividads() {
		return this.cursoActividads;
	}

	public void setCursoActividads(List<CursoActividadVO> cursoActividads) {
		this.cursoActividads = cursoActividads;
	}

	public CursoActividadVO addCursoActividad(CursoActividadVO cursoActividad) {
		getCursoActividads().add(cursoActividad);
		cursoActividad.setCurso(this);

		return cursoActividad;
	}

	public CursoActividadVO removeCursoActividad(CursoActividadVO cursoActividad) {
		getCursoActividads().remove(cursoActividad);
		cursoActividad.setCurso(null);

		return cursoActividad;
	}

	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public UsuarioVO getProfesor() {
		return profesor;
	}

	public void setProfesor(UsuarioVO profesor) {
		this.profesor = profesor;
	}

	public int getLimite_actividades() {
		return limite_actividades;
	}

	public void setLimite_actividades(int limite_actividades) {
		this.limite_actividades = limite_actividades;
	}



}