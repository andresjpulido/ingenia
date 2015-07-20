/*
carga el script loading del init del juego
*/

var loading2 = document.createElement("SCRIPT");
loading2.src = "./Game/objetos/commons/libs.js";
loading2.setAttribute("language","javascript");
loading2.setAttribute("type","text/javascript");


var loading1 = document.createElement("SCRIPT");
loading1.src = "./Game/objetos/commons/clonador.js";
loading1.setAttribute("language","javascript");
loading1.setAttribute("type","text/javascript");




var body = document.getElementsByTagName("body")[0];
body.appendChild(loading2);
body.appendChild(loading1);



