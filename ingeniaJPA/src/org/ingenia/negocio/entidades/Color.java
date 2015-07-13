package org.ingenia.negocio.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the color database table.
 * 
 */
@Entity
@NamedQuery(name="Color.findAll", query="SELECT c FROM Color c")
public class Color implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idcolor;

	private String nombre;

	//bi-directional many-to-one association to Gato
	@OneToMany(mappedBy="colorBean")
	private List<Gato> gatos;

	public Color() {
	}

	public int getIdcolor() {
		return this.idcolor;
	}

	public void setIdcolor(int idcolor) {
		this.idcolor = idcolor;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Gato> getGatos() {
		return this.gatos;
	}

	public void setGatos(List<Gato> gatos) {
		this.gatos = gatos;
	}

	public Gato addGato(Gato gato) {
		getGatos().add(gato);
		gato.setColorBean(this);

		return gato;
	}

	public Gato removeGato(Gato gato) {
		getGatos().remove(gato);
		gato.setColorBean(null);

		return gato;
	}

}