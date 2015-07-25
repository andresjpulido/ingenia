//JavaScript Document - Albeiro Gualdrón - Dibujante

var posXpata = [140, 360, 400, 650];
var posYpata = [320, 250, 40, 0];
var gatoEnFrente = 0;
var enColision = false;
var interpreteCodigo;



function dibujarInicio()
{
	pincel.fillRect(0,0, 800, 500);
	pincel.drawImage(imagenes[3], 0, 25);
	pincel.drawImage(imagenes[6], 120, 50);
	pincel.drawImage(imagenes[7], 340, 300);	
} 
//-----------------------------------------------------------------------------------

function dibujarCodigoLento( i, tabulado )
{
	pincel.font = "Bold 14px consolas";
	pincel.fillStyle = "rgb(0, 255, 0)";
	Xtexto = 500;
	Ytexto = 65;
	MAXtexto = 284;
	if(codigoCompleto[i] !== undefined)
	{
		if(i < 14)
		{
			if( codigoCompleto[i] === "{")
			{
				pincel.fillText(codigoCompleto[i], Xtexto+tabulado, Ytexto +(15*i), MAXtexto-tabulado);
				tabulado+=10;
			}
			else if(codigoCompleto[i] === "}")
			{
				tabulado-=10;
				pincel.fillText(codigoCompleto[i], Xtexto+tabulado, Ytexto +(15*i), MAXtexto-tabulado);
			}
			else
				pincel.fillText(codigoCompleto[i], Xtexto+tabulado, Ytexto +(15*i), MAXtexto-tabulado);
			setTimeout(function(){dibujarCodigo();dibujarCodigoLento(++i, tabulado);}, 500);
		}
		else
			pincel.fillText(". . .", Xtexto, Ytexto+(15*i), MAXtexto-tabulado);
	}
	else
	{
		interpreteCodigo = new InterpreteCodigo();
		interpreteCodigo.separarPalabras();
		resolverColicion();
	}
}
//-------------------------------------------------------------------------------------------------------------------------
function dibujarCodigo()
{
	pincel.drawImage(imagenes[9], 480, 30);
	pincel.font = "14px consolas";
	pincel.fillStyle = "rgb(255,255,255)";
	tabulado = 0;
	Xtexto = 500;
	Ytexto = 65;
	MAXtexto = 284;
	
	for(i = 0; i < codigoCompleto.length;i++ )
	{
		if(codigoCompleto[i] !== undefined)
		{
			if(i < 14)
			{
				if( codigoCompleto[i] === "{")
				{
					pincel.fillText(codigoCompleto[i], Xtexto+tabulado, Ytexto +(15*i), MAXtexto-tabulado);
					tabulado+=10;
				}
				else if(codigoCompleto[i] === "}")
				{
					tabulado-=10;
					pincel.fillText(codigoCompleto[i], Xtexto+tabulado, Ytexto +(15*i), MAXtexto-tabulado);
				}
				else
					pincel.fillText(codigoCompleto[i], Xtexto+tabulado, Ytexto +(15*i), MAXtexto-tabulado);
			}
			else
			{
				pincel.fillText(". . .", Xtexto, Ytexto+(15*i), MAXtexto-tabulado);
				break;
			}
		}
	}
}
//----------------------------------------------------------------------------------------------------------------------


function detenerTiempo(milisegundos)
{
	fin = false;
	fecha0 = new Date();
	inicio = fecha0.getTime();
	while(!fin)
	{
		fecha0 = new Date();
		if( ( fecha0.getTime() - inicio) > milisegundos)
			fin = true;
	}
	//alert("OK");
}
//-----------------------------------------------------------------------------------

function dibujarCaminataGato(img) 
{
  	i = img%4;
	pincel.drawImage(imagenes[3], 0, 25);
	dibujarRaton(img);
	elegirGatosADibujar(i);
	if( gatosDelNivel[gatoEnFrente].X < 600)
		setTimeout(function(){dibujarCaminataGato(++img);}, 24);
	else
	{
		enColision = true;
		raton.ejecucion[0].if_elseif_else();
		raton.ejecucion[0].if_elseif_elseFuncion();
		dibujarCodigo(0);
		setTimeout("dibujarCodigoLento(0, 0);", 500);
	}
}
//-----------------------------------------------------------------------------------
function elegirGatosADibujar(i)
{
	for(g = 0; g < gatosDelNivel.length; g++)
	{
		if(gatosDelNivel[g].isCAminando)
		{
			if(!enColision)
				gatosDelNivel[g].setX(gatosDelNivel[g].X+3);
			pincel.drawImage(imagenes[gatosDelNivel[g].secuenciaCaminar[i]], gatosDelNivel[g].X, gatosDelNivel[g].Y);
			if( (g+1) < gatosDelNivel.length && !gatosDelNivel[g+1].isCAminando && gatosDelNivel[g].X > 150 )
				gatosDelNivel[g+1].setIsCaminando(true);
		}
	}
}

function dibujarRaton(img)
{
	if(img%40 === 0)
		pincel.drawImage(imagenes[5], 650, 368);
	else
		pincel.drawImage(imagenes[4], 650, 368);
} 
//-----------------------------------------------------------------------------------


function dibujarPatasOK(pat)
{	
	pincel.drawImage(imagenes[8], posXpata[pat], posYpata[pat]);
	if(pat < 4)
		setTimeout(function(){dibujarPatasOK(++pat);},500);
	else
		dibujarSelectorRutina();
}
//-----------------------------------------------------------------------------------


function dibujarSelectorRutina()
{
	pincel.fillRect(0,0, 800, 500);
	pincel.drawImage(imagenes[10], 0, 25);
	//dibujarStorkedePrueba();
}
//-----------------------------------------------------------------------------------



function dibujarCreadorFunciones(img)
{
	pincel.fillRect(0,0, 800, 500);
	pincel.drawImage(imagenes[11], 0, 25);
	pincel.drawImage(imagenes[img], -6, 100);
	//dibujarStorkedePrueba();

}
//-----------------------------------------------------------------------------------


function pintarArrastre(X, Y, n_imagen)
{
	//if (vistaActiva === 1)
		pincel.drawImage(imagenes[n_imagen], X, Y);
}
//-----------------------------------------------------------------------------------


function dibujarSegundaSeleccion(X, Y, n_imagen)
{
	if (vistaActiva === 1)
	{
		pincel.drawImage(imagenes[n_imagen], X, Y);
	}
}
//-----------------------------------------------------------------------------------



function dibujarStorkedePrueba()
{
	for( i = 0; i < botonesDrag1y2.length; i++)
	{
		pincel.strokeRect(botonesDrag1y2[i].X, botonesDrag1y2[i].Y, botonesDrag1y2[i].l, botonesDrag1y2[i].h);
	}
	/*pincel.strokeRect(337, 93, 40, 40);//gato
	pincel.strokeRect(386, 93, 40, 40);//gato
	pincel.strokeRect(436, 93, 40, 40);//gato
	pincel.strokeRect(486, 93, 40, 40);//gato
	
	pincel.strokeRect(324, 152, 60, 60);//GATO
	
	pincel.strokeRect(495, 170, 34, 34);//escudo
	pincel.strokeRect(454, 170, 34, 34);//espada
	

	
	
	pincel.strokeRect(40, 124, 25, 25);//If1
	pincel.strokeRect(95, 124, 25, 25);//If2
	pincel.strokeRect(177, 124, 25, 25);//If3
	pincel.strokeRect(232, 124, 25, 25);//If4
	
	pincel.strokeRect(84, 210, 25, 25);//If6
	pincel.strokeRect(135, 210, 25, 25);//If7
	pincel.strokeRect(212, 210, 25, 25);//If5
	pincel.strokeRect(264, 210, 25, 25);//If6
	
	pincel.strokeRect(54, 161, 40, 40);//If5
	pincel.strokeRect(54, 246, 40, 40);//If10
	pincel.strokeRect(54, 334, 40, 40);//If11
	
	pincel.strokeRect(184, 120, 30, 30);//+1
	pincel.strokeRect(184, 208, 30, 30);//+2
	
	pincel.drawImage(imagenes[13], 128, 116);//and1
	pincel.drawImage(imagenes[14], 168, 200);//and2

	pincel.strokeRect(246, 408, 46, 48);//ok*/
	

	

}
//-----------------------------------------------------------------------------------
