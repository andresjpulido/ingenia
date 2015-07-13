package org.ingenia.comunes.vo;

import java.io.Serializable;

public class EstructuraVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idEstructura;

	private String nombre;

	public EstructuraVO() {
	}


	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public int getIdEstructura() {
		return idEstructura;
	}



	public void setIdEstructura(int idEstructura) {
		this.idEstructura = idEstructura;
	}

}