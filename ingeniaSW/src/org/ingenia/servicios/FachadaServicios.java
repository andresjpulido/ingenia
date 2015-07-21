package org.ingenia.servicios;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Path;

import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.*;
import org.ingenia.negocio.gestor.*;
import org.ingenia.negocio.igestor.*;

//import org.ingenia.negocio.gestor.*;

public class FachadaServicios {

	IGestorActividadesLocal gestorActividad;
	IGestorGatosLocal gestorGatos;
	IGestorMovimientosLocal gestorMovimientos;
	IGestorEstructurasLocal gestorEstructuras;
	IGestorActividadxUsuarioLocal gestorActividadxUsuario;

	@PersistenceContext(unitName = "ingeniaJPA")
	private EntityManager em;

	public FachadaServicios() {
		InstanciarJPA();
		 this.gestorActividad = new GestorActividades(em);
		 this.gestorGatos = new GestorGatos(em);
		 this.gestorMovimientos = new GestorMovimientos(em);
		 this.gestorEstructuras = new GestorEstructuras(em);
		 this.gestorActividadxUsuario = new GestorActividadxUsuario(em);
	}

	private void InstanciarJPA() {
		try {
			if (em == null) {
				EntityManagerFactory emf = Persistence
						.createEntityManagerFactory("ingeniaJPA");
				em = emf.createEntityManager();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ActividadVO consultaActividadVO(String idActividad) {
		ActividadVO actividadVO = new ActividadVO();
		if (idActividad != null) {
			actividadVO.setIdactividad(Integer.parseInt(idActividad));
		}

		try {
			//InstanciarJPA();
			//actividadVO = gestorActividad.consultarActividad(actividadVO);
			actividadVO = gestorActividad.consultarActividadVO(actividadVO);
		} catch (AdaptadorException e) {
			e.printStackTrace();
		}
		return actividadVO;
	}
	
	public ArrayList<EstructuraVO> ConsultaEstructurasPorActividad(
			String idActividad) {
		ArrayList<EstructuraVO> estructuras = new ArrayList<EstructuraVO>();
		ActividadVO actividadVO = new ActividadVO();
		if (idActividad != null) {
			actividadVO.setIdactividad(Integer.parseInt(idActividad));
		}

		try {
			estructuras = (ArrayList<EstructuraVO>) gestorEstructuras
					.consultarEstructuras(actividadVO);
		} catch (AdaptadorException e) {
			e.printStackTrace();
		}
		return estructuras;
	}

	public ArrayList<MovimientoVO> ConsultaMovimientosPorActividad(
			String idActividad) {
		ArrayList<MovimientoVO> entidades = new ArrayList<MovimientoVO>();
		ActividadVO actividadVO = new ActividadVO();
		if (idActividad != null) {
			actividadVO.setIdactividad(Integer.parseInt(idActividad));
		}

		try {
			entidades = (ArrayList<MovimientoVO>) gestorMovimientos
					.consultarMovimientos(actividadVO);
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

	public ActividadxUsuarioVO GuardarActividadxUsuario(
			ActividadxUsuarioVO actividadxUsuario) {
		ActividadxUsuarioVO Resultado = null;

		try {
			Resultado = gestorActividadxUsuario
					.crearActividadxUsuario(actividadxUsuario);
		} catch (AdaptadorException e) {
			e.printStackTrace();
		}

		return Resultado;
	}
}
