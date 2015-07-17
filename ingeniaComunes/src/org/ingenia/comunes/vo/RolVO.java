package org.ingenia.comunes.vo;

import java.io.Serializable;
import java.util.List;

public class RolVO implements Serializable {

	private static final long serialVersionUID = 7392284641632632447L;

	private int idRol;

	private String descripcion;

	private String nombre;

	private List<OpcionVO> opcions;

	private boolean estado;
	
	private boolean seleccionado;

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<OpcionVO> getOpcions() {
		return opcions;
	}

	public void setOpcions(List<OpcionVO> opcions) {
		this.opcions = opcions;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public boolean isSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}
}
