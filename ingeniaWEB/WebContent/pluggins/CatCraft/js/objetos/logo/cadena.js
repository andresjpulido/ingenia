// JavaScript Document
/*
** fecha creacion: 04/07/2015
** creador: alejandro parra
** descripcion: objeto cadena, el cual tine todas sus acciones para mostrar,animar una cadena.
*/
var CadenaClass = function (CONTEXT)
	{
	  this.img = new Image();
	  this.img.src = "./12.png";
	  this.context = CONTEXT;
	  this.positionInCanvas = new Position(200,150);
	  this.positionIntoImage = new Position(10,10);
	  this.size = new Size(50,100);
	  this.sizeIntoImage = new Size(80,150);
	  this.moveYpx = 5;
	  this.repeatWhen = 50;
	  this.initpx = 10;
	  this.endpx = 80;
	  this.clone = function (){return clonador(this);};//clona el objeto actual
	  this.clearMe = function (){
		  if(this.img.complete)
		   this.context.clearRect(this.positionInCanvas.X,
		   						  this.positionInCanvas.Y,
								  this.size.WIDTH,
								  this.size.HEIGHT);
		  };///end method clear me
	  this.moveTo = function (){};
	  this.moveUp = function (){

		  if(this.img.complete)
		  		{
			  this.drawImg();
		     this.positionIntoImage.Y+=this.moveYpx;
			 this.positionInCanvas.Y -=this.moveYpx;
	//		 this.size.HEIGHT -=this.moveYpx;
			 if(this.positionIntoImage.Y > this.repeatWhen)this.positionIntoImage.Y=this.initpx;
		    }///end if
		  };
	  this.moveBlocked = function (){
		  	if(this.img.complete)
		  		{
			  	 this.drawImg();
		     	 this.positionIntoImage.Y-=this.moveYpx;
				 
			 	 if(this.positionIntoImage.Y <= 0)this.positionIntoImage.Y=this.initpx;
		    	}///end if
	  };
	  this.moveDown = function (){
  		  if(this.img.complete)
		  		{
			  	 this.drawImg();
		     	 this.positionIntoImage.Y-=this.moveYpx;
				 this.positionInCanvas.Y +=this.moveYpx;
//				 this.size.HEIGHT += this.moveYpx;
			 	 if(this.positionIntoImage.Y <= 0)this.positionIntoImage.Y=this.endpx;
		    	}///end if
		  };
	  this.drawImg = function (){
			if(this.img.complete)
			 {
			// this.clearMe();
		   	 this.context.drawImage(this.img,
			 						this.positionIntoImage.X,
									this.positionIntoImage.Y,
									this.sizeIntoImage.WIDTH,
									this.sizeIntoImage.HEIGHT,
									this.positionInCanvas.X,
									this.positionInCanvas.Y,
									this.size.WIDTH,
									this.size.HEIGHT);		  
			}//end if
		  };///end function drawImg
		  
	};//end clases cadena

