# Host: localhost  (Version: 5.6.25-log)
# Date: 2015-07-15 19:50:37
# Generator: MySQL-Front 5.3  (Build 4.214)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "arma"
#

DROP TABLE IF EXISTS `arma`;
CREATE TABLE `arma` (
  `idarma` int(11) NOT NULL,
  `nombre_arma` varchar(45) NOT NULL,
  `puntaje` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idarma`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "arma"
#

INSERT INTO `arma` VALUES (1,'espada',10),(2,'hacha',10),(3,'daga',10),(4,'mano',10);

#
# Structure for table "armadura"
#

DROP TABLE IF EXISTS `armadura`;
CREATE TABLE `armadura` (
  `idarmadura` int(11) NOT NULL,
  `tipo_armadura` varchar(45) NOT NULL,
  `puntaje` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idarmadura`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "armadura"
#

INSERT INTO `armadura` VALUES (1,'tela',10),(2,'cuero',10),(3,'malla',10);

#
# Structure for table "color"
#

DROP TABLE IF EXISTS `color`;
CREATE TABLE `color` (
  `idcolor` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `puntaje` int(45) DEFAULT NULL,
  PRIMARY KEY (`idcolor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "color"
#

INSERT INTO `color` VALUES (1,'azul',10),(2,'verde',10),(3,'rojo',10);

#
# Structure for table "estructura"
#

DROP TABLE IF EXISTS `estructura`;
CREATE TABLE `estructura` (
  `idestructura` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`idestructura`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "estructura"
#

INSERT INTO `estructura` VALUES (1,'if'),(2,'while'),(3,'for'),(4,'switch'),(5,'or'),(6,'and'),(7,'mayor que'),(8,'igual que '),(9,'menor que'),(10,'diferente que');

#
# Structure for table "juego"
#

DROP TABLE IF EXISTS `juego`;
CREATE TABLE `juego` (
  `idjuego` int(11) NOT NULL,
  `nombre` varchar(65) DEFAULT NULL,
  `descripcion_juego` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idjuego`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "juego"
#

INSERT INTO `juego` VALUES (1,'gato','catcraft');

#
# Structure for table "movimiento"
#

DROP TABLE IF EXISTS `movimiento`;
CREATE TABLE `movimiento` (
  `idmovimientos` int(11) NOT NULL,
  `nombre_movimiento` varchar(45) NOT NULL,
  PRIMARY KEY (`idmovimientos`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "movimiento"
#

INSERT INTO `movimiento` VALUES (1,'a'),(2,'b'),(3,'c'),(4,'d');

#
# Structure for table "opcion"
#

DROP TABLE IF EXISTS `opcion`;
CREATE TABLE `opcion` (
  `idopcion` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `descripcion` varchar(300) DEFAULT NULL,
  `codigo` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`idopcion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "opcion"
#


#
# Structure for table "rol"
#

DROP TABLE IF EXISTS `rol`;
CREATE TABLE `rol` (
  `idRol` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idRol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "rol"
#


#
# Structure for table "opcionrol"
#

DROP TABLE IF EXISTS `opcionrol`;
CREATE TABLE `opcionrol` (
  `idopcion` int(11) NOT NULL,
  `idRol` int(11) NOT NULL,
  PRIMARY KEY (`idopcion`,`idRol`),
  KEY `fk_opcionXrol_rol1_idx` (`idRol`),
  CONSTRAINT `fk_opcionXrol_opcion1` FOREIGN KEY (`idopcion`) REFERENCES `opcion` (`idopcion`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_opcionXrol_rol1` FOREIGN KEY (`idRol`) REFERENCES `rol` (`idRol`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "opcionrol"
#


#
# Structure for table "tipogato"
#

DROP TABLE IF EXISTS `tipogato`;
CREATE TABLE `tipogato` (
  `idtipogato` int(11) NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `puntaje` int(45) DEFAULT NULL,
  PRIMARY KEY (`idtipogato`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "tipogato"
#

INSERT INTO `tipogato` VALUES (1,'normal',10),(2,'inmortal',50);

#
# Structure for table "usuario"
#

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `idusuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `genero` varchar(45) DEFAULT NULL,
  `correo` varchar(45) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT CURRENT_TIMESTAMP,
  `fecha_ultimo_ingreso` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `clave` varchar(45) DEFAULT NULL,
  `alias` varchar(45) DEFAULT NULL,
  `identificacion` int(11) DEFAULT NULL,
  PRIMARY KEY (`idusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "usuario"
#


#
# Structure for table "rolusuario"
#

DROP TABLE IF EXISTS `rolusuario`;
CREATE TABLE `rolusuario` (
  `idRol` int(11) NOT NULL,
  `idusuario` int(11) NOT NULL,
  PRIMARY KEY (`idRol`,`idusuario`),
  KEY `fk_rolXusuario_usuario1_idx` (`idusuario`),
  CONSTRAINT `fk_rolXusuario_rol1` FOREIGN KEY (`idRol`) REFERENCES `rol` (`idRol`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_rolXusuario_usuario1` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "rolusuario"
#


#
# Structure for table "mensaje"
#

DROP TABLE IF EXISTS `mensaje`;
CREATE TABLE `mensaje` (
  `idmensaje` int(11) NOT NULL,
  `idusuario_origen` int(11) NOT NULL,
  `idusuario_destino` int(11) NOT NULL,
  `mensaje` varchar(255) NOT NULL,
  PRIMARY KEY (`idmensaje`),
  KEY `fk_mensaje_usuario1_idx` (`idusuario_origen`),
  KEY `fk_mensaje_usuario2_idx` (`idusuario_destino`),
  CONSTRAINT `fk_mensaje_usuario1` FOREIGN KEY (`idusuario_origen`) REFERENCES `usuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_mensaje_usuario2` FOREIGN KEY (`idusuario_destino`) REFERENCES `usuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "mensaje"
#


#
# Structure for table "curso"
#

DROP TABLE IF EXISTS `curso`;
CREATE TABLE `curso` (
  `idcurso` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(65) NOT NULL,
  `descripcion` varchar(300) DEFAULT NULL,
  `activo` int(11) DEFAULT NULL,
  `estado` varchar(11) DEFAULT NULL,
  `idprofesor` int(11) NOT NULL,
  `limite_actividades` int(11) DEFAULT NULL,
  PRIMARY KEY (`idcurso`),
  KEY `fk_curso_usuario1_idx` (`idprofesor`),
  CONSTRAINT `fk_curso_usuario1` FOREIGN KEY (`idprofesor`) REFERENCES `usuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "curso"
#


#
# Structure for table "estudiantecurso"
#

DROP TABLE IF EXISTS `estudiantecurso`;
CREATE TABLE `estudiantecurso` (
  `idcurso` int(11) NOT NULL,
  `idusuario` int(11) NOT NULL,
  `puntaje` int(11) DEFAULT NULL COMMENT 'puntaje obtenido por un jugador en un curso.',
  PRIMARY KEY (`idcurso`,`idusuario`),
  KEY `fk_curso_estudiante_usuario1_idx` (`idusuario`),
  CONSTRAINT `fk_curso_estudiante_curso` FOREIGN KEY (`idcurso`) REFERENCES `curso` (`idcurso`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_curso_estudiante_usuario1` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "estudiantecurso"
#


#
# Structure for table "actividad"
#

DROP TABLE IF EXISTS `actividad`;
CREATE TABLE `actividad` (
  `idactividad` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `idtipo_juego` int(11) NOT NULL,
  `activo` int(11) DEFAULT NULL COMMENT 'estado activo o inactivo',
  `publicado` int(11) DEFAULT NULL,
  `limite_movimientos` int(11) DEFAULT NULL,
  `enunciado` varchar(45) NOT NULL,
  `url_texto_ensenanza` varchar(255) DEFAULT NULL,
  `idprofesor` int(11) NOT NULL,
  PRIMARY KEY (`idactividad`),
  KEY `fk_actividad_tipo_actividad1_idx` (`idtipo_juego`),
  KEY `fk_actividad_usuario1_idx` (`idprofesor`),
  CONSTRAINT `fk_actividad_tipo_actividad1` FOREIGN KEY (`idtipo_juego`) REFERENCES `juego` (`idjuego`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_actividad_usuario1` FOREIGN KEY (`idprofesor`) REFERENCES `usuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "actividad"
#


#
# Structure for table "respuesta"
#

DROP TABLE IF EXISTS `respuesta`;
CREATE TABLE `respuesta` (
  `idrespuesta` int(11) NOT NULL,
  `idactividad` int(11) NOT NULL,
  `valor` varchar(45) DEFAULT NULL COMMENT 'opcion de respuesta, algunas no seran verdaderas. Corresponde a las opciones que el usuario tiene para seleccionar la respuesta correcta.',
  `verdadero` tinyint(1) DEFAULT NULL COMMENT 'identifica si esta es la respuesta correcta',
  `orden` int(11) DEFAULT NULL,
  PRIMARY KEY (`idrespuesta`),
  KEY `fk_respuestas_actividad1_idx` (`idactividad`),
  CONSTRAINT `fk_respuestas_actividad1` FOREIGN KEY (`idactividad`) REFERENCES `actividad` (`idactividad`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "respuesta"
#


#
# Structure for table "movimientoactividad"
#

DROP TABLE IF EXISTS `movimientoactividad`;
CREATE TABLE `movimientoactividad` (
  `actividad_idactividad` int(11) NOT NULL,
  `movimiento_idmovimientos` int(11) NOT NULL,
  PRIMARY KEY (`actividad_idactividad`,`movimiento_idmovimientos`),
  KEY `fk_movimientoactividad_movimiento1_idx` (`movimiento_idmovimientos`),
  CONSTRAINT `fk_movimientoactividad_actividad1` FOREIGN KEY (`actividad_idactividad`) REFERENCES `actividad` (`idactividad`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_movimientoactividad_movimiento1` FOREIGN KEY (`movimiento_idmovimientos`) REFERENCES `movimiento` (`idmovimientos`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "movimientoactividad"
#


#
# Structure for table "gato"
#

DROP TABLE IF EXISTS `gato`;
CREATE TABLE `gato` (
  `idgato` int(11) NOT NULL,
  `idtipo_gato` int(11) NOT NULL,
  `orden` int(11) NOT NULL,
  `idcolor` int(11) NOT NULL,
  `idarma` int(11) NOT NULL,
  `idactividad` int(11) NOT NULL,
  `idarmadura` int(11) NOT NULL,
  PRIMARY KEY (`idgato`),
  KEY `fk_gato_tipo_gato1_idx` (`idtipo_gato`),
  KEY `fk_gato_color1_idx` (`idcolor`),
  KEY `fk_gato_arma1_idx` (`idarma`),
  KEY `fk_gato_actividad1_idx` (`idactividad`),
  KEY `fk_gato_armadura1_idx` (`idarmadura`),
  CONSTRAINT `fk_gato_actividad1` FOREIGN KEY (`idactividad`) REFERENCES `actividad` (`idactividad`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_gato_arma1` FOREIGN KEY (`idarma`) REFERENCES `arma` (`idarma`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_gato_armadura1` FOREIGN KEY (`idarmadura`) REFERENCES `armadura` (`idarmadura`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_gato_color1` FOREIGN KEY (`idcolor`) REFERENCES `color` (`idcolor`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_gato_tipo_gato1` FOREIGN KEY (`idtipo_gato`) REFERENCES `tipogato` (`idtipogato`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "gato"
#


#
# Structure for table "estructuras_activas"
#

DROP TABLE IF EXISTS `estructuras_activas`;
CREATE TABLE `estructuras_activas` (
  `idestructura` int(11) NOT NULL,
  `idactividad` int(11) NOT NULL,
  PRIMARY KEY (`idactividad`,`idestructura`),
  KEY `fk_actividadjuego_estructura_estructura1_idx` (`idestructura`),
  CONSTRAINT `fk_actividad_estructura_actividad1` FOREIGN KEY (`idactividad`) REFERENCES `actividad` (`idactividad`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_actividadjuego_estructura_estructura1` FOREIGN KEY (`idestructura`) REFERENCES `estructura` (`idestructura`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "estructuras_activas"
#


#
# Structure for table "actividadusuario"
#

DROP TABLE IF EXISTS `actividadusuario`;
CREATE TABLE `actividadusuario` (
  `usuario_idusuario` int(11) NOT NULL,
  `actividad_idactividad` int(11) NOT NULL,
  `raton_idraton` int(11) NOT NULL,
  `numero_intento` int(11) DEFAULT NULL,
  `Fecha` date NOT NULL,
  `Puntos` int(11) DEFAULT NULL,
  `num_movimientos` int(11) DEFAULT NULL,
  PRIMARY KEY (`usuario_idusuario`,`actividad_idactividad`,`raton_idraton`),
  KEY `fk_usuario_actividad_usuario1_idx` (`usuario_idusuario`),
  KEY `fk_usuario_actividad_actividad1_idx` (`actividad_idactividad`),
  CONSTRAINT `fk_usuario_actividad_actividad1` FOREIGN KEY (`actividad_idactividad`) REFERENCES `actividad` (`idactividad`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario_actividad_usuario1` FOREIGN KEY (`usuario_idusuario`) REFERENCES `usuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "actividadusuario"
#


#
# Structure for table "actividadcurso"
#

DROP TABLE IF EXISTS `actividadcurso`;
CREATE TABLE `actividadcurso` (
  `idcurso` int(11) NOT NULL,
  `posicion_actividad` int(11) NOT NULL COMMENT 'posicion dentro de la secuencia de actividades que deben aparecer en un curso.\n',
  `idactividad` int(11) NOT NULL,
  PRIMARY KEY (`idcurso`,`idactividad`),
  KEY `fk_curso_actividad_actividad1_idx` (`idactividad`),
  CONSTRAINT `fk_curso_actividad_actividad1` FOREIGN KEY (`idactividad`) REFERENCES `actividad` (`idactividad`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_curso_actividad_curso1` FOREIGN KEY (`idcurso`) REFERENCES `curso` (`idcurso`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "actividadcurso"
#

