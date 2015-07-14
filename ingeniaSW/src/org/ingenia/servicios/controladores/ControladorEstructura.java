package org.ingenia.servicios.controladores;


import java.util.ArrayList;

import jdk.internal.org.objectweb.asm.Type;

import org.ingenia.comunes.vo.GatoVO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
@RequestMapping("/rest/Estructura")
public class ControladorEstructura {

	@RequestMapping(value = "/{IdActividad}", method = RequestMethod.GET)
	public String getEstructura(@PathVariable String IdActividad) {
		String result="";//"IdActividad: "+IdActividad;		
		
		//consulta a BD
//		ArrayList<GatoVO> gatos = new ArrayList<GatoVO>(); 		
//		
//		for (int j = 0; j < 12; j++) {
//			GatoVO gato = new GatoVO();
//			gato.setIdgato(j);
//			gato.setColor("rojo");
//			gato.setDefensa("45");
//			gato.setOrden("15");
//			gato.setTipogato(1);
//			gatos.add(gato);
//		}
//				
		//TODO: implementar jackson
		//Gson serializador = new Gson();
        //return serializador.toJson(gatos);
        return "vida";
	}
}