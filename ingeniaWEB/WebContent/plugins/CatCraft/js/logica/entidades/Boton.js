//JavaScript Document - Albeiro Gualdrón - AreasClick

function areaClick( X, Y, l, h, vista, nombre, img, enable)
{
	this.X = X;
	this.Y = Y;
	this.l = l;
	this.h = h;
	this.nombre = nombre;
	this.vista = vista;
	this.img = img;
	this.enable = enable;
	
	this.setNombre = function(nombre)
	{
		this.nombre = nombre;
	}
//----------------------------------------------------------
	this.setImagen = function(img)
	{
		this.img = img;
	}	
//-----------------------------------------------------------
	this.setPosicion = function(X, Y)
	{
		this.X = X;
		this.Y = Y;
	}	
//-----------------------------------------------------------
	this.setEnable= function(enable)
	{
		this.enable = enable;
	}	
//-----------------------------------------------------------
	this.setVista= function(vista)
	{
		this.vista = vista;
	}	
//-----------------------------------------------------------
}