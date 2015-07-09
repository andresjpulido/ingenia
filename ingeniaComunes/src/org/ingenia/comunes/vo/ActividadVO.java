package org.ingenia.comunes.vo;

import java.io.Serializable;
import java.util.List;

public class ActividadVO implements Serializable {

	private static final long serialVersionUID = -949796013955276126L;

	private int idactividad;

	private int activo;

	private String enunciado;
	
	private int publicado;
	
	private List<CursoVO> cursos;

	private List<RespuestaVO> respuestas;
	
	private String url_conocimiento;
	
	private int id_juego;
	
	private int limite_movimientos;
	
	private UsuarioVO profesor;

	public ActividadVO() {
	}

	public int getIdactividad() {
		return this.idactividad;
	}

	public void setIdactividad(int idactividad) {
		this.idactividad = idactividad;
	}

	public List<CursoVO> getCursos() {
		return this.cursos;
	}

	public void setCursos(List<CursoVO> cursos) {
		this.cursos = cursos;
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

	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public int getPublicado() {
		return publicado;
	}

	public void setPublicado(int publicado) {
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

	public int getLimite_movimientos() {
		return limite_movimientos;
	}

	public void setLimite_movimientos(int limite_movimientos) {
		this.limite_movimientos = limite_movimientos;
	}

	public UsuarioVO getProfesor() {
		return profesor;
	}

	public void setProfesor(UsuarioVO profesor) {
		this.profesor = profesor;
	}



}