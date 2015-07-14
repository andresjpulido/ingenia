package org.ingenia.negocio.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the estructuras_activas database table.
 * 
 */
@Embeddable
public class EstructurasActivaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int idactividad;

	@Column(insertable=false, updatable=false)
	private int idestructura;

	public EstructurasActivaPK() {
	}
	public int getIdactividad() {
		return this.idactividad;
	}
	public void setIdactividad(int idactividad) {
		this.idactividad = idactividad;
	}
	public int getIdestructura() {
		return this.idestructura;
	}
	public void setIdestructura(int idestructura) {
		this.idestructura = idestructura;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EstructurasActivaPK)) {
			return false;
		}
		EstructurasActivaPK castOther = (EstructurasActivaPK)other;
		return 
			(this.idactividad == castOther.idactividad)
			&& (this.idestructura == castOther.idestructura);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idactividad;
		hash = hash * prime + this.idestructura;
		
		return hash;
	}
}