//JavaScript Document - Albeiro Gualdrón - ControladorEventosMouse
var vistaActiva = 0;
var presionaMouse = false;
var funcionesFx = [];
var raton;


function ControladorEvMouse()
{	
	botonesNext = [];
	botonesDrag = [];
	isOpcion2 = false;
	botonDown = -1;
	enviar = true;
	funciones = 0;
	
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
			if(botonesNext[i]!==null)
			if( X >= botonesNext[i].X && X <= (botonesNext[i].X+botonesNext[i].l) && botonesNext[i].vista === vistaActiva)
			{
				if( Y >= botonesNext[i].Y && Y <= (botonesNext[i].Y+botonesNext[i].h) )
				{
					btnClick = botonesNext[i].nombre;
					botonClickeado_accion( btnClick );
					break;
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
					dibujarCreadorFunciones(15);
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
						botonDown = botonesDrag[i].img;
					}
					botonPresSolt_accion( btnClick, botonesDrag[i]);
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
	}	
//---------------------------------------------------------------------------------------------------------------------------------	

	
	function botonPresSolt_accion( btnClick, btnDrg )
	{
		if(vistaActiva === 0)
			chequeoDragVista0();
		else if(vistaActiva === 1)
			chequeoDragVista1(btnDrg);
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
				vistaActiva = 4;
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
		else if( btnClick === "if_else0" || btnClick === "if_else1" || btnClick === "if_else2" )
		{
			vistaActiva = 2;
			moverMatrizSentencias();
			elegirPaqBotonesXvista();
			dibujarCreadorFunciones(15);
			solicitarPintadoBotones();
		}
		else if( btnClick === "Switch")
		{
			vistaActiva = 2;
			moverMatrizSentencias();
			elegirPaqBotonesXvista();
			dibujarCreadorFunciones(15);
			solicitarPintadoBotones();
		}	
	}
//---------------------------------------------------------------------------------------------------------------------------------	

	
	function chequeoClickVista2()
	{
		if( btnClick === "OK")
		{
			enviar = true;
			validarIf_elseIf_else();
			vistaActiva = 1;
			moverMatrizSentencias();
			dibujarSelectorRutina();
			elegirPaqBotonesXvista();
			solicitarPintadoBotones();
		}
		else if( btnClick === "add0")
		{
			botonesDrag3[9].setEnable(true);
			botonesDrag3[10].setEnable(true);
			botonesNext3[1].setImagen(13);
			botonesNext3[1].setEnable(false);
			dibujarCreadorFunciones(15);
			solicitarPintadoBotones();
		}
		else if( btnClick === "add1")
		{
			botonesDrag3[13].setEnable(true);
			botonesDrag3[14].setEnable(true);
			botonesNext3[2].setImagen(14);
			botonesNext3[2].setEnable(false);
			dibujarCreadorFunciones(15);
			solicitarPintadoBotones();
		}
	}
//---------------------------------------------------------------------------------------------------------------------------------	


	function validarIf_elseIf_else()
	{
		if( botonesDrag3[7].img !== 62)
			 enviar = false;
		else if( botonesDrag3[8].img < 56 || botonesDrag3[8].img > 61 )
			 enviar = false;
		else if( botonesDrag3[9].img !== 62 && botonesDrag3[9].enable )
			 enviar = false;	
		else if( (botonesDrag3[10].img < 56 || botonesDrag3[10].img > 61) && botonesDrag3[10].enable)
			 enviar = false; 
		else if( (botonesDrag3[15].img < 63 || botonesDrag3[15].img > 78) && (botonesDrag3[15].img < 16 || botonesDrag3[15].img > 31) )
			 enviar = false; 
		else if( botonesDrag3[11].img !== 62 )
			 enviar = false; 
		else if( botonesDrag3[12].img < 56 || botonesDrag3[12].img > 61 )
			 enviar = false;
		else if( botonesDrag3[13].img !== 62 && botonesDrag3[13].enable )
			 enviar = false;	 
		else if( (botonesDrag3[14].img < 56 || botonesDrag3[14].img > 61) && botonesDrag3[14].enable)
			 enviar = false; 
		else if( (botonesDrag3[16].img < 63 || botonesDrag3[16].img > 78)  && (botonesDrag3[16].img < 16 || botonesDrag3[16].img > 31))
			 enviar = false; 
		else if( (botonesDrag3[17].img < 63 || botonesDrag3[17].img > 78) && (botonesDrag3[17].img < 16 || botonesDrag3[17].img > 31))
			 enviar = false;
		
		if(enviar && funciones < 16)
		{
			crearSentenciaIf();
			botonesDrag1y2[funciones+32].setImagen(funciones+16);
			funciones++;
		}
		else if(!enviar)
			alert("...sentencia incompleta o incorrecta...");
		else
			alert("Máximo número de sentencias creadas");
	}
//---------------------------------------------------------------------------------------------------------------------------------	
	function crearSentenciaIf()
	{
		
		ifObjeto = [botonesDrag3[7].img,  botonesDrag3[8].img,  botonesDrag3[9].img,  botonesDrag3[10].img,	
					botonesDrag3[15].img, botonesDrag3[11].img, botonesDrag3[12].img, botonesDrag3[13].img, 
					botonesDrag3[14].img, botonesDrag3[16].img, botonesDrag3[17].img];			
		funcionesFx.push (new sentenciaIf( "funcion"+funciones ) );
		funcionesFx[funcionesFx.length-1].setIfObjetos(ifObjeto);
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


	function chequeoDragVista1(btnDrg)
	{
		//alert(btnClick);
		if(!presionaMouse)
		{
			for(i = 0; i < 4; i++)
			{
				for(j = 0; j < 8; j++)
				{
					if( btnClick === "Rutina"+j+i)
					{
						btnDrg.setImagen(botonDown);
						solicitarPintadoBotones();
					}
				}
			}
			botonDown = -1;
		}
		
	}
//---------------------------------------------------------------------------------------------------------------------------------	


	function chequeoDragVista2(btnDrg)
	{
		if(!presionaMouse)
		{
			if( btnClick === "insertGatito00" || btnClick === "insertGatito02" || btnClick === "insertGatito10" || btnClick === "insertGatito12")
			{
				if(botonDown === 12 && btnDrg.enable )
				{
					btnDrg.setImagen(62);
					solicitarPintadoBotones();
				}
			}
			else if( btnClick === "insertGatito01" || btnClick === "insertGatito11" || btnClick === "insertGatito13" || btnClick === "insertGatito03")
			{
				if((botonDown >= 50  && botonDown <= 55) && btnDrg.enable )
				{
					btnDrg.setImagen(botonDown+6);
					solicitarPintadoBotones();
				}
			}
			else if( btnClick === "insertAtaque0" || btnClick === "insertAtaque1" || btnClick === "insertAtaque2")
			{
				if( (botonDown >= 63  && botonDown <= 78 ) || (botonDown >= 16  && botonDown <= 31 ))
				{
					btnDrg.setImagen(botonDown);
					solicitarPintadoBotones();
				}
			}
			botonDown = -1;
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
		pintarArrastre(X-24, Y-22, botonDown);
	}
//---------------------------------------------------------------------------------------------------------------------------------	


	function chequeoMoveVista2()
	{
		dibujarCreadorFunciones(15);
		solicitarPintadoBotones();
		pintarArrastre(X-22, Y-22, botonDown);
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
}