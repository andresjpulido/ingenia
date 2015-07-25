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
		this.secuenciaCaminar = [0, 1, 2, 1];
	else if(color === "amarillo")
		this.secuenciaCaminar = [83, 84, 85, 84];
	else if(color === "azul")
		this.secuenciaCaminar = [86, 87, 88, 87];
	else if(color === "rojo")
	this.secuenciaCaminar = [89, 90, 91, 90];
	puntaje = 10;
	
	this.calcularPuntaje = function()
	{
		if(this.arma)
			puntaje+=10;
		if(this.armadura !== 0)
			puntaje+=(5*this.armadura);
		return puntaje;
	}
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