// JavaScript Document
/*
 * fecha creacion 07/07/2015
 ** creador : alejandro parra
 ** descripcion: este archivo contiene funciones o clases comunes que permitan estandarizar acciones.
 */

/*
 * fecha creacion 07/07/2015
 ** creador : alejandro parra
 ** descripcion: esta clase es la encargada de configurar ancho y alto 
 */
var Size = function(width, height) {
	this.WIDTH = width;
	this.HEIGHT = height;
};// end class size

/*
 * fecha creacion 07/07/2015 * creador : alejandro parra * descripcion: esta
 * clase es la encargada de configurar X y Y.
 */

var Position = function(x, y) {
	this.X = x;
	this.Y = y;

};// /end class position

if (!Array.prototype.indexOf) {
	Array.prototype.indexOf = function(obj, fromIndex) {
		if (fromIndex == null) {
			fromIndex = 0;
		} else if (fromIndex < 0) {
			fromIndex = Math.max(0, this.length + fromIndex);
		}
		for (var i = fromIndex, j = this.length; i < j; i++) {
			if (this[i] === obj)
				return i;
		}
		return -1;
	};
}
