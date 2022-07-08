DROP SCHEMA IF EXISTS mcmilanhris;
CREATE SCHEMA mcmilanhris;
USE mcmilanhris;

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