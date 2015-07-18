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
import org.ingenia.comunes.vo.OpcionVO;
import org.ingenia.comunes.vo.RolVO;
import org.ingenia.negocio.igestor.IGestorUsuariosLocal;
import org.ingenia.presentacion.BaseMB;
import org.ingenia.presentacion.ReglasNavegacion;

@ManagedBean(name = "AdmRolMB")
@SessionScoped
public class AdmRolesMB extends BaseMB {

	private static final long serialVersionUID = 5198983008576748399L;

	private String rol;
	private List<RolVO> listaRoles;
	private RolVO rolVO;

	private List<OpcionVO> listaOpciones;

	private boolean esEdicion;

	@EJB
	IGestorUsuariosLocal gestorUsuarios;

	public AdmRolesMB() {
		rolVO = new RolVO();
	}

	public String buscar() {	 
		listaRoles = gestorUsuarios.consultarRoles(rolVO);
		return ReglasNavegacion.NAV_IRADMROL;
	}

	public String ircrear() {		
		this.esEdicion = true;
		rolVO = new RolVO();
		listaOpciones = gestorUsuarios.consultarOpcionVOPorIdRol(0);
		return ReglasNavegacion.NAV_IRROL;
	}

	public String guardar() {
		List<OpcionVO> listaNuevasOpciones = null;
		try {
			if(listaOpciones != null && !listaOpciones.isEmpty()){
				listaNuevasOpciones = new ArrayList<OpcionVO>();
				for (OpcionVO opcionVO : listaOpciones) {
					if (opcionVO.isSeleccionado()){
						listaNuevasOpciones.add(opcionVO) ;
					}
				}	
				rolVO.setOpcions(listaNuevasOpciones);
			} 
			gestorUsuarios.CrearRol(rolVO);
			 
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

		return null;
	}

	public String irRol() {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext()
				.getRequestParameterMap();
		
		this.esEdicion = false;
		
		String id = params.get("id");
		this.rolVO = new RolVO();
		this.rolVO.setIdRol(Integer.parseInt(id));
		this.rolVO = gestorUsuarios.consultarRol(rolVO);

		// cargar la lista de opciones
		listaOpciones = gestorUsuarios.consultarOpcionVOPorIdRol(rolVO
				.getIdRol());

		return ReglasNavegacion.NAV_IRROL;
	}
	
	public String iradminrol(){
		this.listaRoles = null;
		this.rolVO = new RolVO();
		return ReglasNavegacion.NAV_IRADMROL;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public List<RolVO> getListaRoles() {
		return listaRoles;
	}

	public void setListaRoles(List<RolVO> listaRoles) {
		this.listaRoles = listaRoles;
	}

	public RolVO getRolVO() {
		return rolVO;
	}

	public void setRolVO(RolVO rolVO) {
		this.rolVO = rolVO;
	}

	public List<OpcionVO> getListaOpciones() {
		return listaOpciones;
	}

	public void setListaOpciones(List<OpcionVO> listaOpciones) {
		this.listaOpciones = listaOpciones;
	}

	public boolean isEsEdicion() {
		return esEdicion;
	}

	public void setEsEdicion(boolean esEdicion) {
		this.esEdicion = esEdicion;
	}
}
