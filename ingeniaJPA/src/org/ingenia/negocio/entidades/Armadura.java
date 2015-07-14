package org.ingenia.negocio.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the armadura database table.
 * 
 */
@Entity
@NamedQuery(name="Armadura.findAll", query="SELECT a FROM Armadura a")
public class Armadura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idarmadura;

	@Column(name="tipo_armadura")
	private String tipoArmadura;

	//bi-directional many-to-one association to Gato
	@OneToMany(mappedBy="armadura")
	private List<Gato> gatos;

	public Armadura() {
	}

	public int getIdarmadura() {
		return this.idarmadura;
	}

	public void setIdarmadura(int idarmadura) {
		this.idarmadura = idarmadura;
	}

	public String getTipoArmadura() {
		return this.tipoArmadura;
	}

	public void setTipoArmadura(String tipoArmadura) {
		this.tipoArmadura = tipoArmadura;
	}

	public List<Gato> getGatos() {
		return this.gatos;
	}

	public void setGatos(List<Gato> gatos) {
		this.gatos = gatos;
	}

	public Gato addGato(Gato gato) {
		getGatos().add(gato);
		gato.setArmadura(this);

		return gato;
	}

	public Gato removeGato(Gato gato) {
		getGatos().remove(gato);
		gato.setArmadura(null);

		return gato;
	}

}