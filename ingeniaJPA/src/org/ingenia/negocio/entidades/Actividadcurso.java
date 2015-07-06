package org.ingenia.negocio.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the actividadcurso database table.
 * 
 */
@Entity
@NamedQuery(name="Actividadcurso.findAll", query="SELECT a FROM Actividadcurso a")
public class Actividadcurso implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ActividadcursoPK id;

	@Column(name="limite_actividades")
	private int limiteActividades;

	//bi-directional many-to-one association to Actividad
	@ManyToOne
	@JoinColumn(name="idactividad")
	private Actividad actividad;

	//bi-directional many-to-one association to Curso
	@ManyToOne
	@JoinColumn(name="idcurso")
	private Curso curso;

	public Actividadcurso() {
	}

	public ActividadcursoPK getId() {
		return this.id;
	}

	public void setId(ActividadcursoPK id) {
		this.id = id;
	}

	public int getLimiteActividades() {
		return this.limiteActividades;
	}

	public void setLimiteActividades(int limiteActividades) {
		this.limiteActividades = limiteActividades;
	}

	public Actividad getActividad() {
		return this.actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public Curso getCurso() {
		return this.curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

}