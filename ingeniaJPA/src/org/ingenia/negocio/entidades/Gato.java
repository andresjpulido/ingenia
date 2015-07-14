package org.ingenia.negocio.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the gato database table.
 * 
 */
@Entity
@NamedQuery(name="Gato.findAll", query="SELECT g FROM Gato g")
public class Gato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idgato;

	private int orden;

	//bi-directional many-to-one association to Actividad
	@ManyToOne
	@JoinColumn(name="idactividad")
	private Actividad actividad;

	//bi-directional many-to-one association to Arma
	@ManyToOne
	@JoinColumn(name="idarma")
	private Arma arma;

	//bi-directional many-to-one association to Color
	@ManyToOne
	@JoinColumn(name="idcolor")
	private Color color;

	//bi-directional many-to-one association to Tipogato
	@ManyToOne
	@JoinColumn(name="idtipo_gato")
	private Tipogato tipogato;

	//bi-directional many-to-one association to Armadura
	@ManyToOne
	@JoinColumn(name="idarmadura")
	private Armadura armadura;

	public Gato() {
	}

	public int getIdgato() {
		return this.idgato;
	}

	public void setIdgato(int idgato) {
		this.idgato = idgato;
	}

	public int getOrden() {
		return this.orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public Actividad getActividad() {
		return this.actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public Arma getArma() {
		return this.arma;
	}

	public void setArma(Arma arma) {
		this.arma = arma;
	}

	public Color getColor() {
		return this.color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Tipogato getTipogato() {
		return this.tipogato;
	}

	public void setTipogato(Tipogato tipogato) {
		this.tipogato = tipogato;
	}

	public Armadura getArmadura() {
		return this.armadura;
	}

	public void setArmadura(Armadura armadura) {
		this.armadura = armadura;
	}

}