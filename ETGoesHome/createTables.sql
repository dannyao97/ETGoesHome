-- Create table for the projects

CREATE TABLE Location (
   State CHAR(2),
   City VARCHAR(50),
   PRIMARY KEY (State, City)
);

CREATE TABLE UFOSightings (
   Occurence DATETIME,
   State CHAR(2),
   City VARCHAR(50),
   Shape VARCHAR(30),
   Duration_Seconds INT,
   Description VARCHAR(200),
   PRIMARY KEY(Occurence, City, State),
   FOREIGN KEY(State, City) REFERENCES Location(State, City)
);

CREATE TABLE Shootings (
   Id INT AUTO_INCREMENT,
   Name VARCHAR(50) NOT NULL,
   Day DATE NOT NULL,
   Death VARCHAR(50),
   Weapon VARCHAR(40),
   Age INT,
   Gender CHAR(1) NOT NULL,
   Race CHAR(1),
   City VARCHAR(50) NOT NULL,
   State CHAR(2) NOT NULL,
   Mental CHAR(1),
   Threat VARCHAR(15),
   Flee VARCHAR(15),
   BodyCamera CHAR(1),
   PRIMARY KEY(Id),
   FOREIGN KEY(State, City) REFERENCES Location(State, City)
);

