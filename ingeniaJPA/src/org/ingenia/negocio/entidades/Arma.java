package org.ingenia.negocio.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the arma database table.
 * 
 */
@Entity
@NamedQuery(name="Arma.findAll", query="SELECT a FROM Arma a")
public class Arma implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idarma;

	@Column(name="nombre_arma")
	private String nombreArma;

	@Column(name="puntaje")
	private int puntaje;
	
	//bi-directional many-to-one association to Gato
	@OneToMany(mappedBy="arma")
	private List<Gato> gatos;

	public Arma() {
	}

	public int getIdarma() {
		return this.idarma;
	}

	public void setIdarma(int idarma) {
		this.idarma = idarma;
	}

	public String getNombreArma() {
		return this.nombreArma;
	}

	public void setNombreArma(String nombreArma) {
		this.nombreArma = nombreArma;
	}

	public List<Gato> getGatos() {
		return this.gatos;
	}

	public void setGatos(List<Gato> gatos) {
		this.gatos = gatos;
	}

	public Gato addGato(Gato gato) {
		getGatos().add(gato);
		gato.setArma(this);

		return gato;
	}

	public Gato removeGato(Gato gato) {
		getGatos().remove(gato);
		gato.setArma(null);

		return gato;
	}

}