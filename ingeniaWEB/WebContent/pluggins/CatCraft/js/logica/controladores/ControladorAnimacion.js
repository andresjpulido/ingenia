// JavaScript Document
/*
** FECHA CREACION : 07/07/2015
** CREADOR: 
** DESCRIPCION: 
*/

var imagenes = [];
// var posXpata = [140, 360, 400, 650];
// var posYpata = [320, 250, 40, 0];
// for (var i = 0; i <= 9; i++ )
// {
	// imagenes[i] = new Image();
	// imagenes[i].src = 'Imagen'+i+'.png';
// }

// function dibujarInicio()
// {
	// var canvas = document.getElementById('canvas');
	// var pincel = canvas.getContext('2d');
	// pincel.drawImage(imagenes[3], 0, 25);
	// pincel.drawImage(imagenes[6], 120, 50);
	// pincel.drawImage(imagenes[7], 340, 300);
	// canvas.addEventListener("click", clickInicial, false);	
// }

var ControladorAnimacion = function () {

//imagenes = [];
this.posXpata = [140, 360, 400, 650];
this.posYpata = [320, 250, 40, 0];

this.CargarImagenes = function()
{
	var hostUrl = "/ingeniaWEB/pluggins/CatCraft/";
	
	for (var i = 0; i <= 9; i++ )
	{
		var imagen = new Image();
		imagen.src = hostUrl + '/imagenes/Imagen'+i+'.png';
		imagenes.push(imagen);
	}
}

this.DibujarInicio = function () {
	this.CargarImagenes();
	var canvas = document.getElementById('canvas');
	var pincel = canvas.getContext('2d');
	pincel.drawImage(imagenes[3], 0, 25);
	pincel.drawImage(imagenes[6], 120, 50);
	pincel.drawImage(imagenes[7], 340, 300);
	canvas.addEventListener("click", this.clickInicial, false);	
    };
    
    this.DibujarTableroSeleccion = function () {

    };

    this.AnimarVictoria = function () { 
    
    };

    this.AnimarDerrota = function (Ganador) {
        
    };
	
	this.clickInicial = function(event)
{	
	var canvas = document.getElementById('canvas');
	var linea = canvas.getContext('2d');
	var X = event.clientX - canvas.getBoundingClientRect().left;
    var Y = event.clientY - canvas.getBoundingClientRect().top;//340, 300
	
	if((X >= 340 &&  X <= 455) && (Y >= 300 && Y <= 343))
	{
		new ControladorAnimacion().dibujarPatasOK(0);
		//dibujarImagen(0, 60, 371); 
	}
	
}
	
	this.dibujarPatasOK = function(pat)
{
	
	var canvas = document.getElementById('canvas');
	var pincel = canvas.getContext('2d');
	
	pincel.drawImage(imagenes[8], this.posXpata[pat], this.posYpata[pat]);
	if(pat < 4)
		setTimeout(function(){ new ControladorAnimacion().dibujarPatasOK(++pat);},400);
	else
	{
		pincel.fillRect(0,0, 800, 500);
		dibujarImagen(1, 60, 371); 
	}
}
 
	
	function dibujarImagen(img, x, y) 
{
  var i = img%3;	
  var canvas = document.getElementById('canvas');
  if (canvas.getContext)
  {
   	var ctx = canvas.getContext('2d');
	{
		ctx.drawImage(imagenes[3], 0, 25);
		if(img%40 === 0)
		{
			ctx.drawImage(imagenes[5], 650, 368);
		}
		else
		{
			ctx.drawImage(imagenes[4], 650, 368);
		}
		ctx.drawImage(imagenes[i], x, y);
		if( x < 600)
		{
			img++;
			setTimeout(function(){dibujarImagen(img, x+4, y);},20);
		}
		else
			ctx.drawImage(imagenes[9], 480, 30);
	}
  }
}
}///END CLASS