package org.ingenia.negocio.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the estudiantecurso database table.
 * 
 */
@Entity
@NamedQuery(name="Estudiantecurso.findAll", query="SELECT e FROM Estudiantecurso e")
public class Estudiantecurso implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EstudiantecursoPK id;

	private int puntaje;

	//bi-directional many-to-one association to Curso
	@ManyToOne
	@JoinColumn(name="idcurso")
	private Curso curso;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="idusuario")
	private Usuario usuario;

	public Estudiantecurso() {
	}

	public EstudiantecursoPK getId() {
		return this.id;
	}

	public void setId(EstudiantecursoPK id) {
		this.id = id;
	}

	public int getPuntaje() {
		return this.puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public Curso getCurso() {
		return this.curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}