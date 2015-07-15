package org.ingenia.comunes.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ActividadxUsuarioVO implements Serializable {

	private static final long serialVersionUID = -949796013955276126L;

	private int idActividad;
	
	private int idUsuario;

	private ActividadVO actividadVO;

	private UsuarioVO estudiante;
	
	private int NumeroIntento;

	private Date Fecha;
	
	private int Puntos;

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
		return NumeroIntento;
	}

	public void setNumeroIntento(int numeroIntento) {
		NumeroIntento = numeroIntento;
	}

	public Date getFecha() {
		return Fecha;
	}

	public void setFecha(Date fecha) {
		Fecha = fecha;
	}

	public int getPuntos() {
		return Puntos;
	}

	public void setPuntos(int puntos) {
		Puntos = puntos;
	}

}