package org.ingenia.presentacion;


	import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.ingenia.comunes.vo.ArmaVO;
import org.ingenia.presentacion.beans.AdmGatoMB;

	
	@FacesConverter(value="armaConverter")
	public class ArmaConverter implements Converter{
	    
	  
	    private AdmGatoMB sessionBean;

	 
	    @Override
	    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	        if(value==null||value.equals("")){
	            return null;
	        }
	        sessionBean=(AdmGatoMB)context.getELContext().getELResolver().getValue(context.getELContext(), null, "AdmGatoMB");

	        int number = Integer.parseInt(value);	        
	        ArmaVO g = sessionBean.getArmaporID(number);
	        return g;
	    }
	    
	    
	    @Override
	    public String getAsString(FacesContext context, UIComponent component, Object value) {
	        if(value==null){
	            return "";
	        }
	        return ((ArmaVO) value).getIdarma() + "";
	    }
	}

