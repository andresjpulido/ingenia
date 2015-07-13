package org.ingenia.negocio.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idusuario;

	private String alias;

	private String apellido;

	private String clave;

	private String correo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_ultimo_ingreso")
	private Date fechaUltimoIngreso;

	private String genero;

	private String nombre;

	//bi-directional many-to-one association to Actividadusuario
	@OneToMany(mappedBy="usuario")
	private List<Actividadusuario> actividadusuarios;

	//bi-directional many-to-one association to Curso
	@OneToMany(mappedBy="usuario")
	private List<Curso> cursos;

	//bi-directional many-to-one association to Estudiantecurso
	@OneToMany(mappedBy="usuario")
	private List<Estudiantecurso> estudiantecursos;

	//bi-directional many-to-one association to Mensaje
	@OneToMany(mappedBy="usuario1")
	private List<Mensaje> mensajes1;

	//bi-directional many-to-one association to Mensaje
	@OneToMany(mappedBy="usuario2")
	private List<Mensaje> mensajes2;

	//bi-directional many-to-many association to Rol
	@ManyToMany
	@JoinTable(
		name="rolusuario"
		, joinColumns={
			@JoinColumn(name="idusuario")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idRol")
			}
		)
	private List<Rol> rols;

	public Usuario() {
	}

	public int getIdusuario() {
		return this.idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaUltimoIngreso() {
		return this.fechaUltimoIngreso;
	}

	public void setFechaUltimoIngreso(Date fechaUltimoIngreso) {
		this.fechaUltimoIngreso = fechaUltimoIngreso;
	}

	public String getGenero() {
		return this.genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Actividadusuario> getActividadusuarios() {
		return this.actividadusuarios;
	}

	public void setActividadusuarios(List<Actividadusuario> actividadusuarios) {
		this.actividadusuarios = actividadusuarios;
	}

	public Actividadusuario addActividadusuario(Actividadusuario actividadusuario) {
		getActividadusuarios().add(actividadusuario);
		actividadusuario.setUsuario(this);

		return actividadusuario;
	}

	public Actividadusuario removeActividadusuario(Actividadusuario actividadusuario) {
		getActividadusuarios().remove(actividadusuario);
		actividadusuario.setUsuario(null);

		return actividadusuario;
	}

	public List<Curso> getCursos() {
		return this.cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public Curso addCurso(Curso curso) {
		getCursos().add(curso);
		curso.setUsuario(this);

		return curso;
	}

	public Curso removeCurso(Curso curso) {
		getCursos().remove(curso);
		curso.setUsuario(null);

		return curso;
	}

	public List<Estudiantecurso> getEstudiantecursos() {
		return this.estudiantecursos;
	}

	public void setEstudiantecursos(List<Estudiantecurso> estudiantecursos) {
		this.estudiantecursos = estudiantecursos;
	}

	public Estudiantecurso addEstudiantecurso(Estudiantecurso estudiantecurso) {
		getEstudiantecursos().add(estudiantecurso);
		estudiantecurso.setUsuario(this);

		return estudiantecurso;
	}

	public Estudiantecurso removeEstudiantecurso(Estudiantecurso estudiantecurso) {
		getEstudiantecursos().remove(estudiantecurso);
		estudiantecurso.setUsuario(null);

		return estudiantecurso;
	}

	public List<Mensaje> getMensajes1() {
		return this.mensajes1;
	}

	public void setMensajes1(List<Mensaje> mensajes1) {
		this.mensajes1 = mensajes1;
	}

	public Mensaje addMensajes1(Mensaje mensajes1) {
		getMensajes1().add(mensajes1);
		mensajes1.setUsuario1(this);

		return mensajes1;
	}

	public Mensaje removeMensajes1(Mensaje mensajes1) {
		getMensajes1().remove(mensajes1);
		mensajes1.setUsuario1(null);

		return mensajes1;
	}

	public List<Mensaje> getMensajes2() {
		return this.mensajes2;
	}

	public void setMensajes2(List<Mensaje> mensajes2) {
		this.mensajes2 = mensajes2;
	}

	public Mensaje addMensajes2(Mensaje mensajes2) {
		getMensajes2().add(mensajes2);
		mensajes2.setUsuario2(this);

		return mensajes2;
	}

	public Mensaje removeMensajes2(Mensaje mensajes2) {
		getMensajes2().remove(mensajes2);
		mensajes2.setUsuario2(null);

		return mensajes2;
	}

	public List<Rol> getRols() {
		return this.rols;
	}

	public void setRols(List<Rol> rols) {
		this.rols = rols;
	}

}