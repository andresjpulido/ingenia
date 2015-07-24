package org.ingenia.adaptadores;

import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.ActividadxUsuarioVO;
import org.ingenia.negocio.entidades.Actividadusuario;
import org.ingenia.negocio.entidades.ActividadusuarioPK;


public class AdaptadorActividadxUsuario extends IAdaptadorActividadxUsuario{

	
	public AdaptadorActividadxUsuario(Actividadusuario actividadxUsuario) {
		this.actividadxUsuario = actividadxUsuario;
	}

	public AdaptadorActividadxUsuario(ActividadxUsuarioVO actividadxUsuarioVO) {
		this.actividadxUsuarioVO = actividadxUsuarioVO;
	}
	
	@Override
	public Actividadusuario getActividadxUsuario() throws AdaptadorException {
		Actividadusuario actividadxUsuario = null;
		if (actividadxUsuarioVO == null)
			return null;
		actividadxUsuario = new Actividadusuario();
		
		AdaptadorUsuario adaptador = new AdaptadorUsuario(actividadxUsuarioVO.getEstudiante());
		AdaptadorActividad adaptadorActividad = new AdaptadorActividad(actividadxUsuarioVO.getActividadVO());
		AdaptadorCurso adaptadorCurso = new AdaptadorCurso(actividadxUsuarioVO.getCurso());
		
		actividadxUsuario.setUsuario(adaptador.getUsuario());
		actividadxUsuario.setActividad(adaptadorActividad.getActividad());
		actividadxUsuario.setCurso(adaptadorCurso.getCurso());
		actividadxUsuario.setFecha(actividadxUsuarioVO.getFecha());
		actividadxUsuario.setNumeroMovimientos(actividadxUsuarioVO.getNumeroMovimientos());
		ActividadusuarioPK id = new ActividadusuarioPK();
		id.setActividadIdactividad(actividadxUsuarioVO.getId());
		actividadxUsuario.setId(id);
		actividadxUsuario.setNumeroIntento(actividadxUsuarioVO.getNumeroIntento());
		actividadxUsuario.setPuntos(actividadxUsuarioVO.getPuntos());
		
		return actividadxUsuario;
	}

	@Override
	public ActividadxUsuarioVO getActividadxUsuarioVO() throws AdaptadorException {
		ActividadxUsuarioVO actividadxUsuarioVO = null;
		if (actividadxUsuario == null)
			return null;
		AdaptadorUsuario adaptador = new AdaptadorUsuario(actividadxUsuario.getUsuario());
		AdaptadorActividad adaptadorActividad = new AdaptadorActividad(actividadxUsuario.getActividad());
		AdaptadorCurso adaptadorCurso = new AdaptadorCurso(actividadxUsuario.getCurso());
		
		actividadxUsuarioVO=new ActividadxUsuarioVO();
		actividadxUsuarioVO.setEstudiante(adaptador.getUsuarioVO());
		actividadxUsuarioVO.setActividadVO(adaptadorActividad.getActividadVO());	
		actividadxUsuarioVO.setCurso(adaptadorCurso.getCursoVO());
		actividadxUsuarioVO.setFecha(actividadxUsuario.getFecha());
		actividadxUsuarioVO.setId(actividadxUsuario.getId().getActividadIdactividad());
		actividadxUsuarioVO.setNumeroIntento(actividadxUsuario.getNumeroIntento());
		actividadxUsuarioVO.setPuntos(actividadxUsuario.getPuntos());
		actividadxUsuarioVO.setNumeroMovimientos(actividadxUsuario.getNumeroMovimientos());
		
		return actividadxUsuarioVO;
	}

}
