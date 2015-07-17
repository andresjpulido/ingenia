package org.ingenia.adaptadores;

import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.ActividadVO;
import org.ingenia.negocio.entidades.Actividad;


public class AdaptadorActividad extends IAdaptadorActividad{

	
	public AdaptadorActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public AdaptadorActividad(ActividadVO actividadVO) {
		this.actividadVO = actividadVO;
	}
	
	@Override
	public Actividad getActividad() throws AdaptadorException {
		Actividad actividad = null;
		if (actividadVO == null)
			return null;
		actividad = new Actividad();
		  AdaptadorUsuario adaptador = new AdaptadorUsuario(actividadVO.getProfesor());
		actividad.setUsuario(adaptador.getUsuario());
		 AdaptadorJuego adap =new AdaptadorJuego(actividadVO.getJuegoVO());
		actividad.setJuego(adap.getJuego());
		actividad.setEnunciado(actividadVO.getEnunciado());
		actividad.setIdactividad(actividadVO.getIdactividad());
		actividad.setUrlTextoEnsenanza(actividadVO.getUrl_conocimiento());
		actividad.setActivo(actividadVO.getActivo());
		actividad.setLimiteMovimientos(actividadVO.getLimite_movimientos());
		actividad.setNombre(actividadVO.getNombre());
		return actividad;
	}

	@Override
	public ActividadVO getActividadVO() throws AdaptadorException {
		ActividadVO actividadVO = null;
		if (actividad == null)
			return null;
		actividadVO=new ActividadVO();
		AdaptadorUsuario adaptador = new AdaptadorUsuario(actividad.getUsuario());
		actividadVO.setProfesor(adaptador.getUsuarioVO());
		AdaptadorJuego adap =new AdaptadorJuego(actividad.getJuego());
		actividadVO.setJuegoVO(adap.getJuegoVO());
		actividadVO.setEnunciado(actividad.getEnunciado());
		actividadVO.setIdactividad(actividad.getIdactividad());
		actividadVO.setUrl_conocimiento(actividad.getUrlTextoEnsenanza());
		actividadVO.setActivo(actividad.getActivo());
		actividadVO.setLimite_movimientos(actividad.getLimiteMovimientos());
		actividadVO.setNombre(actividad.getNombre());
		
		return actividadVO;
	}

}
