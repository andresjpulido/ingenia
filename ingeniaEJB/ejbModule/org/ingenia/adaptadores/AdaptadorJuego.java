package org.ingenia.adaptadores;

import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.JuegoVO;
import org.ingenia.negocio.entidades.Juego;

public class AdaptadorJuego extends IAdaptadorJuego{

	
	public AdaptadorJuego(Juego Juego) {
		this.juego = Juego;
	}

	public AdaptadorJuego(JuegoVO juegoVO) {
		this.juegoVO = juegoVO;
	}
	
	@Override
	public Juego getJuego() throws AdaptadorException {
		Juego juego = null;
		if (juegoVO == null)
			return null;
		juego = new Juego();
		juego.setIdjuego(juegoVO.getIdjuego());
		juego.setNombre(juegoVO.getNombre());
		return juego;
	}

	@Override
	public JuegoVO getJuegoVO() throws AdaptadorException {
		JuegoVO juegoVO = null;
		if (juego == null)
			return null;
		juegoVO = new JuegoVO();
		juegoVO.setIdjuego(juego.getIdjuego());
		juegoVO.setNombre(juego.getNombre());
		return juegoVO;
	}

}
