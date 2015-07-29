//JavaScript Document - Albeiro Gualdrón - ControladorEventosMouse
var vistaActiva = 0;
var presionaMouse = false;
var funcionesFx = [];
var raton;
var funcionActual = 0;


function ControladorEvMouse()
{	
	botonesNext = [];
	botonesDrag = [];
	isOpcion2 = false;
	imgBotonDown = -1;
	enviar = true;
	//funciones = 0;
	
	this.addMouseEv = function()
	{
		canvas.addEventListener("click", clickMouse, false);
		canvas.addEventListener("mouseup", presionar_soltarMouse, false);
		canvas.addEventListener("mousedown", presionar_soltarMouse, false);
		canvas.addEventListener("mousemove", moverMouse, false);
	}
//---------------------------------------------------------------------------------------------------------------------------------		


	function calcularX_Y(event)
	{
		X = event.clientX - canvas.getBoundingClientRect().left;
    	Y = event.clientY - canvas.getBoundingClientRect().top;	
	}
		
//---------------------------------------------------------------------------------------------------------------------------------	
	
	
	function clickMouse(event)
	{	
		calcularX_Y(event);
		for( i = 0; i < botonesNext.length; i++)
		{
			if(i=== 0 && isOpcion2)
			{
				i = 6;
				isOpcion2 = false;
			}
			else if( i === 6 && !isOpcion2)
				i = 9;	
				
			if(botonesNext[i]!=null)
			{
			if( X >= botonesNext[i].X && X <= (botonesNext[i].X+botonesNext[i].l) && botonesNext[i].vista === vistaActiva)
			{
				if( Y >= botonesNext[i].Y && Y <= (botonesNext[i].Y+botonesNext[i].h) && botonesNext[i].enable)
				{
					btnClick = botonesNext[i].nombre;
					botonClickeado_accion( btnClick );
					break;
				}
			}
			}
			if(vistaActiva === 1 && (i+1) === botonesNext.length)
			{
				dibujarSelectorRutina();
				solicitarPintadoBotones();
			}
		}
	}
//---------------------------------------------------------------------------------------------------------------------------------	
	
	
	function presionar_soltarMouse(event)
	{	
		elegirPaqBotonesXvista();
		if(event.type === "mouseup")
		{
			if(presionaMouse)
			{
				if(vistaActiva === 1)
					dibujarSelectorRutina();
				else if(vistaActiva === 2)
					dibujarCreadorFunciones(funcionActual);
				solicitarPintadoBotones();
			}
			presionaMouse = false;
		}
		calcularX_Y(event);
		for( i = 0; i < botonesDrag.length; i++)
		{
			if( X >= botonesDrag[i].X && X <= (botonesDrag[i].X+botonesDrag[i].l) && botonesDrag[i].vista === vistaActiva)
			{
				if( Y >= botonesDrag[i].Y && Y <= (botonesDrag[i].Y+botonesDrag[i].h) )
				{
					btnClick = botonesDrag[i].nombre;
					if(event.type === "mousedown")
					{
						presionaMouse = true;
						imgBotonDown = botonesDrag[i].img;
						btnDown = i;
					}
					botonPresSolt_accion( btnClick, botonesDrag[i], btnDown);
					break;
				}
			}
		}
	}	
//---------------------------------------------------------------------------------------------------------------------------------	
	
	
	function moverMouse(event)
	{	
		if(presionaMouse)
		{
			calcularX_Y(event);
			
			if(vistaActiva === 0)
			chequeoMoveVista0();
			else if(vistaActiva === 1)
			chequeoMoveVista1();
			else if(vistaActiva === 2)
			chequeoMoveVista2();
		}
	}
//---------------------------------------------------------------------------------------------------------------------------------	

	
	function botonClickeado_accion( btnClick )
	{
		if(vistaActiva === 0)
			chequeoClickVista0();
		else if(vistaActiva === 1)
			chequeoClickVista1();
		else if(vistaActiva === 2)
			chequeoClickVista2();
		else if(vistaActiva === 3)
			chequeoClickVista3();	
	}	
//---------------------------------------------------------------------------------------------------------------------------------	

	
	function botonPresSolt_accion( btnClick, btnDrg, btnDown )
	{
		if(vistaActiva === 0)
			chequeoDragVista0();
		else if(vistaActiva === 1)
			chequeoDragVista1(btnClick, btnDrg, btnDown);
		else if(vistaActiva === 2)
			chequeoDragVista2(btnDrg);
	}
//---------------------------------------------------------------------------------------------------------------------------------	

	
	function chequeoClickVista1()
	{
		if( btnClick === "Luchar")
		{
			raton = new Raton();
			raton.cargarFunciones();
			if(raton.ejecucion.length > 0)
			{
				vistaActiva = 3;
				elegirPaqBotonesXvista();
				gatosDelNivel[0].setIsCaminando(true);
				dibujarCaminataGato(1); 
			}
			else
				alert("ratón sin rutina, si lo manda a pelear así, será botanita de gato...");
		}
		else if( btnClick === "if_else")
		{
			isOpcion2 = true;
			dibujarSegundaSeleccion(520, 310, 49);
		}
		else if( btnClick === "if_else0" )
		{
			vistaActiva = 2;
			funcionActual = 82;
			moverMatrizSentencias();
			elegirPaqBotonesXvista();
			dibujarCreadorFunciones(funcionActual);
			elegirBotonesDeFuncion();
			solicitarPintadoBotones();
		}
		else if( btnClick === "if_else1" )
		{
			vistaActiva = 2;
			funcionActual = 149;
			moverMatrizSentencias();
			elegirPaqBotonesXvista();
			dibujarCreadorFunciones(funcionActual);
			elegirBotonesDeFuncion();
			solicitarPintadoBotones();
		}
		else if( btnClick === "if_else2" )
		{
			vistaActiva = 2;
			funcionActual = 15;
			moverMatrizSentencias();
			elegirPaqBotonesXvista();
			dibujarCreadorFunciones(funcionActual);
			elegirBotonesDeFuncion();
			solicitarPintadoBotones();
		}
		//else if( btnClick === "Switch")
		//{
		//	vistaActiva = 2;
		//	moverMatrizSentencias();
		//	elegirPaqBotonesXvista();
		//	dibujarCreadorFunciones(funcionActual);
		//	solicitarPintadoBotones();
		//}	
	}
//---------------------------------------------------------------------------------------------------------------------------------	


	function chequeoClickVista3()
	{
		if( btnClick === "new_run")
		{
			crearControladorGatos();
			vistaActiva = 0;
			gatoEnFrente = 0;
			enColision = false;
			presionaMouse = false;
			funcionesFx.length = 0;
			codigoCompleto.length = 0;
			limpiarSentencia();
			for(i = 0; i < botonesDrag1y2.length; i++)
			{
				botonesDrag1y2[i].setImagen(-1);
			}
			
			iniciar();
		}
	}
//---------------------------------------------------------------------------------------------------------------------------------	


	function chequeoClickVista2()
	{
		if( btnClick === "OK")
		{
			enviar = true;
			validarIf_elseIf_else();
			
		}
		else if( btnClick === "CANCELAR" )
		{
			vistaActiva = 1;
			moverMatrizSentencias();
			elegirPaqBotonesXvista();
			dibujarSelectorRutina();
			solicitarPintadoBotones();
		}
		else if( btnClick === "limpiar" )
		{
			limpiarSentencia();
			elegirBotonesDeFuncion();
			dibujarCreadorFunciones(funcionActual);
			solicitarPintadoBotones();
		}
		else if( btnClick === "add0")
		{
			botonesDrag3[9].setEnable(true);
			botonesDrag3[10].setEnable(true);
			botonesNext3[3].setImagen(13);
			botonesNext3[3].setEnable(false);
			dibujarCreadorFunciones(funcionActual);
			solicitarPintadoBotones();
		}
		else if( btnClick === "add1")
		{
			botonesDrag3[13].setEnable(true);
			botonesDrag3[14].setEnable(true);
			botonesNext3[4].setImagen(14);
			botonesNext3[4].setEnable(false);
			dibujarCreadorFunciones(funcionActual);
			solicitarPintadoBotones();
		}
	}
//---------------------------------------------------------------------------------------------------------------------------------	


	function limpiarSentencia()
	{
		for(i = 7; i <= 17; i++)
			{
				botonesDrag3[i].setImagen(-1);
			}
			botonesDrag3[9].setEnable(false);
			botonesNext3[3].setImagen(-1);
			botonesNext3[4].setImagen(-1);
			botonesDrag3[10].setEnable(false);
			botonesNext3[3].setEnable(true);
			
			botonesDrag3[13].setEnable(false);
			botonesDrag3[14].setEnable(false);
			botonesNext3[4].setEnable(true);
	}	
//---------------------------------------------------------------------------------------------------------------------------------	

	
	function validarIf_elseIf_else()
	{
		if( botonesDrag3[7].img !== 62 && botonesDrag3[7].enable)
			 enviar = false;
		else if( (botonesDrag3[8].img < 56 || botonesDrag3[8].img > 61 ) && botonesDrag3[8].enable)
			 enviar = false;
		else if( botonesDrag3[9].img !== 62 && botonesDrag3[9].enable )
			 enviar = false;	
		else if( (botonesDrag3[10].img < 56 || botonesDrag3[10].img > 61) && botonesDrag3[10].enable)
			 enviar = false; 
		else if( (botonesDrag3[15].img < 63 || botonesDrag3[15].img > 78) && (botonesDrag3[15].img < 16 || botonesDrag3[15].img > 31) && botonesDrag3[15].enable )
			 enviar = false; 
		else if( botonesDrag3[11].img !== 62 && botonesDrag3[11].enable)
			 enviar = false; 
		else if( (botonesDrag3[12].img < 56 || botonesDrag3[12].img > 61) && botonesDrag3[12].enable )
			 enviar = false;
		else if( botonesDrag3[13].img !== 62 && botonesDrag3[13].enable )
			 enviar = false;	 
		else if( (botonesDrag3[14].img < 56 || botonesDrag3[14].img > 61) && botonesDrag3[14].enable)
			 enviar = false; 
		else if( (botonesDrag3[16].img < 63 || botonesDrag3[16].img > 78) && (botonesDrag3[16].img < 16 || botonesDrag3[16].img > 31) && botonesDrag3[16].enable)
			 enviar = false; 
		else if( (botonesDrag3[17].img < 63 || botonesDrag3[17].img > 78) && (botonesDrag3[17].img < 16 || botonesDrag3[17].img > 31) && botonesDrag3[17].enable)
			 enviar = false;
		
		if(enviar && (funcionesFx.length < 17))
		{
			crearSentenciaIf();
			//botonesDrag1y2[funcionesFx.length+31].setImagen(funcionesFx.length+15);
			//funciones++;
		}
		else if(!enviar)
			alert("...sentencia incompleta o incorrecta...");
		else
			alert("Máximo número de sentencias creadas");
	}
//---------------------------------------------------------------------------------------------------------------------------------	

	function crearSentenciaIf()
	{
		
		creada = false;
		ifObjeto = [botonesDrag3[7].img,  botonesDrag3[8].img,  botonesDrag3[9].img,  botonesDrag3[10].img,	
					botonesDrag3[15].img, botonesDrag3[11].img, botonesDrag3[12].img, botonesDrag3[13].img, 
					botonesDrag3[14].img, botonesDrag3[16].img, botonesDrag3[17].img];
					
		for( i = 0; i < funcionesFx.length; i++)
		{
			if( funcionesFx[i] === undefined )
			{
				funcionesFx[i] = new sentenciaIf( "funcion"+i );
				funcionesFx[i].setIfObjetos(ifObjeto);
				botonesDrag1y2[i+32].setImagen(i+16);
				creada = true;
				break;
			}	
		}
		if(!creada && (funcionesFx.length < 16))
		{
			funcionesFx.push (new sentenciaIf( "funcion"+funcionesFx.length ) );
			funcionesFx[funcionesFx.length-1].setIfObjetos(ifObjeto);
			botonesDrag1y2[funcionesFx.length+31].setImagen(funcionesFx.length+15);
			creada = true;
		}
		else if (!creada && funcionesFx.length === 16)
			alert("Máximo número de sentencias creadas");
		
		if(creada)
		{
			vistaActiva = 1;
			moverMatrizSentencias();
			dibujarSelectorRutina();
			elegirPaqBotonesXvista();
			solicitarPintadoBotones();
		}
		//funcionesFx[funcionesFx.length-1].if_elseif_else();
	}

//---------------------------------------------------------------------------------------------------------------------------------	
	
	
	function chequeoClickVista0()
	{
		if( btnClick === "Jugar")
		{
			vistaActiva = 1;
			moverMatrizSentencias();
			dibujarPatasOK(0);
		}	
	}
//---------------------------------------------------------------------------------------------------------------------------------	


	function chequeoDragVista0()
	{
	}
//---------------------------------------------------------------------------------------------------------------------------------	


	function chequeoDragVista1(btnClick, btnDrg, btnDown)
	{
		if(!presionaMouse)
		{
			for(i = 0; i < 4; i++)
			{
				for(j = 0; j < 8; j++)
				{
					if( btnClick === "Rutina"+j+i)
					{
						if(imgBotonDown <= 31 && imgBotonDown >= 16)
							btnDrg.setImagen(imgBotonDown+16);
						else if(imgBotonDown <= 47 && imgBotonDown >= 32)
							btnDrg.setImagen(imgBotonDown);
						solicitarPintadoBotones();
					}
				}
			}
			
			if( btnClick === "papelera")
			{
				if(imgBotonDown <= 47 && imgBotonDown >= 32)
				{
					botonesDrag1y2[btnDown].setImagen(-1);
				}
				else if(imgBotonDown <= 31 && imgBotonDown >= 16)
				{
					for(i = 0; i <= 31; i++)
					{ 
						if(botonesDrag1y2[i].img === botonesDrag1y2[btnDown].img+16)
						botonesDrag1y2[i].setImagen(-1);
					}
					botonesDrag1y2[btnDown].setImagen(-1);
					delete funcionesFx[btnDown-32];
				}
				dibujarSelectorRutina();
				solicitarPintadoBotones();
			}
			imgBotonDown = -1;
		}
		
	}
//---------------------------------------------------------------------------------------------------------------------------------	


	function chequeoDragVista2(btnDrg)
	{
		if(!presionaMouse)
		{
			if( btnClick === "insertGatito00" || btnClick === "insertGatito02" || btnClick === "insertGatito10" || btnClick === "insertGatito12")
			{
				if((imgBotonDown === 12 || imgBotonDown === 62 ) && btnDrg.enable )
				{
					
					btnDrg.setImagen(62);
					solicitarPintadoBotones();
				}
			}
			else if( btnClick === "insertGatito01" || btnClick === "insertGatito11" || btnClick === "insertGatito13" || btnClick === "insertGatito03")
			{
				if(( (imgBotonDown >= 56  && imgBotonDown <= 59) || (imgBotonDown >= 50  && imgBotonDown <= 55) ) && btnDrg.enable )
				{
					if(imgBotonDown >= 56  && imgBotonDown <= 59)
						btnDrg.setImagen(imgBotonDown);
					else
						btnDrg.setImagen(imgBotonDown+6);
					solicitarPintadoBotones();
				}
			}
			else if( btnClick === "insertAtaque0" || btnClick === "insertAtaque1" || btnClick === "insertAtaque2")
			{
				if( ((imgBotonDown >= 63  && imgBotonDown <= 78 ) || (imgBotonDown >= 16  && imgBotonDown <= 31 )) && btnDrg.enable )
				{
					btnDrg.setImagen(imgBotonDown);
					solicitarPintadoBotones();
				}
			}
			imgBotonDown = -1;
		}
	}
//---------------------------------------------------------------------------------------------------------------------------------	


	function chequeoMoveVista0()
	{
	}
//---------------------------------------------------------------------------------------------------------------------------------	


	function chequeoMoveVista1()
	{
		dibujarSelectorRutina();
		solicitarPintadoBotones()
		pintarArrastre(X-(botonesDrag[btnDown].l/2), Y-(botonesDrag[btnDown].h/2), imgBotonDown);
	}
//---------------------------------------------------------------------------------------------------------------------------------	


	function chequeoMoveVista2()
	{
		dibujarCreadorFunciones(funcionActual);
		solicitarPintadoBotones();
		pintarArrastre(X-(botonesDrag[btnDown].l/2), Y-(botonesDrag[btnDown].h/2), imgBotonDown);
	}
//---------------------------------------------------------------------------------------------------------------------------------	


	function elegirPaqBotonesXvista()
	{
		if( vistaActiva < 2)
		{
			botonesNext = botonesNext1y2;
			botonesDrag = botonesDrag1y2;
		}
		else if( vistaActiva === 2)
		{
			botonesNext = botonesNext3;
			botonesDrag = botonesDrag3;
		}
		else if (vistaActiva === 3)
		{
			botonesNext = botonesNext4;
			botonesDrag = [];
		}
	}
//---------------------------------------------------------------------------------------------------------------------------------	


	function solicitarPintadoBotones()
	{
		if(vistaActiva === 2)
		{
			for( i = 0; i < botonesNext.length; i++ )
			{
				if( botonesNext[i].img !== -1 && botonesNext[i].nombre === "add1")
					pintarArrastre(botonesNext[i].X-16, botonesNext[i].Y-7, botonesNext[i].img);
				else if( botonesNext[i].img !== -1 && botonesNext[i].nombre === "add0")
					pintarArrastre(botonesNext[i].X-57, botonesNext[i].Y-3, botonesNext[i].img);
			}
			for( i = 0; i < botonesDrag.length; i++ )
			{
				if( (botonesDrag[i].nombre.substring(0,6) === "insert" || botonesDrag[i].nombre.substring(0,1) === "S") && botonesDrag[i].img !== -1)
					pintarArrastre(botonesDrag[i].X, botonesDrag[i].Y, botonesDrag[i].img);
			}
		}
		else if(vistaActiva === 1)
		{
			for( i = 0; i < botonesNext.length; i++ )
			{
				if( botonesNext[i].img !== -1)
				pintarArrastre(botonesNext[i].X, botonesNext[i].Y, botonesNext[i].img);
			}
			for( i = 0; i < botonesDrag.length; i++ )
			{
				if( botonesDrag[i].img !== -1)
					pintarArrastre(botonesDrag[i].X, botonesDrag[i].Y, botonesDrag[i].img);
			}
		}
	}
//---------------------------------------------------------------------------------------------------------------------------------		

	
	function elegirBotonesDeFuncion()
	{
		if(funcionActual === 15)	
		{
			for(i = 7; i <= 17; i++)
			{
				botonesDrag3[i].setImagen(-1);
			}
			botonesDrag3[9].setEnable(false);
			botonesDrag3[10].setEnable(false);
			botonesDrag3[11].setEnable(true);
			botonesDrag3[12].setEnable(true);
			botonesDrag3[13].setEnable(false);
			botonesDrag3[14].setEnable(false);
			botonesDrag3[16].setEnable(true);
			botonesDrag3[17].setEnable(true);
			
			if(botonesDrag3[16].Y > botonesDrag3[17].Y)
			{
				Ytemp = botonesDrag3[16].Y;
				Xtemp = botonesDrag3[16].X;
				botonesDrag3[16].setPosicion( botonesDrag3[17].X , botonesDrag3[17].Y ); 
				botonesDrag3[17].setPosicion( Xtemp, Ytemp);
			}
			
			botonesNext3[3].setImagen(-1);
			botonesNext3[4].setImagen(-1);
			
			botonesNext3[3].setEnable(true);
			botonesNext3[4].setEnable(true);
		}
		else if (funcionActual === 82 )
		{
			for(i = 7; i <= 17; i++)
			{
				botonesDrag3[i].setImagen(-1);
			}
			botonesDrag3[9].setEnable(false);
			botonesDrag3[10].setEnable(false);
			botonesDrag3[11].setEnable(false);
			botonesDrag3[12].setEnable(false);
			botonesDrag3[13].setEnable(false);
			botonesDrag3[14].setEnable(false);
			botonesDrag3[16].setEnable(false);
			botonesDrag3[17].setEnable(false);
			
			botonesNext3[3].setImagen(-1);
			botonesNext3[4].setImagen(-1);
			
			botonesNext3[3].setEnable(true);	
			botonesNext3[4].setEnable(false);
		}
		else if (funcionActual === 149 )
		{
			for(i = 7; i <= 17; i++)
			{
				botonesDrag3[i].setImagen(-1);
			}
			botonesDrag3[9].setEnable(false);
			botonesDrag3[10].setEnable(false);
			botonesDrag3[11].setEnable(false);
			botonesDrag3[12].setEnable(false);
			botonesDrag3[13].setEnable(false);
			botonesDrag3[14].setEnable(false);
			botonesDrag3[16].setEnable(false);
			botonesDrag3[17].setEnable(true);
			
			if(botonesDrag3[16].Y < botonesDrag3[17].Y)
			{
				Ytemp = botonesDrag3[16].Y;
				Xtemp = botonesDrag3[16].X;
				botonesDrag3[16].setPosicion( botonesDrag3[17].X , botonesDrag3[17].Y ); 
				botonesDrag3[17].setPosicion( Xtemp, Ytemp);
			}
			
			botonesNext3[3].setImagen(-1);
			botonesNext3[4].setImagen(-1);
			
			botonesNext3[3].setEnable(true);	
			botonesNext3[4].setEnable(false);
		}
	}
}