package org.ingenia.negocio.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the actividadusuario database table.
 * 
 */
@Embeddable
public class ActividadusuarioPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="usuario_idusuario", insertable=false, updatable=false)
	private int usuarioIdusuario;

	@Column(name="actividad_idactividad", insertable=false, updatable=false)
	private int actividadIdactividad;

	public ActividadusuarioPK() {
	}
	public int getUsuarioIdusuario() {
		return this.usuarioIdusuario;
	}
	public void setUsuarioIdusuario(int usuarioIdusuario) {
		this.usuarioIdusuario = usuarioIdusuario;
	}
	public int getActividadIdactividad() {
		return this.actividadIdactividad;
	}
	public void setActividadIdactividad(int actividadIdactividad) {
		this.actividadIdactividad = actividadIdactividad;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ActividadusuarioPK)) {
			return false;
		}
		ActividadusuarioPK castOther = (ActividadusuarioPK)other;
		return 
			(this.usuarioIdusuario == castOther.usuarioIdusuario)
			&& (this.actividadIdactividad == castOther.actividadIdactividad);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.usuarioIdusuario;
		hash = hash * prime + this.actividadIdactividad;
		
		return hash;
	}
}