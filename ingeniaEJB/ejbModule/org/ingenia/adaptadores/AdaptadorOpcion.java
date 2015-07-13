package org.ingenia.adaptadores;


import org.ingenia.comunes.excepcion.AdaptadorException;
import org.ingenia.comunes.vo.OpcionVO;
import org.ingenia.negocio.entidades.Opcion;

public class AdaptadorOpcion extends IAdaptadorOpcion {

	public AdaptadorOpcion(Opcion rol) {
		this.opcion = rol;
	}

	public AdaptadorOpcion(OpcionVO rolVO) {
		this.opcionVO = rolVO;
	}

	@Override
	public Opcion getOpcion() throws AdaptadorException {
		Opcion opcion = null;

		if (opcionVO == null)
			return null;

		opcion = new Opcion();
		opcion.setNombre(opcionVO.getNombre());
		opcion.setDescripcion(opcionVO.getDescripcion());
		opcion.setIdopcion(opcionVO.getIdopcion());
		return opcion;
	}

	@Override
	public OpcionVO getOpcionVO() throws AdaptadorException {
		OpcionVO opcionVO = null;

		if (opcion == null)
			return null;

		opcionVO = new OpcionVO();
		opcionVO.setNombre(opcion.getNombre());
		opcionVO.setDescripcion(opcion.getDescripcion());
		opcionVO.setIdopcion(opcion.getIdopcion());

		return opcionVO;
	}

}
