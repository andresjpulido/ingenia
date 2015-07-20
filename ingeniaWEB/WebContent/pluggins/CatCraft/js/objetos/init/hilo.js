// JavaScript Document
/*
** fecha creacion: 04/07/2015
** creador : alejandro parra
** descripcion: este objeto maneja las acciones del hilo, aqui se configura que se ejecuta 
**  			los frames por segundo que se van a usar.
*/
var Hilo = ({
	state:0,//estado 0 sube, estado 1 baja
	index:null,
	frames:1000/15,
	miliseconds:1000,
	funcion:function(){

		 
		 
		if(logoDown.getY() < 110 )
			Hilo.state = 1;	
		else 
			 if(logoDown.getY() > 187 )
			  Hilo.state = 0;
			  
			  Tablero.clear();
		if(Hilo.state == 1)	{ 
			 cadena2.moveDown();
			 cadena1.moveDown();
			 logoDown.moveDown();

		}
		else if(Hilo.state == 0) {
			cadena1.moveUp();
			cadena2.moveUp();
			logoDown.moveUp();
			}
			
		logoUp.setX(10);
		logoUp.setY(-5);
		logoUp.drawImg();

		},
	start:function(){
		  Hilo.index = window.setInterval(Hilo.funcion,Hilo.frames);
		}
	});
	
