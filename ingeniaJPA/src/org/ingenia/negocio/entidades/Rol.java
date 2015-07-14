package org.ingenia.negocio.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the rol database table.
 * 
 */
@Entity
@NamedQuery(name="Rol.findAll", query="SELECT r FROM Rol r")
public class Rol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idRol;

	private String descripcion;

	private String estado;

	private String nombre;

	//bi-directional many-to-one association to Opcionrol
	@OneToMany(mappedBy="rol")
	private List<Opcionrol> opcionrols;

	//bi-directional many-to-many association to Opcion
	@ManyToMany
	@JoinTable(
		name="opcionrol"
		, joinColumns={
			@JoinColumn(name="idRol")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idopcion")
			}
		)
	private List<Opcion> opcions;

	//bi-directional many-to-one association to Rolusuario
	@OneToMany(mappedBy="rol")
	private List<Rolusuario> rolusuarios;

	//bi-directional many-to-many association to Usuario
	@ManyToMany(mappedBy="rols")
	private List<Usuario> usuarios;

	public Rol() {
	}

	public int getIdRol() {
		return this.idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Opcionrol> getOpcionrols() {
		return this.opcionrols;
	}

	public void setOpcionrols(List<Opcionrol> opcionrols) {
		this.opcionrols = opcionrols;
	}

	public Opcionrol addOpcionrol(Opcionrol opcionrol) {
		getOpcionrols().add(opcionrol);
		opcionrol.setRol(this);

		return opcionrol;
	}

	public Opcionrol removeOpcionrol(Opcionrol opcionrol) {
		getOpcionrols().remove(opcionrol);
		opcionrol.setRol(null);

		return opcionrol;
	}

	public List<Opcion> getOpcions() {
		return this.opcions;
	}

	public void setOpcions(List<Opcion> opcions) {
		this.opcions = opcions;
	}

	public List<Rolusuario> getRolusuarios() {
		return this.rolusuarios;
	}

	public void setRolusuarios(List<Rolusuario> rolusuarios) {
		this.rolusuarios = rolusuarios;
	}

	public Rolusuario addRolusuario(Rolusuario rolusuario) {
		getRolusuarios().add(rolusuario);
		rolusuario.setRol(this);

		return rolusuario;
	}

	public Rolusuario removeRolusuario(Rolusuario rolusuario) {
		getRolusuarios().remove(rolusuario);
		rolusuario.setRol(null);

		return rolusuario;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}