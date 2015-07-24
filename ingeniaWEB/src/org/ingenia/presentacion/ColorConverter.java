package org.ingenia.presentacion;


	import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.ingenia.comunes.vo.ColorVO;
import org.ingenia.comunes.vo.GatoVO;
import org.ingenia.presentacion.beans.AdmGatoMB;

	
	
	@FacesConverter(value="colorConverter")
	public class ColorConverter implements Converter{
	    
	    
	    private AdmGatoMB sessionBean;

	   
	    @Override
	    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	        if(value==null||value.equals("")){
	            return null;
	        }
	        sessionBean=(AdmGatoMB)context.getELContext().getELResolver().getValue(context.getELContext(), null, "AdmGatoMB");

	        int number = Integer.parseInt(value);	        
	        ColorVO g = sessionBean.getColorporID(number);
	        return g;
	    }
	    
	   
	    @Override
	    public String getAsString(FacesContext context, UIComponent component, Object value) {
	        if(value==null){
	            return "";
	        }
	        return ((ColorVO) value).getIdcolor() + "";
	    }
	}

