package org.ingenia.negocio.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the movimiento database table.
 * 
 */
@Entity
@NamedQuery(name="Movimiento.findAll", query="SELECT m FROM Movimiento m")
public class Movimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idmovimientos;

	@Column(name="nombre_movimiento")
	private String nombreMovimiento;

	/*//bi-directional many-to-many association to Raton
	@ManyToMany(mappedBy="movimientos")
	private List<Raton> ratons;*/

	public Movimiento() {
	}

	public int getIdmovimientos() {
		return this.idmovimientos;
	}

	public void setIdmovimientos(int idmovimientos) {
		this.idmovimientos = idmovimientos;
	}

	public String getNombreMovimiento() {
		return this.nombreMovimiento;
	}

	public void setNombreMovimiento(String nombreMovimiento) {
		this.nombreMovimiento = nombreMovimiento;
	}


}