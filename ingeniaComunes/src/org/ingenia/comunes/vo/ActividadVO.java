package org.ingenia.comunes.vo;

import java.io.Serializable;
import java.util.List;

public class ActividadVO implements Serializable {

	private static final long serialVersionUID = -949796013955276126L;

	private int idactividad;

	private byte activo;

	private String enunciado;
	
	private byte publicado;
	
	private List<CursoActividadVO> cursoActividads;

	private List<RespuestaVO> respuestas;
	
	private String url_conocimiento;
	
	private int id_juego;

	public ActividadVO() {
	}

	public int getIdactividad() {
		return this.idactividad;
	}

	public void setIdactividad(int idactividad) {
		this.idactividad = idactividad;
	}

	public List<CursoActividadVO> getCursoActividads() {
		return this.cursoActividads;
	}

	public void setCursoActividads(List<CursoActividadVO> cursoActividads) {
		this.cursoActividads = cursoActividads;
	}

	public CursoActividadVO addCursoActividad(CursoActividadVO cursoActividad) {
		getCursoActividads().add(cursoActividad);
		cursoActividad.setActividad(this);

		return cursoActividad;
	}

	public CursoActividadVO removeCursoActividad(CursoActividadVO cursoActividad) {
		getCursoActividads().remove(cursoActividad);
		cursoActividad.setActividad(null);

		return cursoActividad;
	}

	public List<RespuestaVO> getRespuestas() {
		return this.respuestas;
	}

	public void setRespuestas(List<RespuestaVO> respuestas) {
		this.respuestas = respuestas;
	}

	public RespuestaVO addRespuesta(RespuestaVO respuesta) {
		getRespuestas().add(respuesta);
		respuesta.setActividad(this);

		return respuesta;
	}

	public RespuestaVO removeRespuesta(RespuestaVO respuesta) {
		getRespuestas().remove(respuesta);
		respuesta.setActividad(null);

		return respuesta;
	}

	public byte getActivo() {
		return activo;
	}

	public void setActivo(byte activo) {
		this.activo = activo;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public byte getPublicado() {
		return publicado;
	}

	public void setPublicado(byte publicado) {
		this.publicado = publicado;
	}

	public String getUrl_conocimiento() {
		return url_conocimiento;
	}

	public void setUrl_conocimiento(String url_conocimiento) {
		this.url_conocimiento = url_conocimiento;
	}

	public int getId_Juego() {
		return id_juego;
	}

	public void setId_juego(int id_tipo_actividad) {
		this.id_juego = id_tipo_actividad;
	}

}