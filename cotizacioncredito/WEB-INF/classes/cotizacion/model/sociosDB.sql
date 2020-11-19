DROP DATABASE IF EXISTS socios;
CREATE DATABASE socios;
CREATE TABLE socios.datos (
    socioID int NOT NULL AUTO_INCREMENT,
    nombre varchar(50) NOT NULL,
    tasaDeInteres float NOT NULL,
    montoMaxDisp float NOT NULL,
    PRIMARY KEY (socioID)
);
