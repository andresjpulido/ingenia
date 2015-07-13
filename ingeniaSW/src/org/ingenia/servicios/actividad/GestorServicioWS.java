package org.ingenia.servicios.actividad;

import java.util.HashMap;
import java.util.Map;

import org.ingenia.comunes.vo.ActividadVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ingenia")
public class GestorServicioWS {

	private static final Logger logger = LoggerFactory
			.getLogger(GestorServicioWS.class);

	Map<Integer, ActividadVO> actividades = new HashMap<Integer, ActividadVO>();

	@RequestMapping(value = GIngeniaURI.DUMMY_ACTIVIDAD, method = RequestMethod.GET)
	public @ResponseBody ActividadVO getDummyACtividad() {

		ActividadVO actividad = new ActividadVO();
		actividad.setActivo(0);
		actividad.setEnunciado("enunciado");
		actividad.setLimite_movimientos(1);
		actividad.setId_juego(0);

		actividades.put(9999, actividad);

		logger.debug("Se ejecuta OK el Dummy de servicio rest de actividades !!!");

		return actividad;
	}
}
