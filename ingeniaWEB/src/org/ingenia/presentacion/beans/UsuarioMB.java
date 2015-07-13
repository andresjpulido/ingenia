package org.ingenia.presentacion.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ingenia.comunes.vo.MensajeVO;
import org.ingenia.comunes.vo.UsuarioVO;
import org.ingenia.negocio.igestor.IGestorUsuariosLocal;
import org.ingenia.presentacion.BaseMB;
import org.ingenia.presentacion.ReglasNavegacion;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "usuarioMB")
@SessionScoped
public class UsuarioMB extends BaseMB {

	private static final long serialVersionUID = -284219328181576347L;

	private String usuario;

	private String clave;

	private UsuarioVO usuariovo;

	private boolean logeado = false;

	private List<MensajeVO> listaMensajesRecibidos;

	private List<MensajeVO> listaMensajesEnviados;

	@EJB
	private IGestorUsuariosLocal gestorUsuarios;

	private static Logger logger = LogManager.getLogger(UsuarioMB.class);

	public String autenticar() {
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage message = null;
		logeado = false;
		String resultado = null;
		List<UsuarioVO> listaUsuarios = null;

		UsuarioVO usu = new UsuarioVO();
		usu.setAlias(usuario);
		usu.setClave(clave);
		listaUsuarios = gestorUsuarios.consultarUsuarios(usu);

		if (listaUsuarios != null && !listaUsuarios.isEmpty()) {
			this.usuariovo = listaUsuarios.get(0);
			logeado = true;
			message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Bienvenido", usuario);
			resultado = ReglasNavegacion.INICIO;
			logger.debug("El usuario " + usu.getAlias()
					+ " se ha autenticado !");
		} else {
			logeado = false;
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Error",
									"Usuario no valido, verifique usuario y contrasena!"));
			resultado = ReglasNavegacion.LOGIN;
			logger.debug("El usuario " + usu.getAlias()
					+ " NO se ha autenticado !");
		}

		return resultado;

	}

	public boolean esPermitido(String idOpcion) {
		// TODO validar opciones en el rol o roles del usuario logueado
		return true;
	}

	public String salir() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		// session.invalidate();
		this.logeado = false;
		return ReglasNavegacion.LOGIN;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public UsuarioVO getUsuariovo() {
		return usuariovo;
	}

	public void setUsuariovo(UsuarioVO usuariovo) {
		this.usuariovo = usuariovo;
	}

	public boolean isLogeado() {
		return logeado;
	}

	public void setLogeado(boolean logeado) {
		this.logeado = logeado;
	}

	public List<MensajeVO> getListaMensajesRecibidos() {
		return listaMensajesRecibidos;
	}

	public void setListaMensajesRecibidos(List<MensajeVO> listaMensajesRecibidos) {
		this.listaMensajesRecibidos = listaMensajesRecibidos;
	}

	public List<MensajeVO> getListaMensajesEnviados() {
		return listaMensajesEnviados;
	}

	public void setListaMensajesEnviados(List<MensajeVO> listaMensajesEnviados) {
		this.listaMensajesEnviados = listaMensajesEnviados;
	}
}