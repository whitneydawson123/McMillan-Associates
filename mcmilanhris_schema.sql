DROP SCHEMA IF EXISTS mcmillanhris;
CREATE SCHEMA mcmillanhris;
USE mcmillanhris;

--
-- Table structure for table `employee`
--

CREATE TABLE employee(
  emplid SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  fname VARCHAR(45) NOT NULL,
  lname VARCHAR(45) NOT NULL,
  address VARCHAR(45) NOT NULL,
  city VARCHAR(45) NOT NULL,
  state VARCHAR(2) NOT NULL,
  zip VARCHAR(10) NOT NULL,
  mgrid SMALLINT UNSIGNED NOT NULL,
  hired BOOLEAN,
  qualid SMALLINT UNSIGNED NOT NULL,
  certid SMALLINT UNSIGNED NOT NULL,
  jobid SMALLINT UNSIGNED NOT NULL,
  trainingid SMALLINT UNSIGNED NOT NULL,
  PRIMARY KEY  (emplid)
  /*
  foreign key constraints go here once the other tables are implemented
  FOREIGN KEY (mgrid) REFERENCES manager(mgrid)
  FOREIGN KEY (qualid) REFERENCES qualifications(qualid)
  FOREIGN KEY (certid) REFERENCES certifications(certid)
  FOREIGN KEY (jobid) REFERENCES job(jobid)
  FOREIGN KEY (trainingid) REFERENCES training(trainingid)
  */
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Table structure for table `statetax`
--

CREATE TABLE state_tax(
emplid SMALLINT UNSIGNED NOT NULL,
required BOOLEAN,
rate DOUBLE NOT NULL,
FOREIGN KEY (emplid) REFERENCES employee(emplid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Table structure for table `federaltax`
--

CREATE TABLE federal_tax(
emplid SMALLINT UNSIGNED NOT NULL,
bracket VARCHAR(10) NOT NULL,
rate DOUBLE NOT NULL,
FOREIGN KEY (emplid) REFERENCES employee(emplid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Table structure for table `payroll`
--

CREATE TABLE payroll(
emplid SMALLINT UNSIGNED NOT NULL,
rates DOUBLE NOT NULL,
rates_overtime DOUBLE NOT NULL,
totalhours SMALLINT NOT NULL,
totalovertime SMALLINT NOT NULL,
grosspay DOUBLE NOT NULL,
FOREIGN KEY (emplid) REFERENCES employee(emplid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


--
-- Table structure for table `benefits`
--

CREATE TABLE benefits(
emplid SMALLINT UNSIGNED NOT NULL,
healthcare VARCHAR(45) NOT NULL,
dentalcare VARCHAR(45) NOT NULL,
annual_sickdays SMALLINT NOT NULL,
annual_vacationdays SMALLINT NOT NULL,
pension DOUBLE NOT NULL,
ira VARCHAR(45) NOT NULL,
maternityleave VARCHAR(45) NOT NULL,
FOREIGN KEY (emplid) REFERENCES employee(emplid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE Certifications(
certid INT NOT NULL,
emplid INT NOT NULL,
Cloud_Architect BOOLEAN NOT NULL,
Cloud_Architect_Experation DATE ('mm.dd.yyyy') NOT NULL,
Solutions_Architect BOOLEAN NOT NULL,
Solutions_Architect_Experation DATE ('mm.dd.yyyy') NOT NULL,
Attribution_Testing BOOLEAN NOT NULL,
Attribution_Testing_Experation DATE ('mm.dd.yyyy') NOT NULL,
Automation_Artificial BOOLEAN NOT NULL,
Automation_Artificial_Experation DATE ('mm.dd.yyyy') NOT NULL,
Financial_Planner BOOLEAN NOT NULL,
Financial_Planner_Experation DATE ('mm.dd.yyyy') NOT NULL,
Financial_Consultant BOOLEAN NOT NULL,
Financial_Consultant_Experation DATE ('mm.dd.yyyy') NOT NULL,
HRCI_Certification BOOLEAN NOT NULL,
HRCI_Certification_Experation DATE ('mm.dd.yyyy') NOT NULL,
SHRM_Certification BOOLEAN NOT NULL,
SHRM_Certification_Experation DATE ('mm.dd.yyyy') NOT NULL,
PRIMARY KEY (certid),
FOREIGN KEY (emplid) REFERENCES employee(emplid)
);

-----------------------------------------------------------------

CREATE TABLE Training(
trainingid INT NOT NULL,
emplid INT NOT NULL,
cybersec BOOLEAN NOT NULL,
cybersecdate DATE ('mm.dd.yyyy') NOT NULL,
harrassment BOOLEAN NOT NULL,
harassmentdate DATE ('mm.dd.yyyy') NOT NULL,
companyculture BOOLEAN NOT NULL,
companyculturedate DATE ('mm.dd.yyyy') NOT NULL,
intellectualproperty BOOLEAN NOT NULL,
intellectualpropertydate DATE ('mm.dd.yyyy') NOT NULL,
PRIMARY KEY (trainingid),
FOREIGN KEY (emplid) REFERENCES employee(emplid)
);

------------------------------------------------------------

CREATE TABLE Qualifications
qualid INT NOT NULL,
emplid INT NOT NULL,
associates_degree BOOLEAN NOT NULL,
associates_degree_school VARCHAR(100) NOT NULL,
associates_degree_date DATE ('mm.dd.yyyy') NOT NULL,
bachelors_degree BOOLEAN NOT NULL,
bachelors_degree_school VARCHAR(100) NOT NULL,
bachelors_degree_date DATE ('mm.dd.yyyy') NOT NULL,
masters_degree BOOLEAN NOT NULL,
masters_degree_school VARCHAR(100) NOT NULL,
masters_degree_date DATE ('mm.dd.yyyy') NOT NULL,
experience INT NOT NULL,
language_ability BOOLEAN NOT NULL,
PRIMARY KEY (qualid),
FOREIGN KEY (emplid) REFERENCES employee(emplid)
);
