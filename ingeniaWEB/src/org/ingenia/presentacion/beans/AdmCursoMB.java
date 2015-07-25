package org.ingenia.presentacion.beans;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.ingenia.comunes.vo.ActividadVO;
import org.ingenia.comunes.vo.ActividadxUsuarioVO;
import org.ingenia.comunes.vo.CursoActividadVO;
import org.ingenia.comunes.vo.EstudianteVO;
import org.ingenia.comunes.vo.UsuarioVO;
import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.CursoVO;
import org.ingenia.negocio.igestor.IGestorActividadesLocal;
import org.ingenia.negocio.igestor.IGestorCursosLocal;
import org.ingenia.negocio.igestor.IGestorUsuariosLocal;
import org.ingenia.presentacion.BaseMB;
import org.ingenia.presentacion.ReglasNavegacion;

@ManagedBean(name = "AdmCursoMB")
@SessionScoped
public class AdmCursoMB extends BaseMB {

	private static final long serialVersionUID = 6956796593946333976L;

	private CursoVO cursoVO = new CursoVO();
	private CursoVO cursoVOcrear = new CursoVO();
	private CursoVO cursoVO1 = new CursoVO();
	ActividadVO actividadVO = new ActividadVO();
	private List<ActividadVO> listaActividades;
	private List<ActividadxUsuarioVO> listaActividadesEstudiante;
	private String curso;
	private List<CursoVO> listaCursos;
	private List<CursoVO> listaCursosest;
	private List<CursoVO> listaCursosdisponible;
	private CursoVO cursoVOtemp = new CursoVO();
	private boolean buscando = false;
	private boolean creando = false;
	private ActividadVO actividadSeleccionada;
	private CursoVO cursoSeleccionado;
	private int posicion = 0;
	private String text;
	private UsuarioVO usuarioVO = new UsuarioVO();
	private EstudianteVO estudianteVO = new EstudianteVO();
	private HttpServletRequest httpServletRequest;
	private FacesContext faceContext;

	@EJB
	private IGestorCursosLocal gestorCursos;
	@EJB
	private IGestorUsuariosLocal gestorUsuarios;
	@EJB
	private IGestorActividadesLocal gestorActividades;

	public void cargarlistas2() {
		if (creando == false) {
			try {
				this.listaActividades = gestorCursos
						.consultarActividadesDisponibles(this.cursoVO,
								this.usuarioVO);
				this.cursoVO = gestorCursos.consultarCursoVO(cursoVO);
			} catch (AdaptadorException e) {
				e.printStackTrace();
			}
		} else {
			creando = false;
		}
	}

	public void cargarlistas() {
		if (buscando == false) {
			curso = "";

			try {
				faceContext = FacesContext.getCurrentInstance();
				httpServletRequest = (HttpServletRequest) faceContext
						.getExternalContext().getRequest();
				if (httpServletRequest.getSession().getAttribute(
						"sessionUsuario") != null) {
					this.usuarioVO = (UsuarioVO) httpServletRequest
							.getSession().getAttribute("sessionUsuario");
					for (int i = 0; this.usuarioVO.getListaRoles().size() > i; i++) {
						if (this.usuarioVO.getListaRoles().get(i).getIdRol() == 2) {
							listaCursos = gestorCursos
									.consultarCursosProfesor(this.usuarioVO);
							listaCursosest = null;
							listaCursosdisponible = null;
						}

						else if (this.usuarioVO.getListaRoles().get(i)
								.getIdRol() == 3) {
							listaCursosest = gestorCursos
									.consultarCursosEstudiante(this.usuarioVO);
							setListaCursosdisponible(gestorCursos
									.consultarCursosDisponibleEstudiante(listaCursosest));
							listaCursos = null;

						} else {
							listaCursosest = null;
							listaCursosdisponible = null;
							listaCursos = null;
						}
					}
				}

			} catch (AdaptadorException e) {
				e.printStackTrace();
			}
		} else {
			// cursoVO=new CursoVO();
			buscando = false;
		}

	}

	public String nuevoCurso() {
		creando = true;
		this.cursoVOcrear = new CursoVO();
		setCursoVOtemp(null);
		return ReglasNavegacion.NAV_IRCURSO;
	}

	public String buscar() {// es mas como un filtro
		CursoVO CursoVO = new CursoVO();
		CursoVO.setNombre(curso);
		CursoVO.setProfesor(this.usuarioVO);

		try {
			this.buscando = true;
			setListaCursos(gestorCursos.consultarCursosPorNombre(CursoVO));

		} catch (AdaptadorException e) {

			e.printStackTrace();
		}

		return ReglasNavegacion.NAV_IRADMCURSO;
	}

	public String buscarcursoest() {// es mas como un filtro
		CursoVO CursoVO = new CursoVO();
		CursoVO.setNombre(curso);

		try {
			this.buscando = true;
			setListaCursosest(gestorCursos.consultarCursosEstudiantePorNombre(
					CursoVO, usuarioVO));

		} catch (AdaptadorException e) {
			e.printStackTrace();
		}

		return ReglasNavegacion.NAV_IRMISCURSOS;
	}

	public String veractividad() {
		try {

			ExternalContext externalContext = FacesContext.getCurrentInstance()
					.getExternalContext();
			Map<String, String> params = externalContext
					.getRequestParameterMap();
			Map<String, Object> sessionMap = externalContext.getSessionMap();
			// subimos el curso seleccionado a sesion
			String id = params.get("id");
			CursoVO cursoActual = new CursoVO();
			cursoActual.setIdcurso(Integer.parseInt(id));
			sessionMap.put("CursoActual", cursoActual);
			cursoActual = gestorCursos.consultarCursoVO(cursoActual);
			List<ActividadVO> lista = cursoActual.getActividades();

			// buscamos la proxima actividad sin avances del estudiante
			faceContext = FacesContext.getCurrentInstance();
			httpServletRequest = (HttpServletRequest) faceContext
					.getExternalContext().getRequest();
			UsuarioVO usuarioActual = (UsuarioVO) httpServletRequest
					.getSession().getAttribute("sessionUsuario");
			ActividadVO actividadActual = null;
			for (ActividadVO actividadVO : lista) {
				List<ActividadxUsuarioVO> listaAvances = gestorActividades
						.consultarActividadesCursoUsuario(actividadVO);
				if (listaAvances == null || listaAvances.size() == 0) {
					actividadActual = actividadVO;
					break;
				} else {
					for (ActividadxUsuarioVO avance : listaAvances) {
						if (avance.getEstudiante().getId() != usuarioActual
								.getId()) {
							actividadActual = actividadVO;
							break;
						}
					}
				}
			}

			// subimos la actividad correspondiente a sesión
			sessionMap.put("ActividadActual", actividadActual);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ReglasNavegacion.NAV_VERACTIVIDAD;
	}

	public String buscarcursodisponible() {// es mas como un filtro
		CursoVO CursoVO = new CursoVO();
		CursoVO.setNombre(curso);

		try {
			this.buscando = true;
			setListaCursosdisponible(gestorCursos
					.consultarCursosDisponiblesEstudiantePorNombre(CursoVO,
							usuarioVO));

		} catch (AdaptadorException e) {
			e.printStackTrace();
		}

		return ReglasNavegacion.NAV_IRINSCRIBIRCURSOS;
	}

	public String asociarActividad() {

		try {
			CursoActividadVO cursoActividadVO = new CursoActividadVO();
			cursoActividadVO.setActividad(this.actividadVO);
			cursoActividadVO.setCurso(this.cursoVO);
			cursoActividadVO
					.setPosicion((this.cursoVO.getActividades().size() + 1));
			gestorCursos.asociarActividad(cursoActividadVO);
			this.cursoVO = gestorCursos.consultarCursoVO(this.cursoVO);
			this.listaActividades = gestorCursos
					.consultarActividadesDisponibles(this.cursoVO,
							this.usuarioVO);
			validarlimiteactividades();
		} catch (AdaptadorException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error de concersion de tipos!"));
			e.printStackTrace();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e
							.getMessage()));
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
						"La operacion fue realizada satisfactoriamente !"));
		return ReglasNavegacion.NAV_IRCURSO;
	}

	public void actualizar() {

		try {
			gestorCursos.modificarCursoVO(this.cursoVO);

		} catch (AdaptadorException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error de concersion de tipos!"));
			e.printStackTrace();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e
							.getMessage()));
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
						"La operacion fue realizada satisfactoriamente !"));

	}

	public String crear() {
		String destino = null;

		try {
			if (this.cursoVOtemp == null) {
				// profesorVO.setId(7890);
				CursoVO cursoVO = this.cursoVOcrear;
				cursoVO.setProfesor(this.usuarioVO);

				gestorCursos.crearCursoVO(cursoVO);
				setListaCursos(gestorCursos
						.consultarCursosProfesor(this.usuarioVO));
				destino = ReglasNavegacion.NAV_IRADMCURSO;
				FacesContext
						.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(FacesMessage.SEVERITY_INFO,
										"Info",
										"La operacion fue realizada satisfactoriamente !"));
			} else {
				if (this.cursoVO.getActividades().size() <= this.cursoVO
						.getLimite_actividades()) {
					CursoVO cursoVO = this.cursoVO;
					gestorCursos.modificarCursoVO(cursoVO);
					destino = ReglasNavegacion.NAV_IRADMCURSO;
					FacesContext
							.getCurrentInstance()
							.addMessage(
									null,
									new FacesMessage(
											FacesMessage.SEVERITY_INFO, "Info",
											"La operacion fue realizada satisfactoriamente !"));
				} else {
					FacesContext
							.getCurrentInstance()
							.addMessage(
									null,
									new FacesMessage(
											FacesMessage.SEVERITY_ERROR,
											"El limite de actividades no puede ser inferior a la cantidad actual",
											null));
				}
			}

		} catch (AdaptadorException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error de concersion de tipos!"));
			e.printStackTrace();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e
							.getMessage()));
			e.printStackTrace();
		}

		return destino;
	}

	public String irCurso() {
		cursoVOtemp = new CursoVO();
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext()
				.getRequestParameterMap();

		String id = params.get("id");
		CursoVO cursoVO = new CursoVO();
		cursoVO.setIdcurso(Integer.parseInt(id));
		try {
			this.cursoVO = gestorCursos.consultarCursoVO(cursoVO);
			this.listaActividades = gestorCursos
					.consultarActividadesDisponibles(this.cursoVO,
							this.usuarioVO);
			validarlimiteactividades();
		} catch (AdaptadorException e) {
			e.printStackTrace();
		}

		return ReglasNavegacion.NAV_IRCURSO;
	}

	public String verCursoActividadesEstudiante() {
		int id = Integer.parseInt(recuperarParametro("idestudiante"));
		this.estudianteVO.setId(id);
		try {
			setListaActividadesEstudiante(gestorCursos
					.consultarActividadesCursoEstudiante(this.cursoVO,
							this.estudianteVO));
			UsuarioVO usuario = gestorUsuarios
					.consultarUsuarioPorId(this.estudianteVO.getId());
			this.estudianteVO.setNombre(usuario.getNombre());
			this.estudianteVO.setApellido(usuario.getApellido());
			this.estudianteVO.setIdentificacion(usuario.getIdentificacion());
			this.estudianteVO.setCorreo(usuario.getCorreo());
			this.estudianteVO.setFechaUltimoIngreso(usuario
					.getFechaUltimoIngreso());

		} catch (AdaptadorException e) {
			e.printStackTrace();
		}
		return ReglasNavegacion.NAV_IRACTCURSOEST;
	}

	private void validarlimiteactividades() {
		if (!(this.cursoVO.getLimite_actividades() > this.cursoVO
				.getActividades().size())) {
			this.setCursoVO1(null);
		} else {
			this.setCursoVO1(new CursoVO());

		}
	}

	public void envioMensajeProfesor() {
		UsuarioVO destinatario = this.estudianteVO;
		try {
			this.gestorUsuarios.enviarMensaje(destinatario, this.usuarioVO,
					this.text);
		} catch (AdaptadorException e) {
			e.printStackTrace();
		}
	}

	public String inscribir() {

		int idcurso = Integer.parseInt(recuperarParametro("id"));
		this.cursoVO.setIdcurso(idcurso);
		try {
			this.gestorCursos.inscribirCurso(this.usuarioVO, this.cursoVO);
		} catch (AdaptadorException e) {
			e.printStackTrace();
		}

		return ReglasNavegacion.NAV_IRMISCURSOS;
	}

	public CursoVO getCursoVO() {

		return cursoVO;

	}

	public void setCursoVO(CursoVO cursoVO) {

		this.cursoVO = cursoVO;
	}

	public String getCurso() {

		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public List<CursoVO> getListaCursos() {

		return listaCursos;
	}

	public void setListaCursos(List<CursoVO> listaCursos) {
		this.listaCursos = listaCursos;
	}

	public CursoVO getCursoVOtemp() {
		return cursoVOtemp;
	}

	public void setCursoVOtemp(CursoVO cursoVOtemp) {
		this.cursoVOtemp = cursoVOtemp;
	}

	public List<ActividadVO> getListaActividades() {

		return listaActividades;
	}

	public void setListaActividades(List<ActividadVO> listaActividades) {
		this.listaActividades = listaActividades;
	}

	public ActividadVO getActividadVO() {
		return actividadVO;
	}

	public void setActividadVO(ActividadVO actividadVO) {
		this.actividadVO = actividadVO;
	}

	public EstudianteVO getEstudianteVO() {
		return estudianteVO;
	}

	public void setEstudianteVO(EstudianteVO estudianteVO) {
		this.estudianteVO = estudianteVO;
	}

	public CursoVO getCursoVOcrear() {
		return cursoVOcrear;
	}

	public void setCursoVOcrear(CursoVO cursoVOcrear) {
		this.cursoVOcrear = cursoVOcrear;
	}

	public List<ActividadxUsuarioVO> getListaActividadesEstudiante() {
		return listaActividadesEstudiante;
	}

	public void setListaActividadesEstudiante(
			List<ActividadxUsuarioVO> listaActividadesEstudiante) {
		this.listaActividadesEstudiante = listaActividadesEstudiante;
	}

	public CursoVO getCursoVO1() {
		return cursoVO1;
	}

	public void setCursoVO1(CursoVO cursoVO1) {
		this.cursoVO1 = cursoVO1;
	}

	public List<CursoVO> getListaCursosest() {
		return listaCursosest;
	}

	public void setListaCursosest(List<CursoVO> listaCursosest) {
		this.listaCursosest = listaCursosest;
	}

	public List<CursoVO> getListaCursosdisponible() {
		return listaCursosdisponible;
	}

	public void setListaCursosdisponible(List<CursoVO> listaCursosdisponible) {
		this.listaCursosdisponible = listaCursosdisponible;
	}

	public ActividadVO getActividadSeleccionada() {

		return actividadSeleccionada;
	}

	public void setActividadSeleccionada(ActividadVO actividadSeleccionada) {

		this.actividadSeleccionada = actividadSeleccionada;
	}

	public int getPosicion() {
		try {
			if (actividadSeleccionada != null) {
				this.setPosicion(gestorActividades.consultarPosicion(cursoVO,
						actividadSeleccionada));
			}
		} catch (AdaptadorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public CursoVO getCursoSeleccionado() {
		return cursoSeleccionado;
	}

	public void setCursoSeleccionado(CursoVO cursoSeleccionado) {
		this.cursoSeleccionado = cursoSeleccionado;
	}

	public String getText() {

		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public UsuarioVO getUsuarioVO() {
		return usuarioVO;
	}

	public void setUsuarioVO(UsuarioVO usuarioVO) {
		this.usuarioVO = usuarioVO;
	}

}
