//JavaScript Document - Albeiro Gualdrón - Dibujante

var posXpata = [140, 360, 400, 650];
var posYpata = [320, 250, 40, 0];
var gatoEnFrente = 0;
var enColision = false;
var interpreteCodigo;


function dibujarInicio()
{
	pincel.fillStyle = "rgb(0, 0, 0)";
	pincel.fillRect(0, 0, 800, 500);
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
			if( codigoCompleto[i] === "{" || codigoCompleto[i] === "else {" )
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
			setTimeout(function(){dibujarCodigo();dibujarCodigoLento(++i, tabulado);}, 300);
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
				if( codigoCompleto[i] === "{" || codigoCompleto[i] === "else {")
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
}
//---------------------------------------------------------------------------------------------------------------


function dibujarCaminataGato(img) 
{
	
  	i = img%4;
	pincel.drawImage(imagenes[3], 0, 25);
	dibujarPuntaje();
	dibujarRaton(img);
	elegirGatosADibujar(i);
	if( gatosDelNivel[gatoEnFrente].X < 600)
		setTimeout(function(){dibujarCaminataGato(++img);}, 24);
	else
	{
		enColision = true;
		if(gatoEnFrente < raton.ejecucion.length )
		{
			raton.ejecucion[gatoEnFrente].inicializar();
			if( funcionActual === 15 )
				raton.ejecucion[gatoEnFrente].if_elseif_else();
			else if (funcionActual === 82)
				raton.ejecucion[gatoEnFrente].if_();
			else if (funcionActual === 149)
				raton.ejecucion[gatoEnFrente].if_else();
			//raton.ejecucion[0].if_elseif_elseFuncion();
			dibujarCodigo(0);
			setTimeout("dibujarCodigoLento(0, 0);", 500);
		}
		else
		{
			alert("ratón perdio porque no tiene más código para ejecutar");
			gatosDelNivel[gatoEnFrente].setIsCaminando(false);
			dibujarGatoComeRaton(0);
		}
	}
}
//-------------------------------------------------------------------------------------------------------------------------


function elegirGatosADibujar(i)
{
	for(g = 0; g < gatosDelNivel.length; g++)
	{
		if(gatosDelNivel[g].isCAminando)
		{
			if(!enColision)
				gatosDelNivel[g].setX(gatosDelNivel[g].X+3);
			dibujarArma(g);
			pincel.drawImage(imagenes[gatosDelNivel[g].secuenciaCaminar[i]], gatosDelNivel[g].X, gatosDelNivel[g].Y);
			dibujarEscudo(g);
			if( (g+1) < gatosDelNivel.length && !gatosDelNivel[g+1].isCAminando && gatosDelNivel[g].X > 150 )
				gatosDelNivel[g+1].setIsCaminando(true);
		}
	}
}
//---------------------------------------------------------------------------------------------------------------------------


function dibujarRaton(img)
{
	if(img%40 >= 0 && img%40 <= 2)
		pincel.drawImage(imagenes[5], 650, 368);
	else
		pincel.drawImage(imagenes[4], 650, 368);
} 
//----------------------------------------------------------------------------------------------------------------------------


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


function dibujarNewRun()
{
	pincel.drawImage(imagenes[81], 703, 375);
	botonesNext4[0].setEnable(true); 
}
//-----------------------------------------------------------------------------------


function dibujarSegundaSeleccion(X, Y, n_imagen)
{
	if (vistaActiva === 1)
	{
		pincel.drawImage(imagenes[n_imagen], X, Y);
	}
}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------


function dibujarGatoMuere(i)
{
	pincel.drawImage(imagenes[3], 0, 25);
	dibujarPuntaje();
	if( i < gatosDelNivel[gatoEnFrente].secuenciaMorir.length)
	{
		elegirGatosADibujar(0);
		if(i === 0)
			dibujarGolpeAsestado(0);
		else
		{
			setTimeout(function(){dibujarGatoMuere(++i);},32);
			pincel.drawImage(imagenes[gatosDelNivel[gatoEnFrente].secuenciaMorir[i]], gatosDelNivel[gatoEnFrente].X-(15*i), gatosDelNivel[gatoEnFrente].Y-=10);
		}
		dibujarRaton(10);
	}
	else if ( i <=  (gatosDelNivel[gatoEnFrente].secuenciaMorir.length*2) +1)
	{
		elegirGatosADibujar(0);
		pincel.drawImage(imagenes[gatosDelNivel[gatoEnFrente].secuenciaMorir[4]], gatosDelNivel[gatoEnFrente].X-(15*i), gatosDelNivel[gatoEnFrente].Y+=(20) );
		dibujarRaton(10);
		setTimeout(function(){dibujarGatoMuere(++i);},24);
	}
	else
		siguienteGato();
}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------


function dibujarGatoComeRaton(i)
{
	if(vistaActiva === 3)
	{
		pincel.drawImage(imagenes[3], 0, 25);
		dibujarPuntaje();
		if( i < gatosDelNivel[gatoEnFrente].secuenciaComer.length-2)
		{
			elegirGatosADibujar(0);
			pincel.drawImage(imagenes[gatosDelNivel[gatoEnFrente].secuenciaComer[i]], gatosDelNivel[gatoEnFrente].X+20, gatosDelNivel[gatoEnFrente].Y);
			dibujarRaton(10);
			setTimeout(function(){dibujarGatoComeRaton(++i);},200);
		}
		else if ( i < gatosDelNivel[gatoEnFrente].secuenciaMorir.length)
		{
			elegirGatosADibujar(0);
			pincel.drawImage(imagenes[gatosDelNivel[gatoEnFrente].secuenciaComer[i]], gatosDelNivel[gatoEnFrente].X+30, gatosDelNivel[gatoEnFrente].Y);
			setTimeout(function(){dibujarGatoComeRaton(++i);},100);
		}
		else if ( i < 500 )
		{
			elegirGatosADibujar(0);
			pincel.drawImage(imagenes[gatosDelNivel[gatoEnFrente].secuenciaComer[(i%2)+3]], gatosDelNivel[gatoEnFrente].X+30, gatosDelNivel[gatoEnFrente].Y);
			dibujarGameOver();
			setTimeout(function(){dibujarGatoComeRaton(++i);},200);
		}
		else
			dibujarGameOver();
	}
}

//----------------------------------------------------------------------------------------------------------------------------------------------------------------


function dibujarEscudo(g)
{
	if( gatosDelNivel[g].armadura > 0)
		pincel.drawImage(imagenes[gatosDelNivel[g].armadura+111], gatosDelNivel[g].X+6, gatosDelNivel[g].Y+25);
}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------


function dibujarArma(g)
{
	if( gatosDelNivel[g].arma )
		pincel.drawImage(imagenes[115], gatosDelNivel[g].X+28, gatosDelNivel[g].Y-5);
}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------


function dibujarGameOver()
{
		pincel.drawImage(imagenes[48], 100, -10);
		dibujarNewRun();
}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------


function dibujarPuntaje()
{
	pincel.font = "bold 30px consolas";
	pincel.fillStyle = "rgb(255,204,0)";
	Xtexto = 18;
	Ytexto = 80;
	MAXtexto = 284;
	pincel.drawImage(imagenes[80], Xtexto-20, Ytexto-52);
	pincel.fillText(raton.puntajeAcumulado, Xtexto , Ytexto , MAXtexto);
	pincel.font = "bold 15px consolas";
	pincel.fillText("   pts", Xtexto+4 , Ytexto+20 , MAXtexto);
}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------


function dibujarYouWin()
{
	pincel.fillStyle = "rgb(0,0,0)";
	pincel.fillRect(0,0, 800, 500);
	pincel.drawImage(imagenes[3], 0, 25);
	dibujarPuntaje();
	pincel.drawImage(imagenes[79], 100, -10);
	dibujarRaton(3);
	dibujarNewRun();
	
}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------


function dibujarGolpeAsestado(i)
{
	if(i <= 4)
	{
		pincel.drawImage(imagenes[3], 0, 25);
		dibujarPuntaje();
		if(i === 4)
		{
			gatosDelNivel[gatoEnFrente].setIsCaminando(false);
			pincel.drawImage(imagenes[armaRaton.secuenciaGolpe[0]], 550, 340);
			
		}
		elegirGatosADibujar(3);
		dibujarRaton();
		pincel.drawImage(imagenes[armaRaton.secuenciaGolpe[1+i]], 574, 324);
		if(i === 4)
		{
			pincel.drawImage(imagenes[armaRaton.secuenciaGolpe[0+i]], 574, 324);
			pincel.drawImage(imagenes[gatosDelNivel[gatoEnFrente].secuenciaMorir[0]], gatosDelNivel[gatoEnFrente].X-15, gatosDelNivel[gatoEnFrente].Y);
		}
		setTimeout(function(){dibujarGolpeAsestado(++i);}, 24);
	}
	if(i === 4)
		setTimeout(function(){dibujarGatoMuere(1);}, 1500);
	
}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------


function dibujarSentenciaIF()
{
	
	
}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------


function dibujarStorkedePrueba()
{
	/*for( i = 0; i < botonesDrag1y2.length; i++)
	{
		pincel.strokeRect(botonesDrag1y2[i].X, botonesDrag1y2[i].Y, botonesDrag1y2[i].l, botonesDrag1y2[i].h);
	}*/
	
	pincel.strokeRect(703, 375, 100, 100);
	
	//pincel.strokeRect(14, 408, 46, 48);
	//pincel.strokeRect(130, 430, 58, 28);
	
	/*pincel.strokeRect(337, 93, 40, 40);//gato
	pincel.strokeRect(386, 93, 40, 40);//gato
	pincel.strokeRect(436, 93, 40, 40);//gato
	pincel.strokeRect(486, 93, 40, 40);//gato
	
	pincel.strokeRect(324, 152, 60, 60);//GATO
	
	pincel.strokeRect(495, 170, 34, 34);//escudo
	pincel.strokeRect(454, 170, 34, 34);//espada
	

	pincel.strokeRect(744, 414, 50, 60);//bin
	
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
