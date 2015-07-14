package org.ingenia.negocio.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the estructuras_activas database table.
 * 
 */
@Entity
@Table(name="estructuras_activas")
@NamedQuery(name="EstructurasActiva.findAll", query="SELECT e FROM EstructurasActiva e")
public class EstructurasActiva implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EstructurasActivaPK id;

	//bi-directional many-to-one association to Actividad
	@ManyToOne
	@JoinColumn(name="idactividad")
	private Actividad actividad;

	//bi-directional many-to-one association to Estructura
	@ManyToOne
	@JoinColumn(name="idestructura")
	private Estructura estructura;

	public EstructurasActiva() {
	}

	public EstructurasActivaPK getId() {
		return this.id;
	}

	public void setId(EstructurasActivaPK id) {
		this.id = id;
	}

	public Actividad getActividad() {
		return this.actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public Estructura getEstructura() {
		return this.estructura;
	}

	public void setEstructura(Estructura estructura) {
		this.estructura = estructura;
	}

}