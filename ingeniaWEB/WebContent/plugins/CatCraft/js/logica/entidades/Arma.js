//JavaScript Document - Albeiro Gualdrón - Arma

function Arma(nombre)
{

	this.nombre = nombre;
	var efectividad;
	this.calcularEfectividad = function()
	{
		if( nombre === "puño")
		{
			efectividad = "azul";
		}
		else if( nombre === "patada")
		{
			efectividad = "verde";
		}
		else if( nombre === "bate")
		{
			efectividad = "rojo";
		}
		else if( nombre === "pistola")
		{
			efectividad = "amarillo";
		}
		else if( nombre === "hado-ken")
		{
			efectividad = "amarillo_arma";
		}
		else if( nombre === "sierra")
		{
			efectividad = "amarillo_escudo";
		}
		else if( nombre === "bomba")
		{
			efectividad = "rojo_arma";
		}
		else if( nombre === "espada")
		{
			efectividad = "rojo_escudo";
		}
		else if( nombre === "hacha")
		{
			efectividad = "verde_escudo";
		}
		else if( nombre === "mazo")
		{
			efectividad = "verde_arma";
		}
		else if( nombre === "martillo")
		{
			efectividad = "azul_escudo";
		}
		else if( nombre === "zarpa")
		{
			efectividad = "azul_arma";
		}
		else if( nombre === "cañon")
		{
			efectividad = "azul_arma_escudo";
		}
		else if( nombre === "perro")
		{
			efectividad = "amarillo_arma_escudo";
		}
		else if( nombre === "laser")
		{
			efectividad = "verde_arma_escudo";
		}
		else if( nombre === "arco")
		{
			efectividad = "rojo_arma_escudo";
		}
		else 
			efectividad = "inefecctiva";
		return efectividad;
	}	
}