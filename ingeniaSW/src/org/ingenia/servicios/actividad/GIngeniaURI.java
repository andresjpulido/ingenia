package org.ingenia.servicios.actividad;

public class GIngeniaURI {

	public static final String DUMMY_ACTIVIDAD = "/dummy";
	//public static final String GET_ACTIVIDAD = "/{idactividad}";
	public static final String GET_ALL_ACTIVIDAD = "/actividad";
	public static final String CREAR_ACTIVIDAD = "/actividad/crear";
	public static final String ANULAR_ACTIVIDAD = "/actividad/anular/{idactividad}";
	
	public static final String GET_ACTIVIDAD = "/Actividad/{IdActividad}";
	public static final String GET_ESTRUCTURAS_ACTIVIDAD = "/Estructura/PorActividad/{IdActividad}";
	public static final String GET_MOVIMIENTOS_ACTIVIDAD = "/Movimiento/PorActividad/{IdActividad}";
	public static final String GET_GATOS_ACTIVIDAD = "/Gato/PorActividad/{IdActividad}";
	public static final String REGISTRAR_AVANCE_ACTIVIDAD = "/ActividadxUsuario/";
}
