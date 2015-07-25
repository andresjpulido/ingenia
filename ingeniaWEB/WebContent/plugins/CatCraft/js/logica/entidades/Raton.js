//JavaScript Document - Albeiro Gualdrón - Ratón
var codigoCompleto = [];

function Raton()
{
	this.nombre = "ratita";
	this.ejecucion = [];
	this.puntajeAcumulado = 0;
	
	this.cargarFunciones = function()
	{
		for(i = 0; i < 32;i++)
		{
			if(botonesDrag1y2[i].img !== -1)
				this.ejecucion.push(funcionesFx[(botonesDrag1y2[i].img-16)]);
		}
	}
//-----------------------------------------------------------------------------------
	
	
	this.setPuntajeAcumulado = function(puntaje)
	{
		this.puntajeAcumulado+=puntaje;
	}	
//-----------------------------------------------------------------------------------
}