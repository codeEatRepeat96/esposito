CREATE DATABASE IF NOT EXISTS visit;

USE visit;

CREATE TABLE IF NOT EXISTS visit (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  date DATE NOT NULL,
  time TIME NOT NULL,
  TYPE ENUM('HOME', 'DOCTOR_OFFICE') NOT NULL,
  reason ENUM('FIRST_VISIT', 'RECURRING_VISIT', 'URGENT') NOT NULL,
  family_history TEXT,
  patient_ssn CHAR(9) NOT NULL,
  UNIQUE (date, time, patient_ssn)
);

INSERT INTO visit (date, time, TYPE, reason, family_history, patient_ssn)
VALUES ('2022-03-18', '10:00:00', 'DOCTOR_OFFICE', 'FIRST_VISIT', NULL, '123456789');

INSERT INTO visit (date, time, TYPE, reason, family_history, patient_ssn)
VALUES ('2022-03-15', '14:30:00', 'DOCTOR_OFFICE', 'RECURRING_VISIT', NULL, '987654321');

INSERT INTO visit (date, time, TYPE, reason, family_history, patient_ssn)
VALUES ('2022-03-20', '09:00:00', 'HOME', 'URGENT', 'Mother with diabete', '123456789');

INSERT INTO visit (date, time, TYPE, reason, family_history, patient_ssn)
VALUES ('2022-03-31', '11:30:00', 'HOME', 'FIRST_VISIT', 'Father with glaucoma', '987654321');