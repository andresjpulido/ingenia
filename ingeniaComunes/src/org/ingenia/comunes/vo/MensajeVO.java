package org.ingenia.comunes.vo;

import java.io.Serializable;

import org.ingenia.comunes.vo.UsuarioVO;

public class MensajeVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8266977523611594466L;

	private int idmensaje;

	private UsuarioVO remitente;

	private UsuarioVO destinatario;
	
	private String mensaje;

	public MensajeVO() {
	}

	public int getIdmensaje() {
		return this.idmensaje;
	}

	public void setIdmensaje(int idmensaje) {
		this.idmensaje = idmensaje;
	}

	public UsuarioVO getRemitente() {
		return this.remitente;
	}

	public void setRemitente(UsuarioVO usuario1) {
		this.remitente = usuario1;
	}

	public UsuarioVO getDestinatario() {
		return this.destinatario;
	}

	public void setDestinatario(UsuarioVO usuario2) {
		this.destinatario = usuario2;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}