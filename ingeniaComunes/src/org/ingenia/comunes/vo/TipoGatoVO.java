package org.ingenia.comunes.vo;

import java.io.Serializable;

public class TipoGatoVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idTipoGato;

	private String nombre;

	public TipoGatoVO() {
	}



	public int getIdTipoGato() {
		return idTipoGato;
	}



	public void setIdTipoGato(int idTipoGato) {
		this.idTipoGato = idTipoGato;
	}



	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}