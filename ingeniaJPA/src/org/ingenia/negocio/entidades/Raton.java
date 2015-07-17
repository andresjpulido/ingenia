package org.ingenia.negocio.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the raton database table.
 * 
 */
@Entity
@NamedQuery(name="Raton.findAll", query="SELECT r FROM Raton r")
public class Raton implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idraton;

	private String nombre;

	//bi-directional many-to-one association to Actividadusuario
	@OneToMany(mappedBy="raton")
	private List<Actividadusuario> actividadusuarios;

	//bi-directional many-to-many association to Estructura
	@ManyToMany
	@JoinTable(
		name="estructuraraton"
		, joinColumns={
			@JoinColumn(name="idraton")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idestructura")
			}
		)
	private List<Estructura> estructuras;

	//bi-directional many-to-many association to Movimiento
	@ManyToMany
	@JoinTable(
		name="movimientoraton"
		, joinColumns={
			@JoinColumn(name="idraton")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idmovimiento")
			}
		)
	private List<Movimiento> movimientos;

	public Raton() {
	}

	public int getIdraton() {
		return this.idraton;
	}

	public void setIdraton(int idraton) {
		this.idraton = idraton;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Actividadusuario> getActividadusuarios() {
		return this.actividadusuarios;
	}

	public void setActividadusuarios(List<Actividadusuario> actividadusuarios) {
		this.actividadusuarios = actividadusuarios;
	}

	public Actividadusuario addActividadusuario(Actividadusuario actividadusuario) {
		getActividadusuarios().add(actividadusuario);
		actividadusuario.setRaton(this);

		return actividadusuario;
	}

	public Actividadusuario removeActividadusuario(Actividadusuario actividadusuario) {
		getActividadusuarios().remove(actividadusuario);
		actividadusuario.setRaton(null);

		return actividadusuario;
	}

	public List<Estructura> getEstructuras() {
		return this.estructuras;
	}

	public void setEstructuras(List<Estructura> estructuras) {
		this.estructuras = estructuras;
	}

	public List<Movimiento> getMovimientos() {
		return this.movimientos;
	}

	public void setMovimientos(List<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}

}