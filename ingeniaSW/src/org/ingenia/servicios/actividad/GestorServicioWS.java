package org.ingenia.servicios.actividad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.ingenia.comunes.vo.*;
import org.ingenia.servicios.FachadaServicios;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/service")
public class GestorServicioWS {

	private static final Logger logger = LoggerFactory.getLogger(GestorServicioWS.class);
	
	@RequestMapping(value = GIngeniaURI.DUMMY_ACTIVIDAD, method = RequestMethod.GET)
	public @ResponseBody ActividadVO getDummyACtividad() {

		Map<Integer, ActividadVO> actividades = new HashMap<Integer, ActividadVO>();

		ActividadVO actividad = new ActividadVO();
		actividad.setActivo(0);
		actividad.setEnunciado("enunciado");
		actividad.setLimite_movimientos(1);
		//actividad.setId_juego(0);

		actividades.put(9999, actividad);

		logger.debug("Se ejecuta OK el Dummy de servicio rest de actividades !!!");

		return actividad;
	}
	
	//@RequestMapping(value = "/{IdActividad}", method = RequestMethod.GET)
	@RequestMapping(value = GIngeniaURI.GET_ACTIVIDAD, method = RequestMethod.GET)
	public ActividadVO getActividad(@PathVariable String IdActividad) {
		//consulta a BD
		ActividadVO actividad = new ActividadVO(); 		
		
		FachadaServicios fachada = new FachadaServicios();
		 
		actividad = fachada.consultaActividadVO(IdActividad);
			
        return actividad;
	}
	
	@RequestMapping(value = GIngeniaURI.GET_ESTRUCTURAS_ACTIVIDAD, method = RequestMethod.GET)
	public ArrayList<EstructuraVO> getEstructurasPorActividad(@PathVariable String IdActividad) {
		
		ArrayList<EstructuraVO> estructuras = new ArrayList<EstructuraVO>(); 		
		
		//consulta a BD
		
		FachadaServicios fachada = new FachadaServicios();
		estructuras =  fachada.ConsultaEstructurasPorActividad(IdActividad);
			
        return estructuras;
	}

	@RequestMapping(value = GIngeniaURI.GET_MOVIMIENTOS_ACTIVIDAD, method = RequestMethod.GET)
	public ArrayList<MovimientoVO> getMovimientosActividad(@PathVariable String IdActividad) {
		
		ArrayList<MovimientoVO> movimientos = new ArrayList<MovimientoVO>(); 		
		
		//consulta a BD
		
		FachadaServicios fachada = new FachadaServicios();
		movimientos =  fachada.ConsultaMovimientosPorActividad(IdActividad);
			
        return movimientos;
	}

	@RequestMapping(value = GIngeniaURI.GET_GATOS_ACTIVIDAD, method = RequestMethod.GET)
	public ArrayList<GatoVO> getGatosActividad(@PathVariable String IdActividad) {
		
		ArrayList<GatoVO> gatos = new ArrayList<GatoVO>(); 		
		
		//consulta a BD
		
		FachadaServicios fachada = new FachadaServicios();
		gatos =  fachada.ConsultaGatosPorActividad(IdActividad);
			
        return gatos;
	}

	@RequestMapping(value = GIngeniaURI.REGISTRAR_AVANCE_ACTIVIDAD, method = RequestMethod.POST)
	public Boolean saveAvanceActividad(@RequestBody ActividadxUsuarioVO avance) {
		
		Boolean Resultado = false;
		//Guardamos el avance en la tabla de la BD
		FachadaServicios fachada = new FachadaServicios();
		fachada.GuardarActividadxUsuario(avance);
			
        return Resultado;
	}
}
