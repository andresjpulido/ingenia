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

	/**Se debe anotar que esta clase corresponde a un converter, así mismo
	 * se debe asignar un nombre a este converter mediante el atributo
	 * value en la anotación
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
	     * Este método se encarga de retornar el objeto seleccionado. Dado que en el método 
	     * de encapsulamiento (getAsString) habíamos definido el ID como representación, 
	     * mediante este ID se busca el objeto y se retorna.
	     * @param context El contexto de la aplicación
	     * @param component El componente que llama a este método
	     * @param value La representación que dimos al objeto en el método getAsString
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
	     * Este método se ejecuta en el momento que se cargan los ítems en el componente, 
	     * podríamos interpretarlo como el encapsulamiento del objeto;
	     * de este modo, se debe retornar una representación del objeto en forma de String, 
	     * en nuestro caso lo haremos mediante el ID. Así, por cada uno de los ítems del 
	     * componente tendremos su respectivo número de identificación.
	     * @param context El contexto de la aplicación
	     * @param component El componente que llama a este método
	     * @param value El ítem que estamos enviando; de este modo, este método se 
	     * ejecuta tantas veces como número de ítems del arreglo
	     * @return La representación en forma de String que queremos asignar al objeto
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

