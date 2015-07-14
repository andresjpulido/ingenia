package org.ingenia.negocio.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the opcionrol database table.
 * 
 */
@Entity
@NamedQuery(name="Opcionrol.findAll", query="SELECT o FROM Opcionrol o")
public class Opcionrol implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OpcionrolPK id;

	//bi-directional many-to-one association to Opcion
	@ManyToOne
	@JoinColumn(name="idopcion")
	private Opcion opcion;

	//bi-directional many-to-one association to Rol
	@ManyToOne
	@JoinColumn(name="idRol")
	private Rol rol;

	public Opcionrol() {
	}

	public OpcionrolPK getId() {
		return this.id;
	}

	public void setId(OpcionrolPK id) {
		this.id = id;
	}

	public Opcion getOpcion() {
		return this.opcion;
	}

	public void setOpcion(Opcion opcion) {
		this.opcion = opcion;
	}

	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

}