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
@RequestMapping("/rest/ActividadxUsuario")
public class ControladorActividadxUsuario {

	@RequestMapping(value = "/{IdActividad}", method = RequestMethod.POST)
	public String getActividadxUsuario(@PathVariable String IdActividad) {
		String result="";//"IdActividad: "+IdActividad;		
		
		//gurdar en BD

		//TODO: implementar jackson
		//Gson serializador = new Gson();
        //return serializador.toJson(gatos);
        return "vida";
	}
}