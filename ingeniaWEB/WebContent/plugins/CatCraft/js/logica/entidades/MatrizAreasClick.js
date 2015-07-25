//JavaScript Document - Albeiro Gualdrón - MatrizDeAreasClickables


var clickNext_1X;
var clickNext_1Y;
var clickNext_1l;
var clickNext_1h;

var dragOut_2X = [];
var dragOut_2Y = [];
var dragOut_2l;

var dragIn_2X = [];
var dragIn_2Y = [];

var clickNext_2X = [];
var clickNext_2Y = [];
var clickNext_2l;

var clickNext_2bX;
var clickNext_2bY;
var clickNext_2bl;
var clickNext_2bh;

var clickNext_2cX = [];
var clickNext_2cY = [];
var clickNext_2cl;
var clickNext_2ch;

var dragOut_3X = [];
var dragOut_3Y = [];
var dragOut_3l;

var dragOut_3bX;
var dragOut_3bY;
var dragOut_3bl;

var dragOut_3cX = [];
var dragOut_3cY = [];
var dragOut_3cl;

var dragIn_3X = [];
var dragIn_3Y = [];
var dragIn_3l;

var dragIn_3bX = [];
var dragIn_3bY = [];

var dragIn_3cX = [];
var dragIn_3cY = [];
var dragIn_3cl;

var dragIn_3dX = [];
var dragIn_3dY = [];
var dragIn_3dl;

var clickNext_3X;
var clickNext_3Y;
var clickNext_3l;
var clickNext_3h;

function Matriz_Areas_Click()
{

	this.llenarMatrices = function()
	{
		llenar_clickNext_1();
		llenar_dragOut_2();
		llenar_dragIn_2();
		llenar_clickNext_2();
		llenar_dragOut_3();
		llenar_dragIn_3();
		llenar_clickNext_3();
	}
//------------------------------------------------------------------------------------		
	
	function llenar_clickNext_1()
	{
		clickNext_1X = 340;
		clickNext_1Y = 300;
		clickNext_1l = 115;
		clickNext_1h = 43;
	}
//------------------------------------------------------------------------------------		
	
	function llenar_dragOut_2()
	{
		dragOut_2X = [32, 86, 137, 187];
		dragOut_2Y = [82, 131, 179, 227, 275, 323, 371, 418];
		dragOut_2l = 44;
	}
//------------------------------------------------------------------------------------		
	
	function llenar_dragIn_2()
	{
		dragIn_2X = [561, 615, 666, 716];
		dragIn_2Y = [86, 136, 182, 231];
	}
//------------------------------------------------------------------------------------		
	
	function llenar_clickNext_2()
	{
		clickNext_2X = [521, 588, 659, 729];
		clickNext_2Y = [323];
		clickNext_2l = 64;
		
		
		clickNext_2bX = 340;
		clickNext_2bY = 268;
		clickNext_2bl = 105;
		clickNext_2bh = 48; 
		
		
		clickNext_2cX = [544];
		clickNext_2cY = [312, 343, 374];
		clickNext_2cl = 51;
		clickNext_2ch = 24; 
	}
//------------------------------------------------------------------------------------

	
	function llenar_dragOut_3()
	{
		dragOut_3X = [337, 386, 436, 486];
		dragOut_3Y = [93];
		dragOut_3l = 40;
	
		dragOut_3bX = 324;
		dragOut_3bY = 152;
		dragOut_3bl = 60;
		
		dragOut_3cX = [495, 454];
		dragOut_3cY = [170];
		dragOut_3cl = 34;
	}
//------------------------------------------------------------------------------------	


	function llenar_dragIn_3()
	{
		dragIn_3X = [40, 95, 177, 232];
		dragIn_3Y = [124];
		dragIn_3l = 25;
		
		dragIn_3bX = [84, 135, 212, 263];
		dragIn_3bY = [210];
		
		dragIn_3cX = [54];
		dragIn_3cY = [161, 246, 334];
		dragIn_3cl = 40;
		
		dragIn_3dX = [184];
		dragIn_3dY = [120, 208];
		dragIn_3dl = 30;
	}
//------------------------------------------------------------------------------------	

function llenar_clickNext_3()
{
	clickNext_3X = 246;
	clickNext_3Y = 408;
	clickNext_3l = 46;
	clickNext_3h = 48;

}
//------------------------------------------------------------------------------------	
}



