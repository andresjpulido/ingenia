//JavaScript Document - Albeiro Gualdrón - Arma

function Arma(nombre)
{
	this.secuenciaGolpe = [];
	this.nombre = nombre;
	var efectividad;
	this.calcularEfectividad = function()
	{
		if( nombre === "puño")
		{
			efectividad = "azul";
			this.secuenciaGolpe = [143, 144, 145, 146, 147, 148];
		}
		else if( nombre === "patada")
		{
			efectividad = "verde";
			this.secuenciaGolpe = [156, 150, 151, 152, 154, 155];
		}
		else if( nombre === "bate")
		{
			efectividad = "rojo";
			this.secuenciaGolpe = [141, 136, 137, 138, 139, 140];
		}
		else if( nombre === "pistola")
		{
			efectividad = "amarillo";
			this.secuenciaGolpe = [162, 157, 158, 159, 160, 161];
		}
		else if( nombre === "hado-ken")
		{
			efectividad = "amarillo_arma";
			this.secuenciaGolpe = [173, 168, 169, 170, 171, 172];
		}
		else if( nombre === "sierra")
		{
			efectividad = "verde_arma";
			this.secuenciaGolpe = [173, 168, 169, 170, 171, 172];
		}
		else if( nombre === "bomba")
		{
			efectividad = "rojo_arma";
			this.secuenciaGolpe = [177, 174, 175, 176, 179, 178];
		}
		else if( nombre === "espada")
		{
			efectividad = "azul_arma";
			this.secuenciaGolpe = [186, 180, 181, 183, 184, 185];
		}
		else if( nombre === "hacha")
		{
			efectividad = "verde_escudo";
			this.secuenciaGolpe = [192, 187, 188, 189, 190, 191];
		}
		else if( nombre === "mazo")
		{
			efectividad = "rojo_escudo";
			this.secuenciaGolpe = [198, 193, 194, 195, 196, 197];
		}
		else if( nombre === "martillo")
		{
			efectividad = "azul_escudo";
			this.secuenciaGolpe = [143, 144, 145, 146, 147, 148];
		}
		else if( nombre === "zarpa")
		{
			efectividad = "amarillo_escudo";
			this.secuenciaGolpe = [143, 144, 145, 146, 147, 148];
		}
		else if( nombre === "cañon")
		{
			efectividad = "azul_arma_escudo";
			this.secuenciaGolpe = [143, 144, 145, 146, 147, 148];
		}
		else if( nombre === "perro")
		{
			efectividad = "amarillo_arma_escudo";
			this.secuenciaGolpe = [143, 144, 145, 146, 147, 148];
		}
		else if( nombre === "laser")
		{
			efectividad = "verde_arma_escudo";
			this.secuenciaGolpe = [143, 144, 145, 146, 147, 148];
		}
		else if( nombre === "arco")
		{
			efectividad = "rojo_arma_escudo";
			this.secuenciaGolpe = [143, 144, 145, 146, 147, 148];
		}
		else 
			efectividad = "inefecctiva";
		return efectividad;
	}	
}