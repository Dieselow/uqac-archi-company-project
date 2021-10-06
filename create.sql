
-- CREATE database archiDB;

-- dropping tables

DROP TABLE IF EXISTS Appointment_Prescription;
DROP TABLE IF EXISTS Tickets_Consumable;
DROP TABLE IF EXISTS Consumable_Inventory;
DROP TABLE IF EXISTS Tickets;
DROP TABLE IF EXISTS Orders;
DROP TABLE IF EXISTS InternInvoices;
DROP TABLE IF EXISTS Consumable;
DROP TABLE IF EXISTS Inventory;
DROP TABLE IF EXISTS EquipmentType;
DROP TABLE IF EXISTS Equipment;
DROP TABLE IF EXISTS Horodateur;
DROP TABLE IF EXISTS Appointment;
DROP TABLE IF EXISTS AppointmentType;
DROP TABLE IF EXISTS Room;
DROP TABLE IF EXISTS HealthFile;
DROP TABLE IF EXISTS user_rights;
DROP TABLE IF EXISTS Invoices;
DROP TABLE IF EXISTS Prescription;
DROP TABLE IF EXISTS user;




CREATE TABLE Horodateur (
  ID            int(10) NOT NULL AUTO_INCREMENT,
  AppointmentID int(10) NOT NULL,
  RoomID        int(10) NOT NULL,
  PRIMARY KEY (ID));
CREATE TABLE Appointment (
  ID         int(10) NOT NULL AUTO_INCREMENT,
  InvoicesID int(10) NOT NULL,
  PatientID    int(10) NOT NULL,
  RoomID     int(10) NOT NULL,
  CaregiverID     int(10) NOT NULL,
  PRIMARY KEY (ID));
CREATE TABLE AppointmentType (
  ID   int(10) NOT NULL AUTO_INCREMENT,
  Name varchar(255),
  PRIMARY KEY (ID));
CREATE TABLE Invoices (
  ID            int(10) NOT NULL AUTO_INCREMENT,
  UserID        int(10) NOT NULL,
  PaymentMethod varchar(255),
  PaymentDate   date,
  PRIMARY KEY (ID));
CREATE TABLE Prescription (
  ID               int(10) NOT NULL AUTO_INCREMENT,
  UserID           int(10) NOT NULL,
  PrescriptionText varchar(255),
  PrescriptionDate date,
  PRIMARY KEY (ID));
CREATE TABLE user (
  ID             int(10) NOT NULL AUTO_INCREMENT,
  Username       varchar(255),
  Password       varchar(255),
  FirstName      varchar(255),
  LastName       varchar(255),
  DateOfBirth    date,
  Email          varchar(255),
  Address        varchar(255),
  PhoneNumber    varchar(255),
  Salary         float,
  WorkSchedule   varchar(255),
  EmploymentDate date,
  LicenceNumber  varchar(255),
  PRIMARY KEY (ID));
CREATE TABLE user_rights (
  ID   int(10) NOT NULL AUTO_INCREMENT,
  Name varchar(255),
  PRIMARY KEY (ID));
CREATE TABLE HealthFile (
  ID                int(10) NOT NULL AUTO_INCREMENT,
  UserID            int(10) NOT NULL,
  Medications       varchar(255),
  ChronicConditions varchar(255),
  PrimaryDoctor     int(10) NOT NULL,
  EmergencyContact  varchar(255),
  PRIMARY KEY (ID));
CREATE TABLE Room (
  ID   int(10) NOT NULL AUTO_INCREMENT,
  Name varchar(255),
  PRIMARY KEY (ID));
CREATE TABLE Equipment (
  ID               int(10) NOT NULL AUTO_INCREMENT,
  RoomID           int(10) NOT NULL,
  InstallationDate date,
  PRIMARY KEY (ID));
CREATE TABLE EquipmentType (
  ID   int(10) NOT NULL AUTO_INCREMENT,
  Name varchar(255),
  PRIMARY KEY (ID));
CREATE TABLE Inventory (
  ID int(10) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (ID));
CREATE TABLE Consumable (
  ID       int(10) NOT NULL AUTO_INCREMENT,
  Quantity int(10) NOT NULL,
  Treshold int(10) NOT NULL,
  PRIMARY KEY (ID));
CREATE TABLE Tickets (
  ID          int(10) NOT NULL AUTO_INCREMENT,
  OrderID    int(10),
  UserID      int(10) NOT NULL,
  RequestDate date,
  PRIMARY KEY (ID));
CREATE TABLE Orders (
  ID               int(10) NOT NULL AUTO_INCREMENT,
  InternInvoicesID int(10),
  OrderSentDate    date,
  PRIMARY KEY (ID));
CREATE TABLE InternInvoices (
  ID    int(10) NOT NULL AUTO_INCREMENT,
  Price float NOT NULL,
  PRIMARY KEY (ID));
CREATE TABLE Consumable_Inventory (
  ConsumableID int(10) NOT NULL,
  InventoryID  int(10) NOT NULL,
  PRIMARY KEY (ConsumableID,
  InventoryID));
CREATE TABLE Tickets_Consumable (
  TicketsID    int(10) NOT NULL,
  ConsumableID int(10) NOT NULL,
  PRIMARY KEY (TicketsID,
  ConsumableID));
CREATE TABLE Appointment_Prescription (
  AppointmentID  int(10) NOT NULL,
  PrescriptionID int(10) NOT NULL,
  PRIMARY KEY (AppointmentID,
  PrescriptionID));

  -- Ajout des contraintes :

  ALTER TABLE Horodateur ADD CONSTRAINT FKHorodateur143537 FOREIGN KEY (AppointmentID) REFERENCES Appointment (ID);
ALTER TABLE Appointment ADD CONSTRAINT FKAppointmen527929 FOREIGN KEY (InvoicesID) REFERENCES Invoices (ID);
ALTER TABLE Invoices ADD CONSTRAINT FKInvoices246726 FOREIGN KEY (UserID) REFERENCES user (ID);
ALTER TABLE Prescription ADD CONSTRAINT FKPrescripti894942 FOREIGN KEY (UserID) REFERENCES user (ID);
ALTER TABLE HealthFile ADD CONSTRAINT FKHealthFile353315 FOREIGN KEY (UserID) REFERENCES user (ID);
ALTER TABLE Appointment ADD CONSTRAINT FKAppointmen21410 FOREIGN KEY (PatientID) REFERENCES user (ID);
ALTER TABLE Appointment ADD CONSTRAINT FKAppointmen643657 FOREIGN KEY (CaregiverID) REFERENCES user (ID);
ALTER TABLE Equipment ADD CONSTRAINT FKEquipment428915 FOREIGN KEY (RoomID) REFERENCES Room (ID);
ALTER TABLE Tickets ADD CONSTRAINT FKTickets258133 FOREIGN KEY (OrderID) REFERENCES Orders (ID);
ALTER TABLE Orders ADD CONSTRAINT FKOrders20042 FOREIGN KEY (InternInvoicesID) REFERENCES InternInvoices (ID);
ALTER TABLE Tickets ADD CONSTRAINT FKTickets841132 FOREIGN KEY (UserID) REFERENCES user (ID);
ALTER TABLE Consumable_Inventory ADD CONSTRAINT FKConsumable309180 FOREIGN KEY (ConsumableID) REFERENCES Consumable (ID);
ALTER TABLE Consumable_Inventory ADD CONSTRAINT FKConsumable895403 FOREIGN KEY (InventoryID) REFERENCES Inventory (ID);
ALTER TABLE Tickets_Consumable ADD CONSTRAINT FKTickets_Co394299 FOREIGN KEY (TicketsID) REFERENCES Tickets (ID);
ALTER TABLE Tickets_Consumable ADD CONSTRAINT FKTickets_Co173873 FOREIGN KEY (ConsumableID) REFERENCES Consumable (ID);
ALTER TABLE Appointment_Prescription ADD CONSTRAINT FKAppointmen100273 FOREIGN KEY (AppointmentID) REFERENCES Appointment (ID);
ALTER TABLE Appointment_Prescription ADD CONSTRAINT FKAppointmen880462 FOREIGN KEY (PrescriptionID) REFERENCES Prescription (ID);


  -- affichage des tables créées

  show tables