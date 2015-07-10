package org.ingenia.negocio.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the estudiantecurso database table.
 * 
 */
@Embeddable
public class EstudiantecursoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int idcurso;

	@Column(insertable=false, updatable=false)
	private int idusuario;

	public EstudiantecursoPK() {
	}
	public int getIdcurso() {
		return this.idcurso;
	}
	public void setIdcurso(int idcurso) {
		this.idcurso = idcurso;
	}
	public int getIdusuario() {
		return this.idusuario;
	}
	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EstudiantecursoPK)) {
			return false;
		}
		EstudiantecursoPK castOther = (EstudiantecursoPK)other;
		return 
			(this.idcurso == castOther.idcurso)
			&& (this.idusuario == castOther.idusuario);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idcurso;
		hash = hash * prime + this.idusuario;
		
		return hash;
	}
}