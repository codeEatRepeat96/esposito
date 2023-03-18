CREATE DATABASE IF NOT EXISTS patient;

USE patient;

CREATE TABLE IF NOT EXISTS patient (
  ssn CHAR(9) NOT NULL PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  surname VARCHAR(50) NOT NULL,
  date_of_birth DATE NOT NULL
);

INSERT INTO patient (ssn, name, surname, date_of_birth)
VALUES ('123456789', 'Giacomo', 'Poretti', '1980-01-01');
INSERT INTO patient (ssn, name, surname, date_of_birth)
VALUES ('987654321', 'Cataldo', 'Baglio', '1970-10-07');