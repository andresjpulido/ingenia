// JavaScript Document
/*
** 
** fecha creacion: 04/07/2015
** creador: alejandro parra
** Descripcion: codigo que realiza la creacion de un objeto, este es descargado de la pagina 
** https://jherax.wordpress.com/2014/07/20/js-clonando-objetos/
*/
function clonador (source, dest) {
    // @Private
    var _objects = [];
 
    // guardamos una referencia local del método toString,
    // ésta técnica se llama: dependancy declaration pattern
    var _toString = Object.prototype.toString;
 
    var _fnExtend = function (from, to) {
        var prop;
        // verificamos si @from hace referencia a un objeto ya creado
        if (_toString.call(from) == "[object Object]") {
            if (_objects.filter(function (item) {
                return item === from;
            }).length) return from;
            // guarda la referencia de los objetos creados
            _objects.push(from);
        }
        // determina si @from es un valor primitivo o una función
        if (from == null || typeof from != "object") return from;
        // determina si @from es una instancia de alguno de los siguientes prototipos
        if (from.constructor == Date || from.constructor == RegExp || from.constructor == Function ||
            from.constructor == String || from.constructor == Number || from.constructor == Boolean) {
            return new from.constructor(from);
        }
        if (from.constructor != Object && from.constructor != Array) return from;
        // itera recursivamente las propiedades del objeto
        to = to || new from.constructor();
        for (prop in from) {
            to[prop] = typeof to[prop] == "undefined" ? _fnExtend(from[prop], null) : to[prop];
        }
        return to;
    };
    // Lazy Function Definition Pattern
    fnExtend = function (from, to) {
        var cloned = _fnExtend(from, to);
        _objects = [];
        return cloned;
    };
    //invoca la función inmediatamente para crear el closure,
    //y permitir que Lazy Function redefina la función fnExtend
    return fnExtend(source, dest);
}///end object clonador
