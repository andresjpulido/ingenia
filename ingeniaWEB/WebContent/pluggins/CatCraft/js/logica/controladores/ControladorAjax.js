// JavaScript Document
/*
** FECHA CREACION : 07/07/2015
** CREADOR: 
** DESCRIPCION: 
*/

var ControladorAjax = function () {
    this.UrlIngeniaSW = "http://localhost:8080/ingeniaSW";


    //Encapsula la funcionalidad de JQuery para  hacer peticiones usando Ajax
    this.HacerLlamadoAjax = function (Url, RequestType, Parametros, SuccessFunction, OnError) {
        jQuery.ajax({
            timeout: 900000, //15 minutos
            url: Url,
            data: Parametros,
            dataType: 'json',
            type: RequestType, //'POST',
            contentType: "application/json; charset=utf-8",
            success: function (rawdata) {
                var jsonresponse = new ControladorAjax().ParsearRespuesta(rawdata, SuccessFunction, true);
            },
            error: function (rawdata) {
                var jsonresponse = new ControladorAjax().ParsearRespuesta(rawdata, OnError, false);
            }
        });
    };

    this.ParsearRespuesta = function (rawdata, callback, successfull) {

        if (!successfull) {
            var responseText = rawdata.responseText;

            //Si al respuesta del servidor no es exitosa o es nula mostramos error y terminamos
            if (rawdata.status !== 200 || typeof rawdata === "undefined" || rawdata == null || typeof responseText === "undefined" || responseText == null) {
                $("body").append("<span id='spanError'>" + responseText + "<\span>");
                // alert(responseText);
                return false;
            }

            var jsonresponse = jQuery.parseJSON(responseText);
            //OnError(jsonresponse);
            callback(jsonresponse);
        }
        else callback(rawdata);
    };

    this.ManejarError = function (data) {
        alert(data);
        return false;
    };

    //metodo que trae todos los enemigos (gatos)configurados para la actividad actual
    this.CargarGatos = function (IdActividadActual) {

        var MetodoServicio = "/Gato/IdActividad";
        var Parametros = null; //Como hacemos un llamado get no va data pues no hay bodycontent
        this.HacerLlamadoAjax(this.UrlIngeniaSW + MetodoServicio.replace("IdActividad", IdActividadActual), "GET", Parametros, this.CargarGatosCallback, this.ManejarError);
    };

    //Si la respuesta es exitosa, cargamos los gatos en memoria
    this.CargarGatosCallback = function (data) {
        Juego.Gatos = data.Gatos;
    };

    //metodo que trae todos los enemigos (gatos)configurados para la actividad actual
    this.CargarEstructurasPermitidas = function (IdActividadActual) {

        var MetodoServicio = "/Estructura/IdActividad";
        var Parametros = null; //Como hacemos un llamado get no va data pues no hay bodycontent
        this.HacerLlamadoAjax(this.UrlIngeniaSW + MetodoServicio.replace("IdActividad", IdActividadActual), "GET", Parametros, this.CargarEstructurasPermitidasCallback, this.ManejarError);
    };

    //Si la respuesta es exitosa, cargamos las estructuras de programaci�n disponibles para la actividad en memoria
    this.CargarEstructurasPermitidasCallback = function (data) {
        Juego.Estructuras = data.Estructuras;
    };

    //metodo que trae todos los movimientos permitidos para la actividad actual
    this.CargarMovimientosPermitidos = function (IdActividadActual) {

        var MetodoServicio = "/Movimiento/IdActividad";
        var Parametros = null; //Como hacemos un llamado get no va data pues no hay bodycontent
        this.HacerLlamadoAjax(this.UrlIngeniaSW + MetodoServicio.replace("IdActividad", IdActividadActual), "GET", Parametros, this.CargarMovimientosPermitidosCallback, this.ManejarError);
    };

    //Si la respuesta es exitosa, cargamos los movimientos en memoria
    this.CargarMovimientosPermitidosCallback = function (data) {
        Juego.Movimientos = data.Movimientos;
    };

    //metodo que trae la data de la actividad actual
    this.CargarActividad = function (IdActividadActual) {

        var MetodoServicio = "/Actividad/IdActividad";
        var Parametros = null; //Como hacemos un llamado get no va data pues no hay bodycontent
        this.HacerLlamadoAjax(this.UrlIngeniaSW + MetodoServicio.replace("IdActividad", IdActividadActual), "GET", Parametros, this.CargarActividadCallback, this.ManejarError);
    };

    //Si la respuesta es exitosa, cargamos el objeto actividad en memoria y el limite de movimientos permitidos
    this.CargarActividadCallback = function (data) {
        Juego.Actividad = data.Actividad;
        Juego.MaximoMovimientos = data.Actividad.limite_movimientos;
    };

    //metodo que trae la data de la actividad actual
    this.RegistrarAvance = function (IdActividadActual, IdUsuarioActual, Puntos, Intentos) {

        var MetodoServicio = "/ActividadxUsuario/";
        var Parametros = JSON.stringify({
            IdActividad: IdActividadActual,
            IdUsuario: IdUsuarioActual,
            Fecha: Date().toString(),
            Puntos: Puntos,
            NumeroIntento:Intentos
        });
        this.HacerLlamadoAjax(this.UrlIngeniaSW + MetodoServicio, "POST", Parametros, this.RegistrarAvanceCallback, this.ManejarError);
    };

    //Si la respuesta es exitosa, Actualizamos los puntos de modo que se le muestren al estudiante (jugador)
    this.RegistrarAvanceCallback = function (data) {
        Juego.Puntos = Juego.Puntos + data.Puntos;
    };

}     ///END CLASS