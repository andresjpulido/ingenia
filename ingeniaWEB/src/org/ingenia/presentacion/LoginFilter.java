package org.ingenia.presentacion;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ingenia.presentacion.beans.UsuarioMB;

@WebFilter(urlPatterns = { "/*" })
public class LoginFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String urlStr = null;
		UsuarioMB loginBean = null;
		boolean noProteger = false;

		// Obtengo el bean que representa el usuario desde el scope sesión
		loginBean = (UsuarioMB) req.getSession().getAttribute("usuarioMB");

		// Proceso la URL que está requiriendo el cliente
		urlStr = req.getRequestURL().toString().toLowerCase();
		noProteger = noProteger(urlStr);
		System.out.println(urlStr + " - desprotegido=[" + noProteger + "]");

		// Si no requiere protección continúo normalmente.
		if (noProteger) {
			chain.doFilter(request, response);
			return;
		}

		// El usuario no está logueado
		if (loginBean == null || !loginBean.isLogeado()) {
			res.sendRedirect(req.getContextPath() + "/login.xhtml");
			System.out.println("No logueado");
			return;
		}

		// El recurso requiere protección, pero el usuario ya está logueado.
		chain.doFilter(request, response);
		System.out.println("Logueado");

	}

	private boolean noProteger(String urlStr) {
 
		if (urlStr.indexOf("/login.xhtml") != -1 || urlStr.indexOf("/registro.xhtml") != -1)
			return true;
		if (urlStr.indexOf("/javax.faces.resource/") != -1)
			return true;
		return false;
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void destroy() {

	}

}