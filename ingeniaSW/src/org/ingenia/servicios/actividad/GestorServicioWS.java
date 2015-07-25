package org.ingenia.servicios.actividad;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.ingenia.comunes.vo.*;
import org.ingenia.negocio.igestor.IGestorGatosLocal;
import org.ingenia.servicios.FachadaServicios;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/service")
public class GestorServicioWS {

	private static final Logger logger = LoggerFactory
			.getLogger(GestorServicioWS.class);

	@RequestMapping(value = GIngeniaURI.DUMMY_ACTIVIDAD, method = RequestMethod.GET)
	public @ResponseBody ActividadVO getDummyACtividad() {

		// Map<Integer, ActividadVO> actividades = new HashMap<Integer,
		// ActividadVO>();

		ActividadVO actividad = new ActividadVO();
		actividad.setActivo(0);
		actividad.setEnunciado("enunciado");
		actividad.setLimite_movimientos(1);
		// actividad.setId_juego(0);

		// actividades.put(9999, actividad);

		logger.debug("Se ejecuta OK el Dummy de servicio rest de actividades !!!");

		return actividad;
	}

	@RequestMapping(value = GIngeniaURI.GET_ACTIVIDAD, method = RequestMethod.GET)
	public @ResponseBody ActividadVO getActividad(
			@PathVariable String IdActividad) {
		// consulta a BD
		ActividadVO actividad = new ActividadVO();

		try {
			FachadaServicios fachada = new FachadaServicios();
			actividad = fachada.consultaActividadVO(IdActividad);
			actividad.setProfesor(null);
			actividad.setEstructuras(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return actividad;
	}
	
	@RequestMapping(value = GIngeniaURI.GET_ESTRUCTURAS_ACTIVIDAD, method = RequestMethod.GET)
	public @ResponseBody ArrayList<EstructuraVO> getEstructurasPorActividad(
			@PathVariable String IdActividad) {

		ArrayList<EstructuraVO> estructuras = new ArrayList<EstructuraVO>();

		// consulta a BD
		try {
			FachadaServicios fachada = new FachadaServicios();
			estructuras = fachada.ConsultaEstructurasPorActividad(IdActividad);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return estructuras;
	}

	@RequestMapping(value = GIngeniaURI.GET_MOVIMIENTOS_ACTIVIDAD, method = RequestMethod.GET)
	public @ResponseBody ArrayList<MovimientoVO> getMovimientosActividad(
			@PathVariable String IdActividad) {

		ArrayList<MovimientoVO> movimientos = new ArrayList<MovimientoVO>();

		// consulta a BD
		try {
			FachadaServicios fachada = new FachadaServicios();
			movimientos = fachada.ConsultaMovimientosPorActividad(IdActividad);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return movimientos;
	}

	@RequestMapping(value = GIngeniaURI.GET_GATOS_ACTIVIDAD, method = RequestMethod.GET)
	public @ResponseBody ArrayList<GatoVO> getGatosActividad(
			@PathVariable String IdActividad) {

		ArrayList<GatoVO> gatos = new ArrayList<GatoVO>();

		// consulta a BD
		try {
			FachadaServicios fachada = new FachadaServicios();
			gatos = fachada.ConsultaGatosPorActividad(IdActividad);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return gatos;
	}
	
	@RequestMapping(value = GIngeniaURI.REGISTRAR_AVANCE_ACTIVIDAD, method = RequestMethod.POST)
	public @ResponseBody Boolean saveAvanceActividad(
			@RequestBody ActividadxUsuarioVO avance) {

		Boolean Resultado = false;
		// Guardamos el avance en la tabla de la BD
		try {
			FachadaServicios fachada = new FachadaServicios();
			
			ActividadVO actividadVO = new ActividadVO();// fachada.consultaActividadVO(String.valueOf(avance.getIdActividad())); 
			actividadVO.setIdactividad(avance.getIdActividad());
			avance.setActividadVO(actividadVO);
			UsuarioVO estudiante = new UsuarioVO();
			estudiante.setId(avance.getIdUsuario());
			avance.setEstudiante(estudiante);
			CursoVO curso = new CursoVO();
			curso.setIdcurso(avance.getIdCurso());
			avance.setCurso(curso);
			
			avance.setFecha(new Date(System.currentTimeMillis()));
			ActividadxUsuarioVO nuevo = fachada.GuardarActividadxUsuario(avance);
			Resultado = (nuevo==null)?false:true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Resultado;
	}
}
