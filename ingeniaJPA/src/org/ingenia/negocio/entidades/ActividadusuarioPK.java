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

	@Column(name="raton_idraton", insertable=false, updatable=false)
	private int ratonIdraton;

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
	public int getRatonIdraton() {
		return this.ratonIdraton;
	}
	public void setRatonIdraton(int ratonIdraton) {
		this.ratonIdraton = ratonIdraton;
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
			&& (this.actividadIdactividad == castOther.actividadIdactividad)
			&& (this.ratonIdraton == castOther.ratonIdraton);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.usuarioIdusuario;
		hash = hash * prime + this.actividadIdactividad;
		hash = hash * prime + this.ratonIdraton;
		
		return hash;
	}
}