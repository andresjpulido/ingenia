package org.ingenia.comunes.vo;

import java.io.Serializable;

public class ArmaVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idarma;

	private String nombre;

	public ArmaVO() {
	}


	public int getIdarma() {
		return idarma;
	}



	public void setIdarma(int idarma) {
		this.idarma = idarma;
	}



	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}