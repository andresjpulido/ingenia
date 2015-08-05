//JavaScript Document - Albeiro Gualdrón - ControladorGatos
var gatosDelNivel = [];

function ControladorGatos( gatosAcrear )
{
	this.gatosAcrear = gatosAcrear;
	
	
	this.crearGatos = function()
	{
		gatosDelNivel.length = 0;
		for(i =0; i< gatosAcrear.length; i++)
			gatosDelNivel.push( new Gato(gatosAcrear[i].color, gatosAcrear[i].tipo, gatosAcrear[i].armado, gatosAcrear[i].armadura.idarmadura) );
	};
	
	this.setGatosAcrear = function(gatosAcrear)
	{
		this.gatosAcrear = gatosAcrear;
	};
	
	
}