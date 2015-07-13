package org.ingenia.comunes.vo;

import java.io.Serializable;

public class ColorVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idcolor;

	private String nombre;

	public ColorVO() {
	}

	public int getIdcolor() {
		return idcolor;
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

}