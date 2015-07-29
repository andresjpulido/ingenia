//JavaScript Document - Albeiro Gualdrón - Gato

function Gato(color, tipo, arma, armadura)
{
	this.color = color;
	this.tipo = tipo;
	this.arma = arma;
	this.armadura = armadura;
	this.isCAminando = false;
	this.X = 60;
	this.Y = 371;
	
	if(color === "verde")
	{
		this.secuenciaCaminar = [0, 1, 2, 1];
		this.secuenciaMorir = [92, 93, 94, 95, 96];
		this.secuenciaComer = [116, 117, 118, 119, 120];
	}
	else if(color === "amarillo")
	{
		this.secuenciaCaminar = [83, 84, 85, 84];
		this.secuenciaMorir = [97, 98, 99, 100, 101];
		this.secuenciaComer = [121, 122, 123, 124, 125];
	}
	else if(color === "azul")
	{
		this.secuenciaCaminar = [86, 87, 88, 87];
		this.secuenciaMorir = [102, 103, 104, 105, 106];
		this.secuenciaComer = [126, 127, 128, 129, 130];
	}
	else if(color === "rojo")
	{
		this.secuenciaCaminar = [89, 90, 91, 90];
		this.secuenciaMorir = [107, 108, 109, 110, 111];
		this.secuenciaComer = [131, 132, 133, 134, 135];
	}
		
	this.atributos = this.color;
	
	if(arma && armadura > 0 )
		this.atributos = this.atributos+"_arma_escudo";
	else if (arma)
		this.atributos = this.atributos+"_arma";
	else if (armadura > 0)
		this.atributos = this.atributos+"_escudo";
	
	this.puntaje = 10;
	
	if(this.arma)
		this.puntaje+=10;
	if(this.armadura > 0)
		this.puntaje+=(5*this.armadura);
		
	
//---------------------------------------------------------------

	this.setIsCaminando = function( isCAminando )
	{
		this.isCAminando  = isCAminando;
	}
	
//---------------------------------------------------------------

	this.setX= function( X )
	{
		this.X = X;
		return X;
	}
//---------------------------------------------------------------

	this.setY= function( Y )
	{
		this.Y = Y;
		return Y;
	}
}