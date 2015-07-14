package org.ingenia.comunes.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class UsuarioVO implements Serializable {

	private static final long serialVersionUID = -5600397150329499922L;

	private int id;

	private String apellido;

	private String alias;

	private String clave;

	private String clave2;

	private String correo;

	private Date fechaCreacion;

	private Date fechaUltimoIngreso;

	private String genero;

	private String nombre;
	
	private int identificacion;
	
	private List<RolVO> listaRoles;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaUltimoIngreso() {
		return fechaUltimoIngreso;
	}

	public void setFechaUltimoIngreso(Date fechaUltimoIngreso) {
		this.fechaUltimoIngreso = fechaUltimoIngreso;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getClave2() {
		return clave2;
	}

	public void setClave2(String clave2) {
		this.clave2 = clave2;
	}

	public List<RolVO> getListaRoles() {
		return listaRoles;
	}

	public void setListaRoles(List<RolVO> listaRoles) {
		this.listaRoles = listaRoles;
	}

	public int getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(int identificacion) {
		this.identificacion = identificacion;
	}

}
