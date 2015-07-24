package org.ingenia.presentacion;

import java.io.Serializable;
import java.util.Map;

import javax.faces.context.FacesContext;

public class BaseMB implements Serializable{

	
	private static final long serialVersionUID = -2921540408017493387L;

	 
    protected String recuperarParametro(String parametro){
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> map = context.getExternalContext().getRequestParameterMap();
        return map.get(parametro);

    }

   
    protected String recuperarIdParametro(String parametro){
        return recuperarParametro(parametro);
    }
	
}
