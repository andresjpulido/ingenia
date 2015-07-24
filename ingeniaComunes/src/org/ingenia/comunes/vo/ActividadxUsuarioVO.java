package org.ingenia.comunes.vo;

import java.io.Serializable;
import java.util.Date;

public class ActividadxUsuarioVO implements Serializable {

	private static final long serialVersionUID = -949796013955276126L;

	private int id;
	
	private int idActividad;
	
	private int idUsuario;
	
	private int idCurso;

	private ActividadVO actividadVO;

	private UsuarioVO estudiante;
	
	private CursoVO curso;
	
	private int numeroIntento;

	private Date fecha;
	
	private int puntos;
	
	private int numeroMovimientos;

	public ActividadxUsuarioVO() {
	}

	public int getIdActividad() {
		return idActividad;
	}

	public void setIdActividad(int idActividad) {
		this.idActividad = idActividad;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public ActividadVO getActividadVO() {
		return actividadVO;
	}

	public void setActividadVO(ActividadVO actividadVO) {
		this.actividadVO = actividadVO;
	}

	public UsuarioVO getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(UsuarioVO estudiante) {
		this.estudiante = estudiante;
	}

	public int getNumeroIntento() {
		return numeroIntento;
	}

	public void setNumeroIntento(int numeroIntento) {
		this.numeroIntento = numeroIntento;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumeroMovimientos() {
		return numeroMovimientos;
	}

	public void setNumeroMovimientos(int numeroMovimientos) {
		this.numeroMovimientos = numeroMovimientos;
	}

	public int getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}

	public CursoVO getCurso() {
		return curso;
	}

	public void setCurso(CursoVO curso) {
		this.curso = curso;
	}

}