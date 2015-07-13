package org.ingenia.negocio.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


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

	private String color;

	private String defensa;

	private String orden;

	//bi-directional many-to-one association to Arma
	@ManyToOne
	private Arma arma;

	//bi-directional many-to-one association to Color
	@ManyToOne
	@JoinColumn(name="color_idcolor")
	private Color colorBean;

	//bi-directional many-to-one association to Tipogato
	@ManyToOne
	@JoinColumn(name="tipo_gato_idtipo_gato")
	private Tipogato tipogato;

	//bi-directional many-to-many association to Actividad
	@ManyToMany
	@JoinTable(
		name="gatoactividad"
		, joinColumns={
			@JoinColumn(name="idgato")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idactividad")
			}
		)
	private List<Actividad> actividads;

	public Gato() {
	}

	public int getIdgato() {
		return this.idgato;
	}

	public void setIdgato(int idgato) {
		this.idgato = idgato;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDefensa() {
		return this.defensa;
	}

	public void setDefensa(String defensa) {
		this.defensa = defensa;
	}

	public String getOrden() {
		return this.orden;
	}

	public void setOrden(String orden) {
		this.orden = orden;
	}

	public Arma getArma() {
		return this.arma;
	}

	public void setArma(Arma arma) {
		this.arma = arma;
	}

	public Color getColorBean() {
		return this.colorBean;
	}

	public void setColorBean(Color colorBean) {
		this.colorBean = colorBean;
	}

	public Tipogato getTipogato() {
		return this.tipogato;
	}

	public void setTipogato(Tipogato tipogato) {
		this.tipogato = tipogato;
	}

	public List<Actividad> getActividads() {
		return this.actividads;
	}

	public void setActividads(List<Actividad> actividads) {
		this.actividads = actividads;
	}

}