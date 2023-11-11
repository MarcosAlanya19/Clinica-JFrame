-- Tabla de historial medico
CREATE TABLE MedicalHistory (
  id INT AUTO_INCREMENT NOT NULL,
  bloodType ENUM('A', 'B', 'AB', 'O') NOT NULL,
  age INT NOT NULL,
  description TEXT NOT NULL,
  PRIMARY KEY (id)
);

-- Tabla de usuario
CREATE TABLE User (
  id INT AUTO_INCREMENT NOT NULL,
  name VARCHAR(45) NOT NULL,
  email VARCHAR(255) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  rol ENUM('Administrador', 'Medico') NOT NULL,
  PRIMARY KEY (id)
);

-- Tabla de paciente
CREATE TABLE Patient (
  id INT AUTO_INCREMENT NOT NULL,
  name VARCHAR(45) NOT NULL,
  dateOfBirth DATE NOT NULL,
  address VARCHAR(100) NOT NULL,
  phone VARCHAR(20) NOT NULL,
  gender ENUM('Masculino', 'Femenino') NOT NULL,
  dni VARCHAR(20) NOT NULL UNIQUE,
  MedicalHistory_id INT NOT NULL,
  User_id INT NOT NULL,
  PRIMARY KEY (id),
  KEY fk_Patient_MedicalHistory1 (MedicalHistory_id),
  KEY fk_Patient_User1 (User_id)
);

-- Tabla de medico
CREATE TABLE Doctor (
  id INT AUTO_INCREMENT NOT NULL,
  name VARCHAR(45) NOT NULL,
  phone VARCHAR(20) NOT NULL,
  email VARCHAR(100) NOT NULL,
  address VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);

-- Tabla de cita
CREATE TABLE Appointment (
  id INT AUTO_INCREMENT NOT NULL,
  date DATETIME NULL,
  description TEXT NULL,
  status ENUM('Programado', 'Cancelado', 'Terminado') NULL,
  Doctor_id INT NOT NULL,
  Patient_id INT NOT NULL,
  PRIMARY KEY (id),
  KEY fk_Appointment_Doctor1 (Doctor_id),
  KEY fk_Appointment_Patient1 (Patient_id)
);

-- Tabla de seguro de vida
CREATE TABLE HealthInsurance (
  id INT AUTO_INCREMENT NOT NULL,
  policyNumber VARCHAR(45) NOT NULL,
  insuranceCompany VARCHAR(100) NOT NULL,
  coverageDetails VARCHAR(100) NOT NULL,
  Patient_id INT NOT NULL,
  PRIMARY KEY (id),
  KEY fk_HealthInsurance_Patient1 (Patient_id)
);

-- Tabla de factura
CREATE TABLE Invoice (
  id INT AUTO_INCREMENT NOT NULL,
  issueDate DATE NULL,
  total DECIMAL(10, 2) NOT NULL,
  legalName VARCHAR(100) NOT NULL,
  taxID VARCHAR(20) NOT NULL,
  details TEXT NOT NULL,
  Patient_id INT NOT NULL,
  PRIMARY KEY (id),
  KEY fk_Invoice_Patient1 (Patient_id)
);

-- Tabla de recurso
CREATE TABLE Resource (
  id INT AUTO_INCREMENT NOT NULL,
  name VARCHAR(45) NOT NULL,
  status ENUM('Disponible', 'En uso', 'Mantenimiento') NOT NULL,
  availability ENUM('Mañana', 'Tarde', 'Noche') NOT NULL,
  Doctor_id INT NOT NULL,
  PRIMARY KEY (id),
  KEY fk_Resource_Doctor1 (Doctor_id)
);

-- Tabla de horario de cita
CREATE TABLE AppointmentSchedule (
  id INT AUTO_INCREMENT NOT NULL,
  startTime TIME NOT NULL,
  endTime TIME NOT NULL,
  day ENUM('Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado', 'Domingo') NOT NULL,
  Doctor_id INT NOT NULL,
  PRIMARY KEY (id),
  KEY fk_AppointmentSchedule_Doctor1 (Doctor_id)
);

ALTER TABLE Patient
ADD CONSTRAINT fk_Patient_MedicalHistory1 FOREIGN KEY (MedicalHistory_id) REFERENCES MedicalHistory (id);

-- ALTER TABLE Patient
-- ADD CONSTRAINT fk_Patient_User1 FOREIGN KEY (User_id) REFERENCES User (id);

ALTER TABLE Appointment
ADD CONSTRAINT fk_Appointment_Doctor1 FOREIGN KEY (Doctor_id) REFERENCES Doctor (id);

ALTER TABLE Appointment
ADD CONSTRAINT fk_Appointment_Patient1 FOREIGN KEY (Patient_id) REFERENCES Patient (id);

ALTER TABLE HealthInsurance
ADD CONSTRAINT fk_HealthInsurance_Patient1 FOREIGN KEY (Patient_id) REFERENCES Patient (id);

ALTER TABLE Invoice
ADD CONSTRAINT fk_Invoice_Patient1 FOREIGN KEY (Patient_id) REFERENCES Patient (id);

ALTER TABLE Resource
ADD CONSTRAINT fk_Resource_Doctor1 FOREIGN KEY (Doctor_id) REFERENCES Doctor (id);

ALTER TABLE AppointmentSchedule
ADD CONSTRAINT fk_AppointmentSchedule_Doctor1 FOREIGN KEY (Doctor_id) REFERENCES Doctor (id);
