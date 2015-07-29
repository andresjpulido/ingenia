//JavaScript Document - Albeiro Gualdrón - ControladorAnimacion

var mtzAreasClick = new Matriz_Areas_Click();
var controladorEvMouse = new ControladorEvMouse();
var controlaGatos;
var botonesNext1y2 = [];
var botonesDrag1y2 = [];
var botonesNext3 = [];
var botonesDrag3 = [];
var botonesNext4 = [];
var pincel;
var canvas;
var armaRaton;


//----------------------------------------------------------------------------------------------------------------------------------------
function iniciar()
{
	mtzAreasClick.llenarMatrices();
	crearbotonesNext1y2();
	crearbotonesDrag1y2();
	crearbotonesNext3();
	crearbotonesDrag3();
	crearbotonesNext4();
	guardarMatrizSentenciasEn3();
	duplicarDragInParaAtaques();
	ObtenerCanvas();
	controladorEvMouse.addMouseEv();
	dibujarInicio();
	crearControladorGatos();
}
//----------------------------------------------------------------------------------------------------------------------------------------


function ObtenerCanvas()
{
	canvas = document.getElementById('canvas');
	pincel = canvas.getContext('2d');
}	
//----------------------------------------------------------------------------------------------------------------------------------------


function crearbotonesNext1y2()
{
	botonesNext1y2[0] = new areaClick( clickNext_1X, clickNext_1Y, clickNext_1l, clickNext_1h, 0, "Jugar", -1, true);
	botonesNext1y2[1] = new areaClick( clickNext_2bX, clickNext_2bY, clickNext_2bl, clickNext_2bh, 1, "Luchar", -1, true);
	for(i = 0; i < clickNext_2X.length; i++)
	{
		for(j = 0; j < clickNext_2Y.length; j++)
		{
			botonesNext1y2.push(new areaClick( clickNext_2X[i], clickNext_2Y[j], clickNext_2l, clickNext_2l, 1, "if_else",-1, true));
		}
	}
	for(i = 0; i < clickNext_2cX.length; i++)
	{
		for(j = 0; j < clickNext_2cY.length; j++)
		{
			botonesNext1y2.push( new areaClick( clickNext_2cX[i], clickNext_2cY[j], clickNext_2cl, clickNext_2ch, 1, "if_else"+j, -1, true ) );
		}
	}
	botonesNext1y2[3].setNombre("Switch");
	botonesNext1y2[4].setNombre("For");
	botonesNext1y2[5].setNombre("While");
}
//----------------------------------------------------------------------------------------------------------------------------------------
	
	
function crearbotonesDrag1y2()
{
	for(j = 0; j < dragOut_2Y.length; j++)
	{
		for(i = 0; i < dragOut_2X.length; i++)
		{
			botonesDrag1y2.push( new areaClick( dragOut_2X[i], dragOut_2Y[j], dragOut_2l, dragOut_2l, 1, "Rutina"+j+i, -1, true ) );	
		}
	}
	for(j = 0; j < dragIn_2Y.length; j++)
	{
		for(i = 0; i < dragIn_2X.length; i++)
		{
			botonesDrag1y2.push( new areaClick( dragIn_2X[i], dragIn_2Y[j], dragOut_2l, dragOut_2l, 1, "Sentencia"+j+i, -1, true ));
		}
	}
	botonesDrag1y2.push(new areaClick( dragOut_2bX, dragOut_2bY, dragOut_2bl, dragOut_2bh, 1, "papelera", -1, true));
}	
//----------------------------------------------------------------------------------------------------------------------------------------


function crearbotonesNext3()
{
	botonesNext3[0] = new areaClick( clickNext_3X[1], clickNext_3Y, clickNext_3l, clickNext_3h, 2, "OK", -1, true);
	botonesNext3[1] = new areaClick( clickNext_3X[0], clickNext_3Y, clickNext_3l, clickNext_3h, 2, "CANCELAR", -1, true);
	botonesNext3[2] = new areaClick( clickNext_3bX, clickNext_3bY, clickNext_3bl, clickNext_3bh, 2, "limpiar", -1, true);
	for(j = 0; j < dragIn_3dY.length; j++)
	{
		for(i = 0; i < dragIn_3dX.length; i++)
		{
			botonesNext3.push( new areaClick( dragIn_3dX[i], dragIn_3dY[j], dragIn_3dl, dragIn_3dl, 2, "add"+j, -1, true ) );	
		}
	}
}
//----------------------------------------------------------------------------------------------------------------------------------------


function crearbotonesDrag3()
{
	for(j = 0; j < dragOut_3Y.length; j++)
	{
		for(i = 0; i < dragOut_3X.length; i++)
		{
			botonesDrag3.push( new areaClick( dragOut_3X[i], dragOut_3Y[j], dragOut_3l, dragOut_3l, 2, "Gatico"+i, 50+i, true ) );	
		}
	}
	botonesDrag3.push( new areaClick( dragOut_3bX, dragOut_3bY, dragOut_3bl, dragOut_3bl, 2, "Gato", 12, true ) );
	for(j = 0; j < dragOut_3cY.length; j++)
	{
		for(i = 0; i < dragOut_3cX.length; i++)
		{
			botonesDrag3.push( new areaClick( dragOut_3cX[i], dragOut_3cY[j], dragOut_3cl, dragOut_3cl, 2, "Escudo", 55-i, true ) );	
		}
	}
	botonesDrag3[botonesDrag3.length-1].setNombre("Espada");
	for(j = 0; j < dragIn_3Y.length; j++)
	{
		for(i = 0; i < dragIn_3X.length; i++)
		{
			botonesDrag3.push( new areaClick( dragIn_3X[i], dragIn_3Y[j], dragIn_3l, dragIn_3l, 2, "insertGatito0"+i, -1, true ) );	
		}
	}
	botonesDrag3[9].setEnable(false);
	botonesDrag3[10].setEnable(false);
	for(j = 0; j < dragIn_3bY.length; j++)
	{
		for(i = 0; i < dragIn_3bX.length; i++)
		{
			botonesDrag3.push( new areaClick( dragIn_3bX[i], dragIn_3bY[j], dragIn_3l, dragIn_3l, 2, "insertGatito1"+i, -1, true ) );
							
		}
	}
	botonesDrag3[13].setEnable(false);
	botonesDrag3[14].setEnable(false);
	for(j = 0; j < dragIn_3cY.length; j++)
	{
		for(i = 0; i < dragIn_3cX.length; i++)
		{
			botonesDrag3.push( new areaClick( dragIn_3cX[i], dragIn_3cY[j], dragIn_3cl, dragIn_3cl, 2, "insertAtaque"+j, -1, true ) );	
		}
	}
}	
//------------------------------------------------------------------------------------------------------------------------------------------------------------------


function crearbotonesNext4()
{
	botonesNext4[0] = new areaClick( clickNext_4X, clickNext_4Y, clickNext_4l, clickNext_4h, 3, "new_run", -1, false);
}
//--------------------------------------------------------------------------------------------------------------------------------------------------------------


function duplicarDragInParaAtaques()
{	
	for( i = 0; i < 16; i++)
	{
		botonesDrag3.push( new areaClick( botonesDrag1y2[32+i].X, botonesDrag1y2[32+i].Y, botonesDrag1y2[32+i].l, 
										  botonesDrag1y2[32+i].h, botonesDrag1y2[32+i].vista, botonesDrag1y2[32+i].nombre, 
										  botonesDrag1y2[32+i].img, botonesDrag1y2[32+i].enable) );
		botonesDrag3[botonesDrag3.length-1].setPosicion(botonesDrag3[botonesDrag3.length-1].X+5, botonesDrag3[botonesDrag3.length-1].Y-2);
		botonesDrag3[botonesDrag3.length-1].setImagen(63+i);
		botonesDrag3[botonesDrag3.length-1].setNombre("Ataque"+i);
		botonesDrag3[botonesDrag3.length-1].setVista(2);
	}
}
//------------------------------------------------------------------------------------------------------------------------------------------------


function guardarMatrizSentenciasEn3()
{
	for( i = 0; i < 16; i++)
	{
		botonesDrag3.push(botonesDrag1y2[32+i]);
	}
}

//------------------------------------------------------------------------------------------------------------------------------------------------
function moverMatrizSentencias()
{	
	if(vistaActiva === 2)
	{
		y = 32;
		for(j = 0; j < dragIn_2Y.length; j++)
		{
			for(i = 0; i < dragIn_2X.length; i++)
			{
				botonesDrag1y2[y].setPosicion( dragIn_2X[i]-238, dragIn_2Y[j]+187);
				botonesDrag1y2[y].setVista(2);
				y++;
			}
		}
	}
	else if (vistaActiva === 1)
	{
		y = 32;
		for(j = 0; j < dragIn_2Y.length; j++)
		{
			for(i = 0; i < dragIn_2X.length; i++)
			{
				botonesDrag1y2[y].setPosicion( dragIn_2X[i], dragIn_2Y[j]);
				botonesDrag1y2[y].setVista(1);
				y++;
			}
		}
	}
}
//------------------------------------------------------------------------------------------------------------------------------------------------

function crearControladorGatos()
{
	gatosAcrear = [];
	gatosAcrear.length = 0;
	
	for (var int = 0; int < Juego.Gatos.length; int++) {
		//var array_element = ;
		gatosAcrear.push( new Gato(Juego.Gatos[int].color.nombre, (Juego.Gatos[int].tipogato.nombre == "normal"?false:true), (Juego.Gatos[int].arma === null?false:true), (Juego.Gatos[int].armadura === null?false:true)));
	}
	controlaGatos = controlaGatos || new ControladorGatos(gatosAcrear);
	//controlaGatos = new ControladorGatos(gatosAcrear);
	controlaGatos.crearGatos();
	//alert(gatosDelNivel[0].color);
}


function resolverColicion()
{	
	
	armaRaton = new Arma(ataqueApropinar);
	//alert("GATO " + gatosDelNivel[gatoEnFrente].atributos + " atacó al ratón");
	//alert("RATÓN contra-ataca con: "+ armaRaton.nombre);
	//alert(armaRaton.nombre + " es efectiva contra GATO " + armaRaton.calcularEfectividad());
	
	if(gatosDelNivel[gatoEnFrente].atributos === armaRaton.calcularEfectividad())
	{
		//alert("gato murio");
		//gatosDelNivel[gatoEnFrente].setIsCaminando(false);
		dibujarGatoMuere(0);
		//siguienteGato();
	}
	else
	{
		//alert("raton Murio");
		gatosDelNivel[gatoEnFrente].setIsCaminando(false);
		dibujarGatoComeRaton(0);
	}
}
//--------------------------------------------------------------------------------------------------------------------------------------------


function siguienteGato()
{
	raton.setPuntajeAcumulado(gatosDelNivel[gatoEnFrente].puntaje);
	if(gatoEnFrente < gatosDelNivel.length-1)
	{
		gatoEnFrente++;
		codigoCompleto = [];
		enColision = false;
		pincel.fillStyle = "rgb(0,0,0)";
		pincel.fillRect(0,0, 800, 500);
		dibujarCaminataGato(0); 
	}
	else
		dibujarYouWin();
}
//--------------------------------------------------------------------------------------------------------------------------------------------

	

//----------------------------------------------------------------------------------------------------------------------------------------
