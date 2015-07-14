package org.ingenia.comunes.vo;

import java.io.Serializable;

public class GatoVO implements Serializable {

	private static final long serialVersionUID = -949796013955276126L;

	private int idgato;

	private TipoGatoVO tipogato;

	private String enunciado;
	
	private ColorVO color;

	private int orden;
	
	private ArmaduraVO armadura;
	
	private ArmaVO arma;

	public GatoVO() {
	}

	public int getIdgato() {
		return idgato;
	}

	public void setIdgato(int idgato) {
		this.idgato = idgato;
	}

	public TipoGatoVO getTipogato() {
		return tipogato;
	}

	public void setTipogato(TipoGatoVO tipogato) {
		this.tipogato = tipogato;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public ColorVO getColor() {
		return color;
	}

	public void setColor(ColorVO color) {
		this.color = color;
	}

	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}


	public ArmaVO getArma() {
		return arma;
	}

	public void setArma(ArmaVO arma) {
		this.arma = arma;
	}



	/**
	 * @return the armadura
	 */
	public ArmaduraVO getArmadura() {
		return armadura;
	}

	/**
	 * @param armadura the armadura to set
	 */
	public void setArmadura(ArmaduraVO armadura) {
		this.armadura = armadura;
	}




}