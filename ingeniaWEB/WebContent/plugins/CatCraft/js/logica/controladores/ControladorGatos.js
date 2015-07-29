//JavaScript Document - Albeiro Gualdrón - ControladorGatos
var gatosDelNivel = [];

function ControladorGatos( gatosAcrear )
{
	this.gatosAcrear = gatosAcrear;
	this.crearGatos = function()
	{
		this.gatosAcrear.length = 0;
		for(i =0; i< gatosAcrear.length; i++)
			gatosDelNivel.push( new Gato(gatosAcrear[i].color, gatosAcrear[i].tipo, gatosAcrear[i].arma, gatosAcrear[i].armadura) );
	}
}