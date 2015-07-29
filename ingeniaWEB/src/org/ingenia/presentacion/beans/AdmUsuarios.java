package org.ingenia.presentacion.beans;

import java.util.ArrayList;
import java.util.Date;
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
import org.ingenia.presentacion.ReglasNavegacion;

@ManagedBean(name = "AdmUsuarioMB")
@SessionScoped
public class AdmUsuarios extends BaseMB {

	private static final long serialVersionUID = 6395589570162245735L;
	private List<UsuarioVO> listaUsuarios = null;
	private String usuario = null;
	private UsuarioVO usuarioVO = null;
	private List<RolVO> listaRoles = null;

	private boolean esEdicion;

	public AdmUsuarios() {
		this.usuarioVO = new UsuarioVO();
	}

	@EJB
	private IGestorUsuariosLocal gestorUsuarios;

	public String buscar() {
		UsuarioVO usuarioVO = new UsuarioVO();
		usuarioVO.setNombre(this.usuario);
		listaUsuarios = gestorUsuarios.consultarUsuarios(usuarioVO);
		return ReglasNavegacion.NAV_IRADMUSUARIO;
	}

	/**
	 * Metodo utilizado en la administracion de usuarios
	 */
	public void guardar() {
		try {
			List<RolVO> listaNuevosRoles = null;

			if (listaRoles != null && !listaRoles.isEmpty()) {
				listaNuevosRoles = new ArrayList<RolVO>();
				for (RolVO rolVO : listaRoles) {
					if (rolVO.isSeleccionado()) {
						listaNuevosRoles.add(rolVO);
					}
				}
				usuarioVO.setListaRoles(listaNuevosRoles);
			}
			gestorUsuarios.crearUsuario(this.usuarioVO);

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

	/**
	 * Metodo utilizado en el registro del usuario
	 * 
	 * @return
	 */
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
			
			
			//verificacion si existe el usuario
			List<UsuarioVO> lista = gestorUsuarios.consultarUsuarios(usuarioVO);
			if (lista != null && lista.size() > 0){
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
								"El nombre de usuario ya existe !"));
				return null;
				
			}
				
			List<RolVO> listaRoles = new ArrayList<RolVO>();
			RolVO rolVO = new RolVO();
			rolVO.setIdRol(3);
			listaRoles.add(rolVO);
			this.usuarioVO.setListaRoles(listaRoles);
			this.usuarioVO.setFechaCreacion(new Date());
			this.usuarioVO.setFechaUltimoIngreso(new Date());
			this.usuarioVO.setActivo(true);
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

	public String ircrear() {
		this.esEdicion = true;
		this.usuarioVO = new UsuarioVO();
		this.listaRoles = gestorUsuarios.consultarRolVOPorIdUsuario(0);
		return ReglasNavegacion.NAV_IRUSUARIO;
	}

	public String iradminusuario() {
		this.listaUsuarios = null;
		this.usuarioVO = new UsuarioVO();
		return ReglasNavegacion.NAV_IRADMUSUARIO;
	}

	/**
	 * Metodo invocado desde la tabla de usuarios y sirve para cargar la
	 * informacion del usuario seleccionado para permitir la gestion de sus
	 * datos.
	 * 
	 * @return
	 */
	public String irUsuario() {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext()
				.getRequestParameterMap();

		this.esEdicion = false;
		
		String id = params.get("id");
		usuarioVO = new UsuarioVO();
		usuarioVO.setId(Integer.parseInt(id));
		try {
			usuarioVO = gestorUsuarios.consultarUsuario(usuarioVO);
			listaRoles = gestorUsuarios
					.consultarRolVOPorIdUsuario(this.usuarioVO.getId());
		} catch (AdaptadorException e) {
			e.printStackTrace();
		} 
		return ReglasNavegacion.NAV_IRUSUARIO;
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

	public List<RolVO> getListaRoles() {
		return listaRoles;
	}

	public void setListaRoles(List<RolVO> listaRoles) {
		this.listaRoles = listaRoles;
	}

	public boolean isEsEdicion() {
		return esEdicion;
	}

	public void setEsEdicion(boolean esEdicion) {
		this.esEdicion = esEdicion;
	}

}
