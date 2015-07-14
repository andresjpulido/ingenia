package org.ingenia.presentacion.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.RolVO;
import org.ingenia.comunes.vo.UsuarioVO;
import org.ingenia.negocio.igestor.IGestorUsuariosLocal;
import org.ingenia.presentacion.BaseMB;

@ManagedBean(name = "AdmUsuarioMB")
@SessionScoped
public class AdmUsuarios extends BaseMB {

	private static final long serialVersionUID = 6395589570162245735L;
	private List<UsuarioVO> listaUsuarios = null;
	private String usuario = null;
	private UsuarioVO usuarioVO = null;

	private final static String NAV_IRUSUARIO = "irusuario";
	private final static String NAV_IRADMUSUARIO = "iradminusuario";

	public AdmUsuarios() {
		this.usuarioVO = new UsuarioVO();
	}

	@EJB
	private IGestorUsuariosLocal gestorUsuarios;

	public String buscar() {
		UsuarioVO usuarioVO = new UsuarioVO();
		usuarioVO.setNombre(this.usuario);
		listaUsuarios = gestorUsuarios.consultarUsuarios(usuarioVO);
		return NAV_IRADMUSUARIO;
	}

	public void guardar() {
		try {
			gestorUsuarios.modificarUsuario(this.usuarioVO);
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

	public String guardarRegistro() {

		if (this.usuarioVO == null)
			return null;

		if (!this.usuarioVO.getClave().equals(this.usuarioVO.getClave2())) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Las claves no son iguales"));
			return null;
		}

		try {
			List<RolVO> listaRoles = new ArrayList<RolVO>();
			RolVO rolVO = new RolVO();
			rolVO.setIdRol(3);
			listaRoles.add(rolVO);
			this.usuarioVO.setListaRoles(listaRoles);
			gestorUsuarios.crearUsuario(this.usuarioVO);
		} catch (AdaptadorException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error de concersion de tipos!"));
			e.printStackTrace();
			return null;

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e
							.getMessage()));
			e.printStackTrace();
			return null;

		}
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
						"La operacion fue realizada satisfactoriamente !"));

		return null;
	}

	public void crear() {
		UsuarioVO usuarioVO = new UsuarioVO();
		usuarioVO.setNombre(this.usuario);
		try {
			gestorUsuarios.crearUsuario(usuarioVO);

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

	public String irUsuario() {
		List<UsuarioVO> listaUsuarios = null;
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext()
				.getRequestParameterMap();

		String id = params.get("id");
		UsuarioVO usuarioVOt = new UsuarioVO();
		usuarioVOt.setId(Integer.parseInt(id));
		listaUsuarios = gestorUsuarios.consultarUsuarios(usuarioVOt);
		if (listaUsuarios != null && !listaUsuarios.isEmpty()) {
			this.usuarioVO = listaUsuarios.get(0);

			//gestorUsuarios.consultarOpcionVOPorIdRol(1);
		}

		return NAV_IRUSUARIO;
	}

	public List<UsuarioVO> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<UsuarioVO> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public UsuarioVO getUsuarioVO() {
		return usuarioVO;
	}

	public void setUsuarioVO(UsuarioVO usuarioVO) {
		this.usuarioVO = usuarioVO;
	}
}
