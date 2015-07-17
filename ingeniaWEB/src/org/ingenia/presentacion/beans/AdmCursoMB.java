package org.ingenia.presentacion.beans;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.ingenia.comunes.vo.ActividadVO;
import org.ingenia.comunes.vo.ActividadxUsuarioVO;
import org.ingenia.comunes.vo.CursoActividadVO;
import org.ingenia.comunes.vo.EstudianteVO;
import org.ingenia.comunes.vo.UsuarioVO;
import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.CursoVO;
import org.ingenia.negocio.igestor.IGestorCursosLocal;
import org.ingenia.negocio.igestor.IGestorUsuariosLocal;
import org.ingenia.presentacion.BaseMB;


@ManagedBean(name = "AdmCursoMB")
@SessionScoped
public class AdmCursoMB extends BaseMB {

	private static final long serialVersionUID = 6956796593946333976L;

	private CursoVO cursoVO=new CursoVO();
	private CursoVO cursoVOcrear=new CursoVO();
	ActividadVO actividadVO = new ActividadVO();
	private List<ActividadVO> listaActividades;
	private List<ActividadxUsuarioVO> listaActividadesEstudiante;
	private String curso;
	private List<CursoVO> listaCursos;
    private CursoVO cursoVOtemp=new CursoVO();
	private boolean buscando=false;
	private final static String NAV_IRCURSO = "ircurso";
	private final static String NAV_IRADMCURSO = "iradmincurso";
	private final static String NAV_IRACTCURSOEST = "iractcursoest";
	private UsuarioVO UsuarioVO=new UsuarioVO();	
	private EstudianteVO estudianteVO=new EstudianteVO();
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;

	@EJB
	private IGestorCursosLocal gestorCursos;
	@EJB
	private IGestorUsuariosLocal gestorUsuarios;

	@PostConstruct
	public void init() {
		
		cursoVO=new CursoVO();		
		
		try {
			 faceContext=FacesContext.getCurrentInstance();
		        httpServletRequest=(HttpServletRequest)faceContext.getExternalContext().getRequest();
		        if(httpServletRequest.getSession().getAttribute("sessionUsuario")!=null)
		        {
		        	this.UsuarioVO=(UsuarioVO) httpServletRequest.getSession().getAttribute("sessionUsuario");
		            System.out.println("id profe"+UsuarioVO.getId());
					listaCursos = gestorCursos.consultarCursosProfesor(this.UsuarioVO.getId());
		        }

		} catch (AdaptadorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	  public String nuevoCurso() {	   
	        this.cursoVOcrear = new CursoVO();   
	        setCursoVOtemp(null);
	        return NAV_IRCURSO;
	    }
	
	public String buscar() {//es mas como un filtro
		CursoVO CursoVO = new CursoVO();
		CursoVO.setNombre(curso);
		CursoVO.setProfesor(this.UsuarioVO);
	
		try {
			this.buscando=true;
			setListaCursos(gestorCursos.consultarCursosPorNombre(CursoVO));
			
		} catch (AdaptadorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		return NAV_IRADMCURSO;
	}

	
	public String asociarActividad() {
		System.out.println(this.actividadVO.getIdactividad());

		try {
			CursoActividadVO cursoActividadVO=new CursoActividadVO();
			cursoActividadVO.setActividad(this.actividadVO);
			cursoActividadVO.setCurso(this.cursoVO);
			cursoActividadVO.setPosicion((this.cursoVO.getActividades().size()+1));
			gestorCursos.asociarActividad(cursoActividadVO);
			this.cursoVO=gestorCursos.consultarCursoVO(this.cursoVO);
			this.listaActividades=gestorCursos.consultarActividadesDisponibles(this.cursoVO,this.UsuarioVO);			
			
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
		return NAV_IRCURSO;
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

		

		try {

			if (this.cursoVOcrear.getIdcurso()==0){
				//profesorVO.setId(7890);
				CursoVO cursoVO = this.cursoVOcrear;
				cursoVO.setProfesor(this.UsuarioVO);
				gestorCursos.crearCursoVO(cursoVO);
				setListaCursos(gestorCursos.consultarCursosProfesor(this.UsuarioVO.getId()));
			}
			else 
			{	
				CursoVO cursoVO = this.cursoVO;
				gestorCursos.modificarCursoVO(cursoVO);
          		this.buscando=false;
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
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
						"La operacion fue realizada satisfactoriamente !"));
		
		return NAV_IRADMCURSO;
	}

	public String irCurso() {
		cursoVOtemp =new CursoVO();
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext()
				.getRequestParameterMap();

		String id = params.get("id");
		CursoVO cursoVO = new CursoVO();
		cursoVO.setIdcurso(Integer.parseInt(id));
		try {
			this.cursoVO = gestorCursos.consultarCursoVO(cursoVO);
			//System.out.println("lista est "+this.cursoVO.getEstudiantes().size());
			this.listaActividades=gestorCursos.consultarActividadesDisponibles(this.cursoVO,this.UsuarioVO);		
            
		} catch (AdaptadorException e) {
			e.printStackTrace();
		}
	
		return NAV_IRCURSO;
	}

	
	public String verCursoActividadesEstudiante(){
		 int id=Integer.parseInt(recuperarParametro("idestudiante"));
		this.estudianteVO.setId(id);
		try {
			setListaActividadesEstudiante(gestorCursos.consultarActividadesCursoEstudiante(this.cursoVO,this.estudianteVO));
			UsuarioVO usuario= gestorUsuarios.consultarUsuarioPorId(this.estudianteVO.getId());
			this.estudianteVO.setNombre(usuario.getNombre());
			this.estudianteVO.setApellido(usuario.getApellido());
			

		} catch (AdaptadorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("actividades estudiante"+id);
		return NAV_IRACTCURSOEST;
	}
	
	public CursoVO getCursoVO() {
		try {

			this.cursoVO=gestorCursos.consultarCursoVO(this.cursoVO);
			
			
		
		} catch (AdaptadorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		try {
			if(buscando==false){
				setListaCursos(gestorCursos.consultarCursosProfesor(this.UsuarioVO.getId()));
			}

		} catch (AdaptadorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		try {
			listaActividades=gestorCursos.consultarActividadesDisponibles(this.cursoVO,this.UsuarioVO);
		} catch (AdaptadorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
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





}
