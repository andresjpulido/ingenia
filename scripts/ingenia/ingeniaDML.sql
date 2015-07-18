use ingenia;

INSERT INTO `arma` VALUES (1,'espada',10),(2,'hacha',10),(3,'daga',10),(4,'mano',10);
INSERT INTO `armadura` VALUES (1,'tela',10),(2,'cuero',10),(3,'malla',10);
INSERT INTO `color` VALUES (1,'azul',10),(2,'verde',10),(3,'rojo',10);
INSERT INTO `estructura` VALUES (1,'if'),(2,'while'),(3,'for'),(4,'switch'),(5,'or'),(6,'and'),(7,'mayor que'),(8,'igual que '),(9,'menor que'),(10,'diferente que');
INSERT INTO `juego` VALUES (1,'gato','catcraft');
INSERT INTO `movimiento` VALUES (1,'a'),(2,'b'),(3,'c'),(4,'d');
INSERT INTO `tipogato` VALUES (1,'normal',10),(2,'inmortal',50);

INSERT INTO `ingenia`.`curso` (`idcurso`,`nombre`,`descripcion`,`activo`,`estado`,`idprofesor`,`limite_actividades`)
VALUES (1,'Curso basico 1','nivel basico 1',1,null,2,2);
INSERT INTO `ingenia`.`curso` (`idcurso`,`nombre`,`descripcion`,`activo`,`estado`,`idprofesor`,`limite_actividades`)
VALUES (2,'Curso basico 2','nivel basico 2',1,null,2,2);
INSERT INTO `ingenia`.`curso` (`idcurso`,`nombre`,`descripcion`,`activo`,`estado`,`idprofesor`,`limite_actividades`)
VALUES (3,'Curso basico 3','nivel basico 3',1,null,2,2);
INSERT INTO `ingenia`.`curso` (`idcurso`,`nombre`,`descripcion`,`activo`,`estado`,`idprofesor`,`limite_actividades`)
VALUES (4,'Curso medio 1','nivel medio 1',1,null,2,2);
INSERT INTO `ingenia`.`curso` (`idcurso`,`nombre`,`descripcion`,`activo`,`estado`,`idprofesor`,`limite_actividades`)
VALUES (5,'Curso medio 2','nivel medio 2',1,null,2,2);
INSERT INTO `ingenia`.`curso` (`idcurso`,`nombre`,`descripcion`,`activo`,`estado`,`idprofesor`,`limite_actividades`)
VALUES (6,'Curso avanzado 1','nivel avanzado 1',1,null,2,2);
INSERT INTO `ingenia`.`curso` (`idcurso`,`nombre`,`descripcion`,`activo`,`estado`,`idprofesor`,`limite_actividades`)
VALUES (7,'Curso avanzado 2','nivel avanzado 2',1,null,2,2);