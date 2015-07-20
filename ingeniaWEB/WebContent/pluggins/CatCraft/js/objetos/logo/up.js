// JavaScript Document
/*
** FECHA CREACION : 07/07/2015
** CREADOR: ALEJANDRO PARRA
** DESCRIPCION: ESTA CLASE MANEJA LA IMAGEN DE LA PARTE INFERIOR DEL LOGO, ESTA CONTIENE LA EJECUCION Y LOS MOVIMIENTOS 
*/
var LogoUp = function (CONTEXT)
	{
		var img = new Image();
		img.src = "LogoParteSuperior.png";
		var context = CONTEXT;
		var positionInCanvas = new Position(20,20);
	  	var positionIntoImage = new Position(10,10);
	    var size = new Size(400,110);
	    var sizeIntoImage = new Size(1150,441);
	    var moveYpx = 5;
	    var repeatWhen = 50;
	    var initpx = 10;
	    var endpx = 80;
		this.setX = function(x){positionInCanvas.X = x;};
		this.setY = function(y){positionInCanvas.Y = y;};
		var clearMe = function (){
		  if(img.complete)
		   context.clearRect(positionInCanvas.X,
		   					 positionInCanvas.Y,
							 size.WIDTH,
							 size.HEIGHT);
		  };///end method clear me		
		this.moveUp = function (){
		  if(img.complete)
		  	{
			 this.drawImg();
		     positionIntoImage.Y+=moveYpx;
			 if(positionIntoImage.Y > repeatWhen)positionIntoImage.Y=initpx;
		    }///end if
		  };
	  this.moveBlocked = function (){
		  	if(img.complete)
		  		{
			  	 drawImg();
		     	 positionIntoImage.Y-=moveYpx;
			 	 if(positionIntoImage.Y <= 0)positionIntoImage.Y=initpx;
		    	}///end if
	  };
	  this.moveDown = function (){
  		  if(img.complete)
		  		{
			  	 this.drawImg();
		     	 positionIntoImage.Y-=moveYpx;
			 	 if(positionIntoImage.Y <= 0)positionIntoImage.Y=endpx;
		    	}///end if
		  };
	  this.drawImg = function (){
			if(img.complete)
			 {
			// clearMe();
			// context.drawImage(img,10,10,1031,140,20,20,400,60);

		   	 context.drawImage(img,
			 					positionIntoImage.X,
								positionIntoImage.Y,
								sizeIntoImage.WIDTH,
								sizeIntoImage.HEIGHT,
								positionInCanvas.X,
								positionInCanvas.Y,
								size.WIDTH,
								size.HEIGHT);		  
			}//end if
			else console.log("problemas");
		  };///end function drawImg


	}///END CLASS LOGOUP// JavaScript Document