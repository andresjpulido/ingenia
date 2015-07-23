package org.ingenia.adaptadores;

import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.MensajeVO;
import org.ingenia.negocio.entidades.Mensaje;


public class AdaptadorMensaje extends IAdaptadorMensaje{

	
	public AdaptadorMensaje(Mensaje actividad) {
		this.mensaje = actividad;
	}

	public AdaptadorMensaje(MensajeVO actividadVO) {
		this.mensajeVO = actividadVO;
	}
	
	@Override
	public Mensaje getMensaje() throws AdaptadorException {
		Mensaje mensaje = null;
		if (mensajeVO == null)
			return null;
		mensaje = new Mensaje();
		  AdaptadorUsuario adaptadorD = new AdaptadorUsuario(mensajeVO.getRemitente());
		  mensaje.setUsuario1(adaptadorD.getUsuario());
		  AdaptadorUsuario adaptadorR = new AdaptadorUsuario(mensajeVO.getDestinatario());
		  mensaje.setUsuario2(adaptadorR.getUsuario());
		  mensaje.setMensaje(mensajeVO.getMensaje());
		  mensaje.setIdmensaje(mensajeVO.getIdmensaje());

		return mensaje;
	}

	@Override
	public MensajeVO getMensajeVO() throws AdaptadorException {
		MensajeVO mensajeVO = null;
		if (mensaje == null)
			return null;
		mensajeVO = new MensajeVO();
		  AdaptadorUsuario adaptadorD = new AdaptadorUsuario(mensaje.getUsuario1());
		  mensajeVO.setRemitente(adaptadorD.getUsuarioVO());
		  AdaptadorUsuario adaptadorR = new AdaptadorUsuario(mensaje.getUsuario2());
		  mensajeVO.setDestinatario(adaptadorR.getUsuarioVO());
		  mensajeVO.setMensaje(mensaje.getMensaje());
		  mensajeVO.setIdmensaje(mensaje.getIdmensaje());
		
		return mensajeVO;
	}

}
