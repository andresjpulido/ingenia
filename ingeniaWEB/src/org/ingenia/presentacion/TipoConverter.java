package org.ingenia.presentacion;


	import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.ingenia.comunes.vo.TipoGatoVO;
import org.ingenia.presentacion.beans.AdmGatoMB;

	/**
	 *
	 * @author Manuel
	 */

	/**Se debe anotar que esta clase corresponde a un converter, as� mismo
	 * se debe asignar un nombre a este converter mediante el atributo
	 * value en la anotaci�n
	 **/
	@FacesConverter(value="tipoConverter")
	public class TipoConverter implements Converter{
	    
	    /**
	     * Se trae el SessionBean para manipular el listado de personal, 
	     * es importante recordar que este bean posee ahora el HashMap que
	     * simula una tabla de base de datos
	     */
	    private AdmGatoMB sessionBean;

	    /**
	     * Este m�todo se encarga de retornar el objeto seleccionado. Dado que en el m�todo 
	     * de encapsulamiento (getAsString) hab�amos definido el ID como representaci�n, 
	     * mediante este ID se busca el objeto y se retorna.
	     * @param context El contexto de la aplicaci�n
	     * @param component El componente que llama a este m�todo
	     * @param value La representaci�n que dimos al objeto en el m�todo getAsString
	     * @return El objeto seleccionado en el componente
	     */
	    @Override
	    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	        if(value==null||value.equals("")){
	            return null;
	        }
	        sessionBean=(AdmGatoMB)context.getELContext().getELResolver().getValue(context.getELContext(), null, "AdmGatoMB");

	        int number = Integer.parseInt(value);	        
	        TipoGatoVO g = sessionBean.getTipoporID(number);
	        return g;
	    }
	    
	    /**
	     * Este m�todo se ejecuta en el momento que se cargan los �tems en el componente, 
	     * podr�amos interpretarlo como el encapsulamiento del objeto;
	     * de este modo, se debe retornar una representaci�n del objeto en forma de String, 
	     * en nuestro caso lo haremos mediante el ID. As�, por cada uno de los �tems del 
	     * componente tendremos su respectivo n�mero de identificaci�n.
	     * @param context El contexto de la aplicaci�n
	     * @param component El componente que llama a este m�todo
	     * @param value El �tem que estamos enviando; de este modo, este m�todo se 
	     * ejecuta tantas veces como n�mero de �tems del arreglo
	     * @return La representaci�n en forma de String que queremos asignar al objeto
	     */
	    @Override
	    public String getAsString(FacesContext context, UIComponent component, Object value) {
	        if(value==null){
	            return "";
	        }
	        //GatoVO p=(GatoVO)value;
	        return ((TipoGatoVO) value).getIdTipoGato() + "";
	    }
	}

