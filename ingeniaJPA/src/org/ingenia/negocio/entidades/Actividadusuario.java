package org.ingenia.negocio.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the actividadusuario database table.
 * 
 */
@Entity
@NamedQuery(name="Actividadusuario.findAll", query="SELECT a FROM Actividadusuario a")
public class Actividadusuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ActividadusuarioPK id;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Column(name="numero_intento")
	private int numeroIntento;

	@Column(name="num_movimientos")
	private int numeroMovimientos;
	
	private int puntos;

	//bi-directional many-to-one association to Actividad
	@ManyToOne
	private Actividad actividad;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	private Usuario usuario;

	//bi-directional many-to-one association to Curso
	@ManyToOne
	private Curso curso;
	
	public Actividadusuario() {
	}

	public ActividadusuarioPK getId() {
		return this.id;
	}

	public void setId(ActividadusuarioPK id) {
		this.id = id;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getNumeroIntento() {
		return this.numeroIntento;
	}

	public void setNumeroIntento(int numeroIntento) {
		this.numeroIntento = numeroIntento;
	}

	public int getPuntos() {
		return this.puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public Actividad getActividad() {
		return this.actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getNumeroMovimientos() {
		return numeroMovimientos;
	}

	public void setNumeroMovimientos(int numeroMovimientos) {
		this.numeroMovimientos = numeroMovimientos;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

}