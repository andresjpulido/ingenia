package org.ingenia.presentacion;

import java.io.Serializable;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.ingenia.comunes.vo.CursoVO;

public class BaseMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2921540408017493387L;

	  /**
     * Recupera el valor asociado a un parametro de la página JSF
     * @param parametro
     * @return
     */
    protected String recuperarParametro(String parametro){
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> map = context.getExternalContext().getRequestParameterMap();
        return map.get(parametro);

    }

    /**
     * Recupera el id de tipo long asociado a un parametro de la página JSF
     * @param parametro
     * @return
     */
    protected String recuperarIdParametro(String parametro){
        return recuperarParametro(parametro);
    }
	
}
