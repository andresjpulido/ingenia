package org.ingenia.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.*;
import org.ingenia.negocio.igestor.*;
import org.ingenia.negocio.gestor.*;

public class FachadaServicios {

	@EJB
	IGestorActividadesRemote gestorActividad;
	IGestorGatosRemote gestorGatos;
	IGestorMovimientosRemote gestorMovimientos;
	IGestorEstructurasRemote gestorEstructuras;
	IGestorActividadxUsuarioRemote gestorActividadxUsuario;
	
	public FachadaServicios() {
		this.gestorActividad = new GestorActividades();
		this.gestorGatos = new GestorGatos();
		//this.gestorMovimientos = new GestorMovimientos();
		//this.gestorEstructuras = new GestorEstructuras();
		//this.gestorActividadxUsuario = new GestorActividadxUsuario();
	}

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
			estructuras = (ArrayList<EstructuraVO>) gestorEstructuras.consultarEstructuras(actividadVO);
		} catch (AdaptadorException e) {
			e.printStackTrace();
		}
		return estructuras;
	}  
	
	public ArrayList<MovimientoVO> ConsultaMovimientosPorActividad(String idActividad) {
		ArrayList<MovimientoVO> entidades = new ArrayList<MovimientoVO>();
		ActividadVO actividadVO = new ActividadVO();
		if (idActividad != null) {
			actividadVO.setIdactividad(Integer.parseInt(idActividad));
		}
		
		try {
			entidades = (ArrayList<MovimientoVO>) gestorMovimientos.consultarMovimientos(actividadVO);
		} catch (AdaptadorException e) {
			e.printStackTrace();
		}
		return entidades;
	}

	public ArrayList<GatoVO> ConsultaGatosPorActividad(String idActividad) {
		ArrayList<GatoVO> gatos = new ArrayList<GatoVO>();
		ActividadVO actividadVO = new ActividadVO();
		if (idActividad != null) {
			actividadVO.setIdactividad(Integer.parseInt(idActividad));
		}
		
		try {
			gatos = (ArrayList<GatoVO>) gestorGatos.consultarGatos(actividadVO);
		} catch (AdaptadorException e) {
			e.printStackTrace();
		}
		return gatos;
	}

	public ActividadxUsuarioVO GuardarActividadxUsuario(ActividadxUsuarioVO actividadxUsuario) {
		ActividadxUsuarioVO Resultado = null;
				
		try {
			Resultado = gestorActividadxUsuario.crearActividadxUsuario(actividadxUsuario);
		} catch (AdaptadorException e) {
			e.printStackTrace();
		}
		
		return Resultado;
	}
}
