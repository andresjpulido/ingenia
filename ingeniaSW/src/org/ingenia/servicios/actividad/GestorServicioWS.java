package org.ingenia.servicios.actividad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.ingenia.comunes.vo.ActividadVO;
import org.ingenia.comunes.vo.EstructuraVO;
import org.ingenia.servicios.FachadaServicios;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/service")
public class GestorServicioWS {

	private static final Logger logger = LoggerFactory.getLogger(GestorServicioWS.class);

	Map<Integer, ActividadVO> actividades = new HashMap<Integer, ActividadVO>();

	@RequestMapping(value = GIngeniaURI.DUMMY_ACTIVIDAD, method = RequestMethod.GET)
	public @ResponseBody ActividadVO getDummyACtividad() {

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
	
	@RequestMapping(value = GIngeniaURI.GET_ACTIVIDAD, method = RequestMethod.GET)
	public ArrayList<EstructuraVO> getEstructurasPorActividad(@PathVariable String IdActividad) {
		
		ArrayList<EstructuraVO> estructuras = new ArrayList<EstructuraVO>(); 		
		
		//consulta a BD
		
		FachadaServicios fachada = new FachadaServicios();
		estructuras =  fachada.ConsultaEstructurasPorActividad(IdActividad);
			
        return estructuras;
	}
}
