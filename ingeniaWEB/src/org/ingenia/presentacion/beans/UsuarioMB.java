package org.ingenia.presentacion.beans;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.MensajeVO;
import org.ingenia.comunes.vo.OpcionVO;
import org.ingenia.comunes.vo.RolVO;
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
	
	private MensajeVO mensajeRecibido;

	private List<MensajeVO> listaMensajesEnviados;
	
	private MensajeVO mensajeEnviado;
	
	private String mensaje;

	private HttpServletRequest httpServletRequest = null;

	private FacesContext faceContext = null;

	@EJB
	private IGestorUsuariosLocal gestorUsuarios;

	private static Logger logger = LogManager.getLogger(UsuarioMB.class);

	public String autenticar(ActionEvent event) {
		RequestContext context = RequestContext.getCurrentInstance();
		logeado = false;
		String resultado = null;
		List<UsuarioVO> listaUsuarios = null;

		UsuarioVO usu = new UsuarioVO();
		usu.setAlias(usuario);
		usu.setClave(clave);
		listaUsuarios = gestorUsuarios.consultarUsuarios(usu);

		if (listaUsuarios != null && !listaUsuarios.isEmpty()) {
			this.usuariovo = listaUsuarios.get(0);
			
			if(this.usuariovo.isActivo()){
				logeado = true;

				resultado = ReglasNavegacion.INICIO;
				logger.debug("El usuario " + usu.getAlias()
						+ " se ha autenticado !");
				
				try {
					this.usuariovo.setFechaUltimoIngreso(new Date());
					gestorUsuarios.crearUsuario(this.usuariovo);
				} catch (AdaptadorException e) {
					e.printStackTrace();
				}

				// guarda el usuario para la sesion
				faceContext = FacesContext.getCurrentInstance();
				httpServletRequest = (HttpServletRequest) faceContext
						.getExternalContext().getRequest();
				httpServletRequest.getSession().setAttribute("sessionUsuario",
						this.usuariovo);

				// FacesContext.getCurrentInstance().addMessage(null, message);
				// context.addCallbackParam(ReglasNavegacion.INICIO, this);
				context.addCallbackParam("estaLogeado", logeado);
				context.addCallbackParam("view", "paginas/inicio.xhtml");
				return resultado;

			}else{
				logeado = false;
				FacesContext
						.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(FacesMessage.SEVERITY_ERROR,
										"Error",
										"Usuario no activo, consulte con el administrador!"));
				resultado = ReglasNavegacion.LOGIN;
				logger.debug("El usuario " + usu.getAlias()
						+ " esta inactivo !");

				this.clave = "";
			}

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

			this.clave = "";
		}

		return resultado;

	}

	public boolean esPermitido(String idOpcion) {
		// TODO validar opciones en el rol o roles del usuario logueado
		// iterar sobre todos los roles y sus opciones hasta q se encuentre
		// el idopcion q llega.
		if (this.usuariovo.getListaRoles() != null
				&& !this.usuariovo.getListaRoles().isEmpty()) {
			for (RolVO rolVO : this.usuariovo.getListaRoles()) {
				if (rolVO.getOpcions() != null && !rolVO.getOpcions().isEmpty()) {
					for (OpcionVO opcionVO : rolVO.getOpcions()) {
						if (opcionVO != null
								&& String.valueOf(opcionVO.getCodigo()).equals(
										idOpcion))
							return true;
					}
				}

			}
		}

		return false;
	}

	public String salir() {
		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		session.removeAttribute("sessionUsuario");
		// session.invalidate();
		this.logeado = false;
		this.usuariovo = null;
		return ReglasNavegacion.LOGIN;
	}
	
	public void cargarMensajes(){
		
		try {
			listaMensajesRecibidos=this.gestorUsuarios.consultarMensajesRecibidos(this.usuariovo);
			listaMensajesEnviados=this.gestorUsuarios.consultarMensajesEnviados(this.usuariovo);
			System.out.println("cantidad "+ listaMensajesRecibidos.size());
		} catch (AdaptadorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void responderMensaje(){

		try {
			this.gestorUsuarios.enviarMensaje(this.mensajeRecibido.getRemitente(),this.mensajeRecibido.getDestinatario(),this.mensaje);
			listaMensajesEnviados=this.gestorUsuarios.consultarMensajesEnviados(this.usuariovo);
			listaMensajesRecibidos=this.gestorUsuarios.consultarMensajesRecibidos(this.usuariovo);
			 FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Exito",  "Mensaje Enviado" ) );
		} catch (AdaptadorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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


	public List<MensajeVO> getListaMensajesEnviados() {
		return listaMensajesEnviados;
	}

	public void setListaMensajesEnviados(List<MensajeVO> listaMensajesEnviados) {
		this.listaMensajesEnviados = listaMensajesEnviados;
	}

	public List<MensajeVO> getListaMensajesRecibidos() {
		return listaMensajesRecibidos;
	}

	public void setListaMensajesRecibidos(List<MensajeVO> listaMensajesRecibidos) {
		this.listaMensajesRecibidos = listaMensajesRecibidos;
	}

	public MensajeVO getMensajeRecibido() {
		return mensajeRecibido;
	}

	public void setMensajeRecibido(MensajeVO mensajeRecibido) {
		this.mensajeRecibido = mensajeRecibido;
	}

	public MensajeVO getMensajeEnviado() {
		return mensajeEnviado;
	}

	public void setMensajeEnviado(MensajeVO mensajeEnviado) {
		this.mensajeEnviado = mensajeEnviado;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}