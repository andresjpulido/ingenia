/*
carga el script loading del init del juego
*/

var loading0 = document.createElement("SCRIPT");
loading0.src = hostUrl + "/js/logica/controladores/ControladorJuego.js";
loading0.setAttribute("language", "javascript");
loading0.setAttribute("type", "text/javascript");

var loading1 = document.createElement("SCRIPT");
loading1.src = hostURL + "/js/logica/controladores/ControladorAjax.js";
loading1.setAttribute("language","javascript");
loading1.setAttribute("type","text/javascript");

var loading2 = document.createElement("SCRIPT");
loading2.src = hostURL + "/js/logica/controladores/ControladorAnimacion.js";
loading2.setAttribute("language","javascript");
loading2.setAttribute("type","text/javascript");

var loading3 = document.createElement("SCRIPT");
loading3.src = hostURL + "/js/logica/entidades/Colision.js";
loading3.setAttribute("language","javascript");
loading3.setAttribute("type","text/javascript");

var loading4 = document.createElement("SCRIPT");
loading4.src = hostURL + "/js/logica/entidades/Enemigo.js";
loading4.setAttribute("language", "javascript");
loading4.setAttribute("type", "text/javascript");

var loading5 = document.createElement("SCRIPT");
loading5.src = hostURL + "/js/logica/entidades/Gato.js";
loading5.setAttribute("language", "javascript");
loading5.setAttribute("type", "text/javascript");

var loading6 = document.createElement("SCRIPT");
loading6.src = hostURL + "/js/logica/entidades/Raton.js";
loading6.setAttribute("language", "javascript");
loading6.setAttribute("type", "text/javascript");

var body = document.getElementsByTagName("body")[0];
body.appendChild(loading1);
body.appendChild(loading2);
body.appendChild(loading0);


