package org.ingenia.comunes.vo;

import java.io.Serializable;

public class ArmaduraVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idarmadura;

	private String nombre;

	public ArmaduraVO() {
	}


	public int getIdarmadura() {
		return idarmadura;
	}



	public void setIdarmadura(int idarmadura) {
		this.idarmadura = idarmadura;
	}



	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}