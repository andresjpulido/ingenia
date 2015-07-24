package org.ingenia.adaptadores;

import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.MensajeVO;
import org.ingenia.negocio.entidades.Mensaje;

public abstract class IAdaptadorMensaje {

	Mensaje mensaje;
	MensajeVO mensajeVO;

	public abstract Mensaje getMensaje() throws AdaptadorException;

	public abstract MensajeVO getMensajeVO() throws AdaptadorException;

}
