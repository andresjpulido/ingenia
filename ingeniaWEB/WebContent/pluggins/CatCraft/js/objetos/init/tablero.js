// JavaScript Document
/*
** fecha creacion: 04/07/2015
** creador : alejadnro parra
** descripcion: objeto q tiene la informacion del tablero canvas.
*/

var Tablero = ({
	 canvas:null,
	 context:null,
	 size:{width:440,height:297},
	 clear:(function ()
	 	{	
			this.context.clearRect(0,0,this.size.width,this.size.height);
		})
	});