// JavaScript Document
/*
** FECHA CREACION : 07/07/2015
** CREADOR: 
** DESCRIPCION: 
*/
var Cargado = true;
var cantidadImagenes = 91;
var imagenes = new Array();

var ControladorJuego = function () {

    this.CargaInicial = function () {
        //Hacemos los llamados al servicio Rest, para cargar en memoria las variables configuradas por el profesor
        Juego.IdActividad = $("#IdActividad").val();
        Juego.IdUsuario = $("#IdUsuario").val();
        Juego.IdCurso = $("#IdCurso").val();
        controlador = new ControladorAjax();
        controlador.CargarActividad(Juego.IdActividad);
        controlador.CargarGatos(Juego.IdActividad);
        controlador.CargarEstructurasPermitidas(Juego.IdActividad);
        controlador.CargarMovimientosPermitidos(Juego.IdActividad);
        this.CargarImagenes();
        //var controladorAnimacion = new ControladorAnimacion();
        //controladorAnimacion.iniciar();
        iniciar();
    };

    this.CargarImagenes = function()
    {
    	var hostUrl = "/ingeniaWEB/plugins/CatCraft/imagenes/";
    	
    	for (var i = 0; i <= 91; i++ )
    	{
    		var imagen = new Image();
    		imagen.src = hostUrl + 'Imagen'+i+'.png';
    		imagenes.push(imagen);
    	}
    }
    
    //Calculamos los puntos del jugador
    this.CalcularPuntos = function () {
        //Primero calculamos los puntos por pasar la actividad
        var Puntos = Juego.Actividad.Puntos;

        //calculo por cada gato vencido
        $.each(Juego.Gatos, function (indice, gato) {
            //calculo por el tipo de gato
            Puntos += gato.Puntos;
            //calculo por armas
            if (gato.Armas != undefined) {
                $.each(gato.Armas, function (indice, arma) {
                    Puntos += arma.Puntos;
                });
            }
            //calculo por armaduras
            if (gato.Armaduras != undefined) {
                $.each(gato.Armaduras, function (indice, armadura) {
                    Puntos += armadura.Puntos;
                });
            }
        });

        //calculamos puntos que se restan por reintentos
        var PorcentajeIntentos = Puntos * (Juego.MovimientosRealizados - 1) * 0.05;
        Puntos -= PorcentajeIntentos;
        return Puntos;
    };

    this.EvaluarEjecucion = function () {
        var Ganador = false;
        var Terminado = false;
        //ejecuto por cada enemigo que va apareciendo, si no puedo vencer uno notifico y detengo
        $.each(Juego.Gatos, function (indice, gato) {
            if (!Terminado && Juego.EstructurasEscogidas != undefined) {
                $.each(Juego.EstructurasEscogidas, function (indice, estructura) {
                    if (!estructura.Ejecutada) {
                        estructura.Ejecutada = true;
                        switch (estructura.id) {
                            //If            
                            case 1:
                                Ganador = new ControladorJuego().EjecutarIf(estructura, gato);
                                if (Ganador == false) Terminado = true; return false;
                                break;
                            default:
                                Ganador = false;
                                break;
                        }
                    }
                });
            }
        });

        //notificamos al servicio el fin de un intento 
        //y al canvas para que pinte de acuerdo al resultado
        this.NotificarEjecucion(Ganador);

        return Ganador;
    };

    this.EjecutarIf = function (estructuraIf, gato) {
        var Ganador = false;

        //estructura del If
        var Izquierda = estructuraIf.ComparacionIzquierda;
        var Derecha = estructuraIf.ComparacionDerecha;
        var movimiento = estructuraIf.Movimiento;

        //validar que los elementos sean validos


        //evaluar tipo de gato
        if (Izquierda === "1")//1 gato, 2 arma, 3 armadura
        {
            if (gato.color == Derecha.color && gato.tipo == Derecha.tipo) //si el elemento a comparar es igual al que viene
            {
                var EfectivoColor = movimiento.Efectivo.indexOf(gato.color) == -1 ? false : true;
                var EfectivoTipo = movimiento.Efectivo.indexOf(gato.tipo) == -1 ? false : true;

                //si el movimiento seleccionado no es efectivo no contra el tipo de gato, ni contra el color
                if (EfectivoColor || EfectivoTipo)
                    Ganador = true; //si no coincide el jugador pierde
            }
        }
        else
        //evaluar arma
            if (Izquierda === "2") {
                //calculo por armas
                if (gato.Armas != undefined) {
                    $.each(gato.Armas, function (indice, arma) {
                        var Efectivo = movimiento.Efectivo.indexOf(arma.Nombre) == -1 ? false : true;

                        //si el movimiento seleccionado no es efectivo ni contra el tipo de gato, ni contra el color
                        if (Efectivo)
                            Ganador = true; //si no coincide el jugador pierde
                    });
                }
            }
            else
            //evaluar armadura
                if (Izquierda === "3") {
                    //calculo por armaduras
                    if (gato.Armaduras != undefined) {
                        $.each(gato.Armaduras, function (indice, armadura) {
                            var Efectivo = movimiento.Efectivo.indexOf(armadura.Nombre) == -1 ? false : true;

                            //si el movimiento seleccionado no es efectivo no contra el tipo de gato, ni contra el color
                            if (Efectivo)
                                Ganador = true; //si no coincide el jugador pierde
                        });
                    }
                }

        return Ganador;
    };

    this.NotificarEjecucion = function (Ganador) {
        //Instanciamos las clases controlador
        var controladorAjax = new ControladorAjax();
        var controladorAnimacion = new ControladorAnimacion();

        //Ejecutamos la parametrizacion del jugador sobre la actividad
        //var Ganador = this.EvaluarEjecucion();

        //Actualizamos la variable global de intentos
        Juego.IntentosRealizados++;

        if (Ganador) {
            //Si el jugador gano, calculamos los puntos, registramos el avance y pintamos la victoria
            Juego.Puntos = Math.round(this.CalcularPuntos());

            controladorAjax.RegistrarAvance(Juego.IdActividad, Juego.IdUsuario, Juego.Puntos, Juego.IntentosRealizados);
            controladorAnimacion.AnimarVictoria();
        }
        else {
            //controladorAjax.RegistrarAvance(Avance);
            //controladorAjax.RegistrarIntentoFallido(Juego.IdActividad, IdUsuario, Puntos, Juego.IntentosRealizados);
            controladorAnimacion.AnimarDerrota();
        }
    };
}                 ///END CLASS

