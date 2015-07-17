package org.ingenia.comunes.vo;

import java.io.Serializable;

public class MovimientoVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idMovimiento;

	private String nombre;

	public MovimientoVO() {
	}


	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public int getIdMovimiento() {
		return idMovimiento;
	}



	public void setIdMovimiento(int idMovimiento) {
		this.idMovimiento = idMovimiento;
	}

}