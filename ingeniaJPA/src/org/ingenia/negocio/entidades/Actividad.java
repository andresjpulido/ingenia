package org.ingenia.negocio.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the actividad database table.
 * 
 */
@Entity
@NamedQuery(name="Actividad.findAll", query="SELECT a FROM Actividad a")
public class Actividad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idactividad;

	private int activo;

	private String enunciado;

	@Column(name="limite_movimientos")
	private int limiteMovimientos;


	private int publicado;

	@Column(name="`url_texto_ensenanza`")
	private String url_texto_ensenanza;

	//bi-directional many-to-many association to Curso
	@ManyToMany
	@JoinTable(
		name="actividadcurso"
		, joinColumns={
			@JoinColumn(name="idactividad")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idcurso")
			}
		)
	private List<Curso> cursos;

	//bi-directional many-to-one association to Juego
	@ManyToOne
	@JoinColumn(name="idtipo_juego")
	private Juego juego;

	//bi-directional many-to-one association to Actividadcurso
	@OneToMany(mappedBy="actividad")
	private List<Actividadcurso> actividadcursos;

	//bi-directional many-to-one association to Actividadusuario
	@OneToMany(mappedBy="actividad")
	private List<Actividadusuario> actividadusuarios;

	//bi-directional many-to-many association to Estructura
	@ManyToMany(mappedBy="actividads")
	private List<Estructura> estructuras;

	//bi-directional many-to-many association to Gato
	@ManyToMany(mappedBy="actividads")
	private List<Gato> gatos;

	//bi-directional many-to-one association to Respuesta
	@OneToMany(mappedBy="actividad")
	private List<Respuesta> respuestas;

	public Actividad() {
	}

	public int getIdactividad() {
		return this.idactividad;
	}

	public void setIdactividad(int idactividad) {
		this.idactividad = idactividad;
	}

	public int getActivo() {
		return this.activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}

	public String getEnunciado() {
		return this.enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public int getLimiteMovimientos() {
		return this.limiteMovimientos;
	}

	public void setLimiteMovimientos(int limiteMovimientos) {
		this.limiteMovimientos = limiteMovimientos;
	}

	public int getPublicado() {
		return this.publicado;
	}

	public void setPublicado(int publicado) {
		this.publicado = publicado;
	}

	public String getUrl_texto_ensenanza() {
		return this.url_texto_ensenanza;
	}

	public void setUrl_texto_ensenanza(String url_texto_ensenanza) {
		this.url_texto_ensenanza = url_texto_ensenanza;
	}

	public List<Curso> getCursos() {
		return this.cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public Juego getJuego() {
		return this.juego;
	}

	public void setJuego(Juego juego) {
		this.juego = juego;
	}

	public List<Actividadcurso> getActividadcursos() {
		return this.actividadcursos;
	}

	public void setActividadcursos(List<Actividadcurso> actividadcursos) {
		this.actividadcursos = actividadcursos;
	}

	public Actividadcurso addActividadcurso(Actividadcurso actividadcurso) {
		getActividadcursos().add(actividadcurso);
		actividadcurso.setActividad(this);

		return actividadcurso;
	}

	public Actividadcurso removeActividadcurso(Actividadcurso actividadcurso) {
		getActividadcursos().remove(actividadcurso);
		actividadcurso.setActividad(null);

		return actividadcurso;
	}

	public List<Actividadusuario> getActividadusuarios() {
		return this.actividadusuarios;
	}

	public void setActividadusuarios(List<Actividadusuario> actividadusuarios) {
		this.actividadusuarios = actividadusuarios;
	}

	public Actividadusuario addActividadusuario(Actividadusuario actividadusuario) {
		getActividadusuarios().add(actividadusuario);
		actividadusuario.setActividad(this);

		return actividadusuario;
	}

	public Actividadusuario removeActividadusuario(Actividadusuario actividadusuario) {
		getActividadusuarios().remove(actividadusuario);
		actividadusuario.setActividad(null);

		return actividadusuario;
	}

	public List<Estructura> getEstructuras() {
		return this.estructuras;
	}

	public void setEstructuras(List<Estructura> estructuras) {
		this.estructuras = estructuras;
	}

	public List<Gato> getGatos() {
		return this.gatos;
	}

	public void setGatos(List<Gato> gatos) {
		this.gatos = gatos;
	}

	public List<Respuesta> getRespuestas() {
		return this.respuestas;
	}

	public void setRespuestas(List<Respuesta> respuestas) {
		this.respuestas = respuestas;
	}

	public Respuesta addRespuesta(Respuesta respuesta) {
		getRespuestas().add(respuesta);
		respuesta.setActividad(this);

		return respuesta;
	}

	public Respuesta removeRespuesta(Respuesta respuesta) {
		getRespuestas().remove(respuesta);
		respuesta.setActividad(null);

		return respuesta;
	}

}