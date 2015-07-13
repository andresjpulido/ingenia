package org.ingenia.negocio.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the respuesta database table.
 * 
 */
@Entity
@NamedQuery(name="Respuesta.findAll", query="SELECT r FROM Respuesta r")
public class Respuesta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idrespuesta;

	private int orden;

	private String valor;

	private byte verdadero;

	//bi-directional many-to-one association to Actividad
	@ManyToOne
	@JoinColumn(name="idactividad")
	private Actividad actividad;

	public Respuesta() {
	}

	public int getIdrespuesta() {
		return this.idrespuesta;
	}

	public void setIdrespuesta(int idrespuesta) {
		this.idrespuesta = idrespuesta;
	}

	public int getOrden() {
		return this.orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public byte getVerdadero() {
		return this.verdadero;
	}

	public void setVerdadero(byte verdadero) {
		this.verdadero = verdadero;
	}

	public Actividad getActividad() {
		return this.actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

}