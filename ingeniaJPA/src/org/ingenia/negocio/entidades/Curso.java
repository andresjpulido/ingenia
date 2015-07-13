package org.ingenia.negocio.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the curso database table.
 * 
 */
@Entity
@NamedQuery(name="Curso.findAll", query="SELECT c FROM Curso c")
public class Curso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idcurso;

	private int activo;

	private String descripcion;

	private String estado;

	@Column(name="limite_actividades")
	private int limiteActividades;

	private String nombre;

	//bi-directional many-to-one association to Actividadcurso
	@OneToMany(mappedBy="curso")
	private List<Actividadcurso> actividadcursos;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="idprofesor")
	private Usuario usuario;

	//bi-directional many-to-one association to Estudiantecurso
	@OneToMany(mappedBy="curso")
	private List<Estudiantecurso> estudiantecursos;

	public Curso() {
	}

	public int getIdcurso() {
		return this.idcurso;
	}

	public void setIdcurso(int idcurso) {
		this.idcurso = idcurso;
	}

	public int getActivo() {
		return this.activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getLimiteActividades() {
		return this.limiteActividades;
	}

	public void setLimiteActividades(int limiteActividades) {
		this.limiteActividades = limiteActividades;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Actividadcurso> getActividadcursos() {
		return this.actividadcursos;
	}

	public void setActividadcursos(List<Actividadcurso> actividadcursos) {
		this.actividadcursos = actividadcursos;
	}

	public Actividadcurso addActividadcurso(Actividadcurso actividadcurso) {
		getActividadcursos().add(actividadcurso);
		actividadcurso.setCurso(this);

		return actividadcurso;
	}

	public Actividadcurso removeActividadcurso(Actividadcurso actividadcurso) {
		getActividadcursos().remove(actividadcurso);
		actividadcurso.setCurso(null);

		return actividadcurso;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Estudiantecurso> getEstudiantecursos() {
		return this.estudiantecursos;
	}

	public void setEstudiantecursos(List<Estudiantecurso> estudiantecursos) {
		this.estudiantecursos = estudiantecursos;
	}

	public Estudiantecurso addEstudiantecurso(Estudiantecurso estudiantecurso) {
		getEstudiantecursos().add(estudiantecurso);
		estudiantecurso.setCurso(this);

		return estudiantecurso;
	}

	public Estudiantecurso removeEstudiantecurso(Estudiantecurso estudiantecurso) {
		getEstudiantecursos().remove(estudiantecurso);
		estudiantecurso.setCurso(null);

		return estudiantecurso;
	}

}