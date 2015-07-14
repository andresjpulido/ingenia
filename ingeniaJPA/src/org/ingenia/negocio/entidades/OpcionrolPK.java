package org.ingenia.negocio.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the opcionrol database table.
 * 
 */
@Embeddable
public class OpcionrolPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int idopcion;

	@Column(insertable=false, updatable=false)
	private int idRol;

	public OpcionrolPK() {
	}
	public int getIdopcion() {
		return this.idopcion;
	}
	public void setIdopcion(int idopcion) {
		this.idopcion = idopcion;
	}
	public int getIdRol() {
		return this.idRol;
	}
	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof OpcionrolPK)) {
			return false;
		}
		OpcionrolPK castOther = (OpcionrolPK)other;
		return 
			(this.idopcion == castOther.idopcion)
			&& (this.idRol == castOther.idRol);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idopcion;
		hash = hash * prime + this.idRol;
		
		return hash;
	}
}