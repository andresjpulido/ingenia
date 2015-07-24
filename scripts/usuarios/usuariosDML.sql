use ingenia;
INSERT INTO `opcion` VALUES (1,'Administracion de usuarios','Seccion que permite crear, modificar o eliminar de usuarios','OP001'),(2,'Administracion de Roles','Seccion que permite crear, modificar o eliminar de roles','OP002'),(3,'Administracion de cursos','Seccion que permite la creacion, modificacion o eliminacion de cursos.','OP003'),(4,'Administracion de Actividades','Seccion que permite la creacion, modificacion o eliminacion de actividades.','OP004'),(5,'Mis cursos','Lista de los cursos en los que se encuentra un estudiante.','OP005'),(6,'Inscripcion a cursos','Pagina para que un estudiante pueda registrarse en un curso','OP006'),(7,'log','log','OP007');
INSERT INTO `rol` VALUES (1,'Administrador del sistema','Administrador','S'),(2,'Profesor','Gestionar Cursos','S'),(3,'Estudiante','Desarrollar Actividades','S');
INSERT INTO `opcionrol` VALUES (1,1),(2,1),(7,1),(5,3),(6,3),(3,2),(4,2);
INSERT INTO `usuario` 
VALUES (1,'Albeiro','Guallon','M','test@udistrital.edu.co','2015-06-30 07:50:09','2015-06-30 07:50:09','password','Albeiro',234652,1),
(2,'franky','Montero','M','test@udistrital.edu.co','2015-06-30 07:50:09','2015-06-30 07:50:09','password','Franky',875678,1),
(3,'sergio','Bayona','M','test@udistrital.edu.co','2015-06-30 07:50:09','2015-06-30 07:50:09','password','Sergio',45634,1),
(4,'jaime','Baez','M','test@udistrital.edu.co','2015-06-30 07:50:09','2015-06-30 07:50:09','password','Jaime',129876,1),
(5,'johann','Briñez','M','test@udistrital.edu.co','2015-06-30 07:50:09','2015-06-30 07:50:09','password','Johann',325787,1),
(6,'Manuel','Castro','M','test@udistrital.edu.co','2015-06-30 07:50:09','2015-06-30 07:50:09','password','Manuel',763422,1),
(7,'Andres','Pulido','M','test@udistrital.edu.co','2015-06-30 07:50:09','2015-06-30 07:50:09','password','Andres',4576989,1),
(8,'Alejandro','Parra','M','test@udistrital.edu.co','2015-06-30 07:50:09','2015-06-30 07:50:09','password','Alejandro',234976,1);
INSERT INTO `rolusuario` VALUES (1,1),(2,2),(1,3),(1,4),(1,5),(1,6),(1,7);

/*crear estudiante */
INSERT INTO `ingenia`.`usuario`
(`idusuario`,`nombre`,`apellido`,`genero`,`correo`,`fecha_creacion`,`fecha_ultimo_ingreso`,`clave`,`alias`,`activo`)
VALUES(9,"nombre estudiante 1","apellido estudiante 1","M","estudiante@udistrital.edu.co",now(),now(),"password","estudiante1",1);
INSERT INTO `ingenia`.`usuario`
(`idusuario`,`nombre`,`apellido`,`genero`,`correo`,`fecha_creacion`,`fecha_ultimo_ingreso`,`clave`,`alias`,`activo`)
VALUES(10,"nombre estudiante 2","apellido estudiante 2","M","estudiante2@udistrital.edu.co",now(),now(),"password","estudiante2",1);

select * from rol;
INSERT INTO `ingenia`.`rolusuario`(`idRol`,`idusuario`)
VALUES(3,9);
INSERT INTO `ingenia`.`rolusuario`(`idRol`,`idusuario`)
VALUES(3,10);