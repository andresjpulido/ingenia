//JavaScript Document - Albeiro Gualdrón - SenteciaIf


var atribGato = [];
var atrib = 0;
var ataqueApropinar = "maullido";
//---------------------------------------------------------------------------------------------


function sentenciaIf( nombre )
{
	
	this.sentenciaString = [];
	sentenciaString = this.sentenciaString; 
	this.nombre = nombre;
	var ifObjetos = [];
//---------------------------------------------------------------------------------------------


	this.inicializar = function ()
	{
		ataqueApropinar = "maullido";
		atrib = 0;
		atribGato = [];
		sentenciaString = [];
	}

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
	
	function puente()
	{
		this.if_elseif_elseFuncion();
	}
//---------------------------------------------------------------------------------------------

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
		{
			sentenciaString[0] = sentenciaString[0]+")";
			atribGato.push("vacio");
			atribGato.push("vacio");
		}
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
		{
			atribGato.push("vacio");
			atribGato.push("vacio");
			sentenciaString[3] = sentenciaString[3]+")";
		}
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
		{
			sentencia = sentencia+"color = VERDE ";
			atribGato.push(gatosDelNivel[gatoEnFrente].color);
			atribGato.push("verde");
		}
		else if(ifObjetos[elemento] === 57)
		{
			sentencia = sentencia+"color = AMARILLO ";
			atribGato.push(gatosDelNivel[gatoEnFrente].color);
			atribGato.push("amarillo");
		}
		else if(ifObjetos[elemento] === 58)
		{
			sentencia= sentencia+"color = AZUL ";
			atribGato.push(gatosDelNivel[gatoEnFrente].color);
			atribGato.push("azul");
		}
		else if(ifObjetos[elemento] === 59)
		{
			sentencia = sentencia+"color = ROJO ";
			atribGato.push(gatosDelNivel[gatoEnFrente].color);
			atribGato.push("rojo");
		}
		else if(ifObjetos[elemento] === 60)
		{
			sentencia = sentencia+"arma = VERDADERO ";
			atribGato.push(gatosDelNivel[gatoEnFrente].arma);
			atribGato.push(true);
		}
		else if(ifObjetos[elemento] === 61)
		{
			sentencia = sentencia+"escudo = VERDADERO ";
			atribGato.push(gatosDelNivel[gatoEnFrente].armadura > 0? "escudo":0);
			atribGato.push("escudo");
		}
		return sentencia;
	}
//---------------------------------------------------------------------------------------------	
	
	
	function identificarAtaque(elemento, sentencia)
	{
		if(ifObjetos[elemento] === 63)
		{
			if(sentencia !== "")
				atribGato.push("puño");
			sentencia = sentencia+"puño";
		}
		else if(ifObjetos[elemento] === 64)
		{
			if(sentencia !== "")
				atribGato.push("patada");
			sentencia = sentencia+"patada";
		}
		else if(ifObjetos[elemento] === 65)
		{
			if(sentencia !== "")
				atribGato.push("bate");
			sentencia = sentencia+"batazo";
		}
		else if(ifObjetos[elemento] === 66)
		{
			if(sentencia !== "")
				atribGato.push("pistola");
			sentencia = sentencia+"disparo";
		}
		else if(ifObjetos[elemento] === 67)
		{
			if(sentencia !== "")
				atribGato.push("hado-ken");
			sentencia = sentencia+"hado-ken";
		}
		else if(ifObjetos[elemento] === 68)
		{
			if(sentencia !== "")
				atribGato.push("sierra");
			sentencia = sentencia+"corteSierra";
		}
		else if(ifObjetos[elemento] === 69)
		{
			if(sentencia !== "")
				atribGato.push("bomba");
			sentencia = sentencia+"bombazo";
		}
		else if(ifObjetos[elemento] === 70)
		{
			if(sentencia !== "")
				atribGato.push("espada");
			sentencia = sentencia+"corteEspada";
		}
		else if(ifObjetos[elemento] === 71)
		{
			if(sentencia !== "")
				atribGato.push("hacha");
			sentencia = sentencia+"hachazo";
		}			
		else if(ifObjetos[elemento] === 72)
		{
			if(sentencia !== "")
				atribGato.push("mazo");
			sentencia = sentencia+"mazazo";
		}
		else if(ifObjetos[elemento] === 73)
		{
			if(sentencia !== "")
				atribGato.push("martillo");
			sentencia = sentencia+"martillazo";
		}	
		else if(ifObjetos[elemento] === 74)
		{
			if(sentencia !== "")
				atribGato.push("zarpa");
			sentencia = sentencia+"zarpazo";
		}	
		else if(ifObjetos[elemento] === 75)
		{
			if(sentencia !== "")
				atribGato.push("cañon");
			sentencia = sentencia+"cañonazo";
		}
		else if(ifObjetos[elemento] === 76)
		{
			if(sentencia !== "")
				atribGato.push("perro");
			sentencia = sentencia+"soltadaDePitbull";
		}
		else if(ifObjetos[elemento] === 77)
		{
			if(sentencia !== "")
				atribGato.push("laser");
			sentencia = sentencia+"disparoLaser";
		}	
		else if(ifObjetos[elemento] === 78)
		{
			if(sentencia !== "")
				atribGato.push("arco");
			sentencia = sentencia+"flechazo";
		}
		else if(ifObjetos[elemento] >= 16 && ifObjetos[elemento] <= 31)
		{
			if(sentencia !== "")
				atribGato.push("funcion");
			sentencia = "función";
		}
		return sentencia;
	}
//---------------------------------------------------------------------------------------------	
	
	
	function lanzarAlerta( alerta )
	{	
		codigoCompleto.push(alerta);
		//alert(alerta);
	}
}