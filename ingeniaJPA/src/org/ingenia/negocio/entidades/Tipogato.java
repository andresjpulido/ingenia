package org.ingenia.negocio.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipogato database table.
 * 
 */
@Entity
@NamedQuery(name="Tipogato.findAll", query="SELECT t FROM Tipogato t")
public class Tipogato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idtipogato;

	private String tipo;

	//bi-directional many-to-one association to Gato
	@OneToMany(mappedBy="tipogato")
	private List<Gato> gatos;

	public Tipogato() {
	}

	public int getIdtipogato() {
		return this.idtipogato;
	}

	public void setIdtipogato(int idtipogato) {
		this.idtipogato = idtipogato;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Gato> getGatos() {
		return this.gatos;
	}

	public void setGatos(List<Gato> gatos) {
		this.gatos = gatos;
	}

	public Gato addGato(Gato gato) {
		getGatos().add(gato);
		gato.setTipogato(this);

		return gato;
	}

	public Gato removeGato(Gato gato) {
		getGatos().remove(gato);
		gato.setTipogato(null);

		return gato;
	}

}