/* TABLAS EN MYSQL */

SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */
DROP TABLE IF EXISTS RESERVAS;
DROP TABLE IF EXISTS VIAJES;
DROP TABLE IF EXISTS CIUDADES;
DROP TABLE IF EXISTS CLIENTES;
DROP TABLE IF EXISTS PAISES;


/* Create Tables */

CREATE TABLE CIUDADES
(
	CIUDAD varchar(20) not null,
	CODPAIS char(3),
	CONSTRAINT CIUDADESPK16 PRIMARY KEY (CIUDAD)
);

CREATE TABLE CLIENTES
(
	CODCLIEN int NOT NULL,
	NOMBRE varchar(25),
	DIRECCION varchar(25),
	TLF varchar(10),
	POBLACION varchar(25),
	CONSTRAINT CLIENTESPK16 PRIMARY KEY (CODCLIEN)
);

CREATE TABLE PAISES
(
	CODPAIS char(3) NOT NULL,
	NOMBRE varchar(25),
	IDIOMA varchar(15),
	TASA float,
	CONSTRAINT PAISESPK16 PRIMARY KEY (CODPAIS)
);

CREATE TABLE RESERVAS
(
	CODRESERVA int NOT NULL,
	NUMPLAZA int ,
	FECHARESERVA date,
	CODCLIEN int,
	CODVIAJE int,
	CONSTRAINT RESERVASPK16 PRIMARY KEY (CODRESERVA)
);

CREATE TABLE VIAJES
(
	CODVIAJE int NOT NULL,
	FECHAINICIO date,
	FECHAFIN date,
	PLAZASOFERTADAS int,
	PLAZASOCUPADAS int,
	DESCRIPCION varchar(50),
	CIUDADORIGEN varchar(20),
	CIUDADDESTINO varchar(20),
	PRECIO float,
	CONSTRAINT VIAJESPK16 PRIMARY KEY (CODVIAJE)
);


/* Create Foreign Keys */

ALTER TABLE viajes
	ADD CONSTRAINT DESTINOviajes16 FOREIGN KEY (CIUDADDESTINO)
	REFERENCES CIUDADES (CIUDAD)
	ON UPDATE CASCADE
	ON DELETE RESTRICT
;


ALTER TABLE viajes
	ADD CONSTRAINT ORIGENviajes16 FOREIGN KEY (CIUDADORIGEN)
	REFERENCES CIUDADES (CIUDAD)
	ON UPDATE CASCADE
	ON DELETE RESTRICT
;


ALTER TABLE RESERVAS
	ADD CONSTRAINT REALIZANRESERV16 FOREIGN KEY (CODCLIEN)
	REFERENCES CLIENTES (CODCLIEN)
	ON UPDATE CASCADE
	ON DELETE RESTRICT
;

ALTER TABLE RESERVAS
	ADD CONSTRAINT VIAJERESERVA16 FOREIGN KEY (CODVIAJE)
	REFERENCES VIAJES (CODVIAJE)
	ON UPDATE CASCADE
	ON DELETE RESTRICT
;

ALTER TABLE CIUDADES
	ADD CONSTRAINT PAISCIUDAD16 FOREIGN KEY (CODPAIS)
	REFERENCES PAISES (CODPAIS)
	ON UPDATE CASCADE
	ON DELETE RESTRICT
;

/*  insert PAISES */

 INSERT INTO PAISES VALUES ('DEU', 'Alemania', 'Alemán', 170);
 INSERT INTO PAISES VALUES ('AUS', 'Australia', 'Inglés', 200);
 INSERT INTO PAISES VALUES ('ARG', 'Argentina', 'Español', 150);
 INSERT INTO PAISES VALUES ('FRA', 'Francia', 'Francés', 200);
INSERT INTO PAISES VALUES ('ITA', 'Italia', 'Italiano', 130);
 INSERT INTO PAISES VALUES ('ESP', 'España', 'Español', 30);
 INSERT INTO PAISES VALUES ('NLD', 'Paises Bajos', 'Neerlandés', 350);
 INSERT INTO PAISES VALUES ('GBR', 'Reino Unido', 'Inglés', 210);
	 
/*  insert CIUDADES   */
	
Insert into ciudades values( 'MUNICH','DEU');
Insert into ciudades values( 'BERLIN','DEU');
Insert into ciudades values( 'FRANKFURT','DEU');
Insert into ciudades values( 'HAMBURGO','DEU');
	
Insert into ciudades values( 'ADELAIDA','AUS');
Insert into ciudades values( 'SIDNEY','AUS');
Insert into ciudades values( 'BRISBANE','AUS');

Insert into ciudades values( 'PARIS','FRA');
Insert into ciudades values( 'RENNES','FRA');
Insert into ciudades values( 'LYON','FRA');
Insert into ciudades values( 'MARSELLA','FRA');
	
Insert into ciudades values('ROMA','ITA');
Insert into ciudades values('FLORENCIA','ITA');
Insert into ciudades values( 'VENECIA','ITA');
Insert into ciudades values( 'NÁPOLES','ITA');
	
Insert into ciudades values( 'MADRID','ESP');
Insert into ciudades values( 'BARCELONA','ESP');
Insert into ciudades values('BILBAO','ESP');
	  
Insert into ciudades values( 'AMSTERDAM','NLD');
Insert into ciudades values( 'ROTTERDAM','NLD');
Insert into ciudades values( 'GRONINGEN','NLD');
	
Insert into ciudades values( 'LONDRES','GBR');
Insert into ciudades values( 'LIVERPOOL','GBR');
Insert into ciudades values( 'GLASGOW','GBR');
Insert into ciudades values('OXFORD','GBR');
	 
/*  insert CLIENTES  */

 insert into clientes values (1, 'AG. Alicia Gil', 'C/Prado 25', '925453535', 'Talavera de la Reina');
 insert into clientes values (2, 'Transportes Gómez', 'Polígono la Floresta', '925009988', 'Talavera de la Reina');
 insert into clientes values (3, 'Limpiezas brillante', 'C/La Alameda 22', '915009900', 'Madrid');
 insert into clientes values (4, 'Pedro Pérez', 'C/Martillo 10', '919191919', 'Madrid');
  insert into clientes values(5, 'Ana Maryín', 'C/Alameda 3', '925450035', 'Talavera de la Reina');
 insert into clientes values (6, 'Viajes María Rato Melz', 'C/Chicharrón 22', '923456588', 'Alcalá de Henares');
 insert into clientes values (7, 'Escuela Padel', 'Polígono ceniceros', '912332900', 'Madrid');
 insert into clientes values (8, 'Rosaura Memez', 'C/Televisiones 5', '9100998877', 'Madrid');


 
/*  insert VIAJES  */
/* sumar fechas  ADDDATE(ADDDATE(ADDDATE(curdate(),,, 31);
 restar  ADDDATE(ADDDATE(curdate(),, -31) */
 /*
	CREATE TABLE VIAJES
(
	CODVIAJE int NOT NULL,	FECHAINICIO date,	FECHAFIN date,
	PLAZASOFERTADAS int,	PLAZASOCUPADAS int,
	DESCRIPCION varchar(30),	CIUDADORIGEN varchar(20),
	CIUDADDESTINO varchar(20),	PRECIO float,
	CONSTRAINT VIAJESPK PRIMARY KEY (CODVIAJE)
);
*/
Insert into VIAJES values(1,ADDDATE(curdate(),+10),ADDDATE(curdate(),+20),30,3, 
'La selva Negra', 'MADRID', 'MUNICH', 70.5 );


Insert into VIAJES values(2,ADDDATE(curdate(),+15),ADDDATE(curdate(),+25),40,4, 
'Valles del Rhin', 'BARCELONA', 'FRANKFURT', 150.5 );

Insert into VIAJES values(3,ADDDATE(curdate(),+5),ADDDATE(curdate(),+13),50,5, 
'Visitas Románticas', 'MADRID', 'FLORENCIA', 80 );

Insert into VIAJES values(4,ADDDATE(curdate(),+15),ADDDATE(curdate(),+20),55,0, 
'Por los canales románticos', 'BILBAO', 'VENECIA', 80.5 );

Insert into VIAJES values(5,ADDDATE(curdate(),+10),ADDDATE(curdate(),+18),20,0, 
'Universidad 1', 'BILBAO', 'OXFORD', 55.5 );

Insert into VIAJES values(6,ADDDATE(curdate(),+25),ADDDATE(curdate(),+30),30,0, 
'Ciudad de la música', 'BARCELONA', 'LIVERPOOL', 85.5 );

Insert into VIAJES values(7,ADDDATE(curdate(),+23),ADDDATE(curdate(),+30),60,0, 
'De Baviera a la ciudad de la música', 'MUNICH', 'LIVERPOOL', 95.5 );

Insert into VIAJES values(8,ADDDATE(curdate(),+12),ADDDATE(curdate(),+17),52,3, 
'Ciudad Imperial-Tulipanes', 'ROMA', 'AMSTERDAM', 55.5 );

Insert into VIAJES values(9,ADDDATE(curdate(),+12),ADDDATE(curdate(),+22),45,6, 
'Castillos imperiales', 'MUNICH', 'PARIS', 155.5 );

Insert into VIAJES values(10,ADDDATE(curdate(),+11),ADDDATE(curdate(),+32),35,4, 
'A las antípodas', 'MADRID', 'SIDNEY', 200 );


/*  insert RESERVAS  */
/*

	 CODRESERVA int NOT NULL,
	NUMPLAZA int ,
	FECHARESERVA date,
	CODCLIEN int, 1 a 8
	CODVIAJE int, 1 a 10
*/

/*viaje 1 */
Insert into RESERVAS values(100,1,  ADDDATE(curdate(),-10), 1, 1);
Insert into RESERVAS values(101,2,  ADDDATE(curdate(),-10), 2, 1);
Insert into RESERVAS values(102,10,  ADDDATE(curdate(),-8), 3, 1);

/*viaje 2 */
Insert into RESERVAS values(200,  1,ADDDATE(curdate(),-12), 5, 2);
Insert into RESERVAS values(201,  2,ADDDATE(curdate(),-11), 6, 2);
Insert into RESERVAS values(202,  11,ADDDATE(curdate(),-9), 7, 2);
Insert into RESERVAS values(203,  12,ADDDATE(curdate(),-8), 8, 2);

/*viaje 3 */
Insert into RESERVAS values(300,  1,ADDDATE(curdate(),-30), 1, 3);
Insert into RESERVAS values(301,  2,ADDDATE(curdate(),-27), 2, 3);
Insert into RESERVAS values(302,  11,ADDDATE(curdate(),-20), 5, 3);
Insert into RESERVAS values(303,  12,ADDDATE(curdate(),-17), 7, 3);
Insert into RESERVAS values(304,  15,ADDDATE(curdate(),-7), 8, 3);

/*viaje 8 */
Insert into RESERVAS values(800,  1,ADDDATE(curdate(),-12), 3, 8);
Insert into RESERVAS values(801,  2,ADDDATE(curdate(),-10), 4, 8);
Insert into RESERVAS values(802,  4,ADDDATE(curdate(),-5), 8,  8);


/*viaje 9 */
Insert into RESERVAS values(900,  1,ADDDATE(curdate(),-32), 4, 9);
Insert into RESERVAS values(901,  2,ADDDATE(curdate(),-30), 4, 9);
Insert into RESERVAS values(902,  4,ADDDATE(curdate(),-25), 6, 9);
Insert into RESERVAS values(903,  5,ADDDATE(curdate(),-24), 7, 9);
Insert into RESERVAS values(904,  15,ADDDATE(curdate(),-22), 8, 9);
Insert into RESERVAS values(905,  16,ADDDATE(curdate(),-22), 8, 9);

/*viaje 10 */
Insert into RESERVAS values(1000,  1,ADDDATE(curdate(),-22), 5, 10);
Insert into RESERVAS values(1001,  2,ADDDATE(curdate(),-20), 5, 10);
Insert into RESERVAS values(1002,  12,ADDDATE(curdate(),-15), 6, 10);
Insert into RESERVAS values(1003,  13,ADDDATE(curdate(),-14), 7, 10);

commit;



