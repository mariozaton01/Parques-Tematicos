-- PUBLIC.CLIENTES definition

-- Drop table

-- DROP TABLE PUBLIC.CLIENTES;

CREATE TABLE PUBLIC.CLIENTES (
	ID INTEGER NOT NULL AUTO_INCREMENT,
	NOMBRE CHARACTER VARYING(255) NOT NULL,
	APELLIDO CHARACTER VARYING(255) NOT NULL,
	DNI CHARACTER VARYING(255) NOT NULL,
	EDAD INTEGER NOT NULL,
	CONSTRAINT CONSTRAINT_6 PRIMARY KEY (ID)
);
CREATE UNIQUE INDEX PRIMARY_KEY_6 ON PUBLIC.CLIENTES (ID);


-- PUBLIC.EMPLEADOS definition

-- Drop table

-- DROP TABLE PUBLIC.EMPLEADOS;

CREATE TABLE PUBLIC.EMPLEADOS (
	ID INTEGER NOT NULL AUTO_INCREMENT,
	NOMBRE CHARACTER VARYING(255) NOT NULL,
	APELLIDO CHARACTER VARYING(255) NOT NULL,
	DNI CHARACTER VARYING(255) NOT NULL,
	EDAD CHARACTER VARYING(255) NOT NULL,
	FECHA_NAC CHARACTER VARYING(255) NOT NULL,
	FECHA_CONTRATO CHARACTER VARYING(255) NOT NULL,
	NACIONALIDAD CHARACTER VARYING(255) NOT NULL,
	CARGO INTEGER NOT NULL,
	CONSTRAINT CONSTRAINT_4 PRIMARY KEY (ID)
);
CREATE UNIQUE INDEX PRIMARY_KEY_4 ON PUBLIC.EMPLEADOS (ID);


-- PUBLIC.PARQUE definition

-- Drop table

-- DROP TABLE PUBLIC.PARQUE;

CREATE TABLE PUBLIC.PARQUE (
	ID INTEGER NOT NULL AUTO_INCREMENT,
	NOMBRE CHARACTER VARYING(255) NOT NULL,
	APERTURA CHARACTER VARYING(255) NOT NULL,
	DIRECCION CHARACTER VARYING(255) NOT NULL,
	CONSTRAINT CONSTRAINT_8 PRIMARY KEY (ID)
);
CREATE UNIQUE INDEX PRIMARY_KEY_8 ON PUBLIC.PARQUE (ID);


-- PUBLIC.ESPECTACULOS definition

-- Drop table

-- DROP TABLE PUBLIC.ESPECTACULOS;

CREATE TABLE PUBLIC.ESPECTACULOS (
	ID INTEGER NOT NULL AUTO_INCREMENT,
	NOMBRE CHARACTER VARYING(255) NOT NULL,
	AFORO INTEGER NOT NULL,
	DESCRIPCION CHARACTER VARYING(255) NOT NULL,
	LUGAR CHARACTER VARYING(255) NOT NULL,
	COSTE DOUBLE PRECISION NOT NULL,
	EMPLEADO_CARGO INTEGER DEFAULT NULL,
	CONSTRAINT CONSTRAINT_A PRIMARY KEY (ID),
	CONSTRAINT ESPECTACULOS_IBFK_1 FOREIGN KEY (EMPLEADO_CARGO) REFERENCES PUBLIC.EMPLEADOS(ID) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE INDEX ESPECTACULOS_IBFK_1_INDEX_A ON PUBLIC.ESPECTACULOS (EMPLEADO_CARGO);
CREATE UNIQUE INDEX PRIMARY_KEY_A ON PUBLIC.ESPECTACULOS (ID);


-- PUBLIC.ESPECTACULO_CLIENTES definition

-- Drop table

-- DROP TABLE PUBLIC.ESPECTACULO_CLIENTES;

CREATE TABLE PUBLIC.ESPECTACULO_CLIENTES (
	ID INTEGER NOT NULL AUTO_INCREMENT,
	ID_ESPECTACULO INTEGER NOT NULL,
	ID_CLIENTE INTEGER NOT NULL,
	CONSTRAINT CONSTRAINT_2 PRIMARY KEY (ID),
	CONSTRAINT ESPECTACULO_CLIENTES_IBFK_1 FOREIGN KEY (ID_ESPECTACULO) REFERENCES PUBLIC.ESPECTACULOS(ID) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT ESPECTACULO_CLIENTES_IBFK_2 FOREIGN KEY (ID_CLIENTE) REFERENCES PUBLIC.CLIENTES(ID) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE INDEX ESPECTACULO_CLIENTES_IBFK_1_INDEX_2 ON PUBLIC.ESPECTACULO_CLIENTES (ID_ESPECTACULO);
CREATE INDEX ESPECTACULO_CLIENTES_IBFK_2_INDEX_2 ON PUBLIC.ESPECTACULO_CLIENTES (ID_CLIENTE);
CREATE UNIQUE INDEX PRIMARY_KEY_2 ON PUBLIC.ESPECTACULO_CLIENTES (ID);