//JavaScript Document - Albeiro Gualdrón - InterpreteCodigo

function InterpreteCodigo()
{
	this.separarPalabras = function()
	{
		var arrayTemporal = [];
		var acierto = "no_asignado";
		j = 0;
		for( i = 0; i < codigoCompleto.length; i++ )
		{
			arrayTemporal = codigoCompleto[i].split(" ");
			//alert("\""+arrayTemporal+"\"");
			
			if( (acierto === "no_asignado" || acierto === "falso") && arrayTemporal[0] === "if(" )
			{
				if( (atribGato[j] === atribGato[j+1]) && (atribGato[j+2] === atribGato[j+3]) && acierto === "no_asignado")
					acierto = "verdadero";
				j+=5;
			}
			else if( (acierto === "no_asignado" || acierto === "falso")  && arrayTemporal[0] === "else" && arrayTemporal[1] === "if(" ) 
			{
				if( (atribGato[j] === atribGato[j+1]) && (atribGato[j+2] === atribGato[j+3]) && acierto === "no_asignado")
					acierto = "verdadero";
				j+=5;
			}
			else if(  (acierto === "no_asignado" || acierto === "falso")  && arrayTemporal[0] === "else" && arrayTemporal[1] === "{"  && arrayTemporal[3] === "GOLPE")
			{
				if( acierto === "no_asignado")
					acierto = "verdadero";
				j+=1;
			}
			else if( codigoCompleto[i] === "else {" )
			{
				//j-=1;
				//acierto = "no_asignado";
			}
			else if( codigoCompleto[i] === "{" )
			{
				j-=1;
				if(acierto === "verdadero")
					acierto = "no_asignado";
				else if(acierto === "no_asignado")
					acierto = "falso";
			}
			else if( codigoCompleto[i] === "}" )
			{
				if(acierto === "falso")
					acierto = "no_asignado";
			}
				
			if( acierto==="verdadero" && (arrayTemporal[2] === "GOLPE" || arrayTemporal[3] === "GOLPE"))
			{
				ataqueApropinar = atribGato[j-1]; //13
				break;
				/*for(i = 0; i < atribGato.length; i++)
				{
					alert("atributo:" + i + " ---"+ atribGato[i] );
				}*/
			}
		}
		//alert(ataqueApropinar + "..." + j);
		/*for(i = 0; i < atribGato.length; i++)
		{
			alert("atributo:" + i + " ---"+ atribGato[i] );
		}*/
		
	}
	
}