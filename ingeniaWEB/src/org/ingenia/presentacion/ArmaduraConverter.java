package org.ingenia.presentacion;


	import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.ingenia.comunes.vo.ArmaduraVO;
import org.ingenia.presentacion.beans.AdmGatoMB;

	
	
	@FacesConverter(value="armaduraConverter")
	public class ArmaduraConverter implements Converter{
	    
	    
	    private AdmGatoMB sessionBean;

	   
	    @Override
	    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	        if(value==null||value.equals("")){
	            return null;
	        }
	        sessionBean=(AdmGatoMB)context.getELContext().getELResolver().getValue(context.getELContext(), null, "AdmGatoMB");

	        int number = Integer.parseInt(value);	        
	        ArmaduraVO g = sessionBean.getArmaduraporID(number);
	        return g;
	    }
	    
	   
	    @Override
	    public String getAsString(FacesContext context, UIComponent component, Object value) {
	        if(value==null){
	            return "";
	        }
	        return ((ArmaduraVO) value).getIdarmadura() + "";
	    }
	}

