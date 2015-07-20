/*
carga el script loading del init del juego
*/

var loading1 = document.createElement("SCRIPT");
loading1.src = "./Game/objetos/logo/cadena.js";
loading1.setAttribute("language","javascript");
loading1.setAttribute("type","text/javascript");

var loading2 = document.createElement("SCRIPT");
loading2.src = "./Game/objetos/logo/logo.js";
loading2.setAttribute("language","javascript");
loading2.setAttribute("type","text/javascript");

var loading3 = document.createElement("SCRIPT");
loading3.src = "./Game/objetos/logo/up.js";
loading3.setAttribute("language","javascript");
loading3.setAttribute("type","text/javascript");


var body = document.getElementsByTagName("body")[0];

body.appendChild(loading1);
body.appendChild(loading2);
body.appendChild(loading3);



