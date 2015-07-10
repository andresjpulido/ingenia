package org.ingenia.negocio.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the actividadcurso database table.
 * 
 */
@Embeddable
public class ActividadcursoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int idcurso;

	@Column(insertable=false, updatable=false)
	private int idactividad;

	public ActividadcursoPK() {
	}
	public int getIdcurso() {
		return this.idcurso;
	}
	public void setIdcurso(int idcurso) {
		this.idcurso = idcurso;
	}
	public int getIdactividad() {
		return this.idactividad;
	}
	public void setIdactividad(int idactividad) {
		this.idactividad = idactividad;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ActividadcursoPK)) {
			return false;
		}
		ActividadcursoPK castOther = (ActividadcursoPK)other;
		return 
			(this.idcurso == castOther.idcurso)
			&& (this.idactividad == castOther.idactividad);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idcurso;
		hash = hash * prime + this.idactividad;
		
		return hash;
	}
}