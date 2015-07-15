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
import org.ingenia.comunes.vo.CursoActividadVO;
import org.ingenia.comunes.vo.UsuarioVO;
import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.CursoVO;
import org.ingenia.negocio.igestor.IGestorCursosLocal;
import org.ingenia.presentacion.BaseMB;


@ManagedBean(name = "AdmCursoMB")
@SessionScoped
public class AdmCursoMB extends BaseMB {

	private static final long serialVersionUID = 6956796593946333976L;

	private CursoVO cursoVO=new CursoVO();
	ActividadVO actividadVO = new ActividadVO();
	private List<ActividadVO> listaActividades;
	private String curso;
	private List<CursoVO> listaCursos;
    private CursoVO cursoVOtemp=new CursoVO();
	private boolean buscando=false;
	private final static String NAV_IRCURSO = "ircurso";
	private final static String NAV_IRADMCURSO = "iradmincurso";
	private UsuarioVO UsuarioVO=new UsuarioVO();	
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;

	@EJB
	IGestorCursosLocal gestorCursos;

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
	        this.cursoVO = new CursoVO();   
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

		CursoVO cursoVO = this.cursoVO;

		try {

			if (cursoVO.getIdcurso()==0){
				//profesorVO.setId(7890);
				cursoVO.setProfesor(this.UsuarioVO);
				gestorCursos.crearCursoVO(cursoVO);
				setListaCursos(gestorCursos.consultarCursosProfesor(this.UsuarioVO.getId()));
			}
			else 
			{	
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
			System.out.println("lista est "+this.cursoVO.getEstudiantes().size());
			this.listaActividades=gestorCursos.consultarActividadesDisponibles(this.cursoVO,this.UsuarioVO);		
            
		} catch (AdaptadorException e) {
			e.printStackTrace();
		}
	
		return NAV_IRCURSO;
	}

	public CursoVO getCursoVO() {
		try {
			this.cursoVO = gestorCursos.consultarCursoVO(this.cursoVO);
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
			gestorCursos.consultarActividadesDisponibles(this.cursoVO,this.UsuarioVO);
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



}
