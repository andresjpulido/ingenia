$(document).ready(function () {
	//creamos el canvas para el juego
	var htmlCanvas = '<canvas id="canvas" width="800" height="500">Your browser does not support the HTML5 canvas tag.</canvas>';
	$("#ContenidoActividad").html(htmlCanvas);
     var controlador = new ControladorJuego();
     controlador.CargaInicial();
});
