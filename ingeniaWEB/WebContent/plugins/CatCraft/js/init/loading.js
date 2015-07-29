/*
carga el script loading del init del juego
 */
var Cargados=0;

window.onload = function() {
	var hostUrl = "/ingeniaWEB/plugins/CatCraft/";
	var Rutas = [
	"js/framework/jquery-1.11.3.min.js",
	"js/init/vglobales.js",
	"js/commons/libs.js",
	"js/commons/clonador.js",
	"js/logica/entidades/Arma.js",
	"js/logica/entidades/Boton.js",
	"js/logica/entidades/IfSentencia.js",
	"js/logica/entidades/InterpreteCodigo.js",
	"js/logica/entidades/MatrizAreasClick.js",
	"js/logica/entidades/Gato.js",
	"js/logica/entidades/Raton.js",
	"js/logica/entidades/Dibujante.js",
	"js/logica/controladores/ControladorEvMouse.js",
	"js/logica/controladores/ControladorAjax.js",
	"js/logica/controladores/ControladorGatos.js",
	"js/logica/controladores/ControladorAnimacion.js",
	"js/logica/controladores/ControladorJuego.js"
	             ];
	
	var head = document.getElementsByTagName("head")[0];
	
	for (var ruta in Rutas) {
		var loading = document.createElement("SCRIPT");
		loading.src = hostUrl + Rutas[ruta];
		loading.setAttribute("language", "javascript");
		loading.setAttribute("type", "text/javascript");
		loading.onload = function(){ Carga(Rutas.length);};
		head.appendChild(loading);
	}
}

//Si termino la carga de los scripts pintamos el canvas e iniciamos el juego 
function Carga(total)
{
	Esperar();
	Cargados++;
	if(Cargados==total)
	{
		var htmlCanvas = '<canvas id="canvas" width="800" height="500">Your browser does not support the HTML5 canvas tag.</canvas>';
		$("#ContenidoActividad").html(htmlCanvas);
		var controlador = new ControladorJuego();
		controlador.CargaInicial();
	}
}

function Esperar()
{
	if (typeof jQuery === "undefined" || Juego.Gatos === "undefined" ) {//|| botonesNext1y2 === "undefined"
        // Vuelve a intentarlo dentro de 100ms
        setTimeout(Esperar,100);
        return;
    }
}