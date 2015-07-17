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
		
		actividadxUsuario.setUsuario(adaptador.getUsuario());
		actividadxUsuario.setActividad(adaptadorActividad.getActividad());
		actividadxUsuario.setFecha(actividadxUsuarioVO.getFecha());
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
		
		actividadxUsuarioVO=new ActividadxUsuarioVO();
				
		actividadxUsuarioVO.setFecha(actividadxUsuario.getFecha());
		actividadxUsuarioVO.setId(actividadxUsuario.getId().getActividadIdactividad());
		actividadxUsuarioVO.setNumeroIntento(actividadxUsuario.getNumeroIntento());
		actividadxUsuarioVO.setPuntos(actividadxUsuario.getPuntos());
		
		return actividadxUsuarioVO;
	}

}
