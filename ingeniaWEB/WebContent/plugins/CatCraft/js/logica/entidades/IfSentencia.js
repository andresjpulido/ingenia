//JavaScript Document - Albeiro Gualdrón - SenteciaIf

function sentenciaIf( nombre )
{

	this.sentenciaString = [];
	sentenciaString = this.sentenciaString; 
	this.nombre = nombre;
	var ifObjetos = [];
	this.if_elseif_else = function()
	{ 
		interpretarInformacionIF();
		interpretarInformacionELSEIF();
		interpretarInformacionELSE();
	}
//---------------------------------------------------------------------------------------------

	this.if_else = function()
	{
		interpretarInformacionIF();
		interpretarInformacionELSE();
	}
	
//---------------------------------------------------------------------------------------------

	this.if_elseFuncion = function(gato, comparacion)
	{
		//if(gato.color === comparacion)
	}
//---------------------------------------------------------------------------------------------

	this.if_ = function()
	{
		interpretarInformacionIF();
	}
//---------------------------------------------------------------------------------------------

	
	this.setNombre = function(nombre)
	{
		this.nombre = nombre;
	}
//---------------------------------------------------------------------------------------------


	this.setIfObjetos = function(ifO)
	{
		ifObjetos = ifO;
	}
//---------------------------------------------------------------------------------------------


	function interpretarInformacionIF()
	{
		sentenciaString[0] = "if( GATO.";
		sentenciaString[0] = identificarColorY_O_arma( 1, sentenciaString[0]);
		if(ifObjetos[2] === -1)
			sentenciaString[0] = sentenciaString[0]+")";
		else if(ifObjetos[2] === 62)
			sentenciaString[0] = sentenciaString[0]+"y GATO.";	
		if(ifObjetos[3] !== -1 )
		{
			sentenciaString[0] = identificarColorY_O_arma( 3, sentenciaString[0]);
			sentenciaString[0] = sentenciaString[0]+")";
		}
		lanzarAlerta(sentenciaString[0]);
		if(identificarAtaque( 4, "") === "función")
		{
			sentenciaString[1]  = "{";
			lanzarAlerta(sentenciaString[1]);
			funcionesFx[ifObjetos[4]-16].if_elseif_else();
			sentenciaString[2]  = "}";
			lanzarAlerta(sentenciaString[2]);
			//sentenciaString[1] = "{    FUNCIÓN F(x)   }";
		}
		else
		{
			sentenciaString[1] = identificarAtaque( 4, "{  GOLPE = ")+"  }";
			lanzarAlerta(sentenciaString[1]);
		}
	}
//---------------------------------------------------------------------------------------------	


	function interpretarInformacionELSEIF()
	{
		sentenciaString[3] = "else if( GATO.";
		sentenciaString[3] = identificarColorY_O_arma( 6, sentenciaString[3]);
		if(ifObjetos[7] === -1)
			sentenciaString[3] = sentenciaString[3]+")";
		else if(ifObjetos[7] === 62)
			sentenciaString[3] = sentenciaString[3]+"y GATO.";
		if(ifObjetos[8] !== -1)	
		{
			sentenciaString[3] = identificarColorY_O_arma( 8, sentenciaString[3]);
			sentenciaString[3] = sentenciaString[3]+")";
		}
		lanzarAlerta(sentenciaString[3]);
		if(identificarAtaque( 9, "") === "función")
		{
			sentenciaString[4]  = "{";
			lanzarAlerta(sentenciaString[4]);
			funcionesFx[ifObjetos[9]-16].if_elseif_else();
			sentenciaString[5]  = "}";
			lanzarAlerta(sentenciaString[5]);
			//sentenciaString[3] = "{    FUNCIÓN F(x)   }";
		}
		else
		{
			sentenciaString[4] = identificarAtaque( 9, "{  GOLPE = ")+"  }";
			lanzarAlerta(sentenciaString[4]);
		}
	}
//---------------------------------------------------------------------------------------------	

	
	function interpretarInformacionELSE()
	{
		if(identificarAtaque( 10, "") === "función")
		{
			sentenciaString[6]  = "else {";
			lanzarAlerta(sentenciaString[6]);
			funcionesFx[ifObjetos[10]-16].if_elseif_else();
			sentenciaString[7]  = "}";
			lanzarAlerta(sentenciaString[7]);
			//sentenciaString[4] = "else {    FUNCIÓN F(x)   }";
		}
		else
		{
			sentenciaString[6] = identificarAtaque( 10, "else {  GOLPE = ")+"  }";
			lanzarAlerta(sentenciaString[6]);
		}
	}
//---------------------------------------------------------------------------------------------	
	
	
	function identificarColorY_O_arma(elemento, sentencia)
	{
		if(ifObjetos[elemento] === 56)
			sentencia = sentencia+"color = VERDE ";
		else if(ifObjetos[elemento] === 57)
			sentencia = sentencia+"color = AMARILLO ";
		else if(ifObjetos[elemento] === 58)
			sentencia= sentencia+"color = AZUL ";
		else if(ifObjetos[elemento] === 59)
			sentencia = sentencia+"color = ROJO ";
		else if(ifObjetos[elemento] === 60)
			sentencia = sentencia+"arma = VERDADERO ";
		else if(ifObjetos[elemento] === 61)
			sentencia = sentencia+"escudo = VERDADERO ";
		return sentencia;
	}
//---------------------------------------------------------------------------------------------	
	
	
	function identificarAtaque(elemento, sentencia)
	{
		if(ifObjetos[elemento] === 63)
			sentencia = sentencia+"puño";
		if(ifObjetos[elemento] === 64)
			sentencia = sentencia+"patada";
		if(ifObjetos[elemento] === 65)
			sentencia = sentencia+"batazo";
		if(ifObjetos[elemento] === 66)
			sentencia = sentencia+"disparo";
		if(ifObjetos[elemento] === 67)
			sentencia = sentencia+"hado-ken";
		if(ifObjetos[elemento] === 68)
			sentencia = sentencia+"corteSierra";
		if(ifObjetos[elemento] === 69)
			sentencia = sentencia+"bombazo";
		if(ifObjetos[elemento] === 70)
			sentencia = sentencia+"corteEspada";
		if(ifObjetos[elemento] === 71)
			sentencia = sentencia+"hachazo";
		if(ifObjetos[elemento] === 72)
			sentencia = sentencia+"mazazo";
		if(ifObjetos[elemento] === 73)
			sentencia = sentencia+"martillazo";
		if(ifObjetos[elemento] === 74)
			sentencia = sentencia+"zarpazo";
		if(ifObjetos[elemento] === 75)
			sentencia = sentencia+"cañonazo";
		if(ifObjetos[elemento] === 76)
			sentencia = sentencia+"soltadaDePitbull";
		if(ifObjetos[elemento] === 77)
			sentencia = sentencia+"disparoLaser";
		if(ifObjetos[elemento] === 78)
			sentencia = sentencia+"flechazo";
		if(ifObjetos[elemento] >= 16 && ifObjetos[elemento] <= 31)
			sentencia = "función";
			
			return sentencia;
	}
//---------------------------------------------------------------------------------------------	
	
	
	function lanzarAlerta( alerta )
	{	
		codigoCompleto.push(alerta);
		//alert(alerta);
	}
}