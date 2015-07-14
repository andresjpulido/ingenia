package org.ingenia.negocio.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the rolusuario database table.
 * 
 */
@Entity
@NamedQuery(name="Rolusuario.findAll", query="SELECT r FROM Rolusuario r")
public class Rolusuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RolusuarioPK id;

	//bi-directional many-to-one association to Rol
	@ManyToOne
	@JoinColumn(name="idRol")
	private Rol rol;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="idusuario")
	private Usuario usuario;

	public Rolusuario() {
	}

	public RolusuarioPK getId() {
		return this.id;
	}

	public void setId(RolusuarioPK id) {
		this.id = id;
	}

	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}