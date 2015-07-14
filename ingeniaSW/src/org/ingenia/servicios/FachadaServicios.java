package org.ingenia.servicios;

import java.util.ArrayList;

import javax.ejb.EJB;

import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.ActividadVO;
import org.ingenia.comunes.vo.EstructuraVO;
import org.ingenia.negocio.igestor.IGestorActividadesRemote;

public class FachadaServicios {

	@EJB
	IGestorActividadesRemote gestorActividad;

	public ActividadVO consultaActividadVO(String idActividad) {
		ActividadVO actividadVO = new ActividadVO();
		if (idActividad != null) {
			actividadVO.setIdactividad(Integer.parseInt(idActividad));
		}
		
		try {
			actividadVO = gestorActividad.consultarActividad(actividadVO);
		} catch (AdaptadorException e) {
			e.printStackTrace();
		}
		return actividadVO;
	}

	public ArrayList<EstructuraVO> ConsultaEstructurasPorActividad(String idActividad) {
		ArrayList<EstructuraVO> estructuras = new ArrayList<EstructuraVO>();
		ActividadVO actividadVO = new ActividadVO();
		if (idActividad != null) {
			actividadVO.setIdactividad(Integer.parseInt(idActividad));
		}
		
		try {
			actividadVO = gestorActividad.consultarActividad(actividadVO);
		} catch (AdaptadorException e) {
			e.printStackTrace();
		}
		return estructuras;
	}  
	
}
