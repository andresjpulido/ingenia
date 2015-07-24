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
	
	@Column(name="idcurso", insertable=false, updatable=false)
	private int idCurso;
	
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

	public int getIdCurso() {
		return this.idCurso;
	}
	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
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
			&& (this.idCurso == castOther.idCurso);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.usuarioIdusuario;
		hash = hash * prime + this.actividadIdactividad;
		hash = hash * prime + this.idCurso;
		
		return hash;
	}
}