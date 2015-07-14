package org.ingenia.negocio.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the opcion database table.
 * 
 */
@Entity
@NamedQuery(name="Opcion.findAll", query="SELECT o FROM Opcion o")
public class Opcion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idopcion;

	private String codigo;

	private String descripcion;

	private String nombre;

	//bi-directional many-to-one association to Opcionrol
	@OneToMany(mappedBy="opcion")
	private List<Opcionrol> opcionrols;

	//bi-directional many-to-many association to Rol
	@ManyToMany(mappedBy="opcions")
	private List<Rol> rols;

	public Opcion() {
	}

	public int getIdopcion() {
		return this.idopcion;
	}

	public void setIdopcion(int idopcion) {
		this.idopcion = idopcion;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
		opcionrol.setOpcion(this);

		return opcionrol;
	}

	public Opcionrol removeOpcionrol(Opcionrol opcionrol) {
		getOpcionrols().remove(opcionrol);
		opcionrol.setOpcion(null);

		return opcionrol;
	}

	public List<Rol> getRols() {
		return this.rols;
	}

	public void setRols(List<Rol> rols) {
		this.rols = rols;
	}

}