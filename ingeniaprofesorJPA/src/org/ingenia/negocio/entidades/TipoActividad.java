package org.ingenia.negocio.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipo_actividad database table.
 * 
 */
@Entity
@Table(name="tipo_actividad")
@NamedQuery(name="TipoActividad.findAll", query="SELECT t FROM TipoActividad t")
public class TipoActividad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idtipo_actividad")
	private int idtipoActividad;

	private String nombre;

	//bi-directional many-to-one association to Actividad
	@OneToMany(mappedBy="tipoActividad")
	private List<Actividad> actividads;

	public TipoActividad() {
	}

	public int getIdtipoActividad() {
		return this.idtipoActividad;
	}

	public void setIdtipoActividad(int idtipoActividad) {
		this.idtipoActividad = idtipoActividad;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Actividad> getActividads() {
		return this.actividads;
	}

	public void setActividads(List<Actividad> actividads) {
		this.actividads = actividads;
	}

	public Actividad addActividad(Actividad actividad) {
		getActividads().add(actividad);
		actividad.setTipoActividad(this);

		return actividad;
	}

	public Actividad removeActividad(Actividad actividad) {
		getActividads().remove(actividad);
		actividad.setTipoActividad(null);

		return actividad;
	}

}