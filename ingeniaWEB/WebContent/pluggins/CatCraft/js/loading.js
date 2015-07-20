/*
carga el script loading del init del juego
*/

var hostUrl = window.location.host+"/ingeniaWEB/pluggins/";

var loadingjq = document.createElement("SCRIPT");
loadingjq.src = hostUrl+ 'js/framework/jquery-1.11.3.min.js";
loadingjq.setAttribute("language","javascript");
loadingjq.setAttribute("type","text/javascript");

var loading1 = document.createElement("SCRIPT");
loading1.src = "js/init/vglobales.js";
loading1.setAttribute("language","javascript");
loading1.setAttribute("type","text/javascript");

var loading2 = document.createElement("SCRIPT");
loading2.src = "js/init/loader.js";
loading2.setAttribute("language","javascript");
loading2.setAttribute("type","text/javascript");

var loading3 = document.createElement("SCRIPT");
loading3.src = "js/logica/loading.js";
loading3.setAttribute("language", "javascript");
loading3.setAttribute("type", "text/javascript");

window.onload = function (){
var body = document.getElementsByTagName("body")[0];
body.appendChild(loadingjq);
body.appendChild(loading1);
body.appendChild(loading2);
body.appendChild(loading3);
}


