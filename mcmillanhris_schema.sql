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
  employment_status VARCHAR(45) NOT NULL,
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
statetaxid SMALLINT UNSIGNED NOT NULL,
emplid SMALLINT UNSIGNED NOT NULL,
required BOOLEAN,
rate DOUBLE NOT NULL,
PRIMARY KEY  (statetaxid),
FOREIGN KEY (emplid) REFERENCES employee(emplid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Table structure for table `federaltax`
--

CREATE TABLE federal_tax(
fedtaxid SMALLINT UNSIGNED NOT NULL,
emplid SMALLINT UNSIGNED NOT NULL,
bracket VARCHAR(10) NOT NULL,
rate DOUBLE NOT NULL,
PRIMARY KEY  (fedtaxid),
FOREIGN KEY (emplid) REFERENCES employee(emplid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Table structure for table `payroll`
--

CREATE TABLE payroll(
payrollid SMALLINT UNSIGNED NOT NULL,
emplid SMALLINT UNSIGNED NOT NULL,
rates DOUBLE NOT NULL,
rates_overtime DOUBLE NOT NULL,
totalhours SMALLINT NOT NULL,
totalovertime SMALLINT NOT NULL,
grosspay DOUBLE NOT NULL,
PRIMARY KEY  (payrollid),
FOREIGN KEY (emplid) REFERENCES employee(emplid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


--
-- Table structure for table `benefits`
--

CREATE TABLE benefits(
benid SMALLINT UNSIGNED NOT NULL,
emplid SMALLINT UNSIGNED NOT NULL,
healthcare VARCHAR(45) NOT NULL,
dentalcare VARCHAR(45) NOT NULL,
annual_sickdays SMALLINT NOT NULL,
annual_vacationdays SMALLINT NOT NULL,
pension DOUBLE NOT NULL,
ira VARCHAR(45) NOT NULL,
maternityleave VARCHAR(45) NOT NULL,
PRIMARY KEY  (benid),
FOREIGN KEY (emplid) REFERENCES employee(emplid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE departments(
	depid INT NOT NULL AUTO_INCREMENT,
    IT BOOLEAN NOT NULL,
    Marketing BOOLEAN NOT NULL,
    Finance BOOLEAN NOT NULL,
    HR BOOLEAN NOT NULL,
    PRIMARY KEY (depid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE manager(
	mgrid INT NOT NULL AUTO_INCREMENT,
    depid INT NOT NULL,
    IT_Manager BOOLEAN NOT NULL,
    Marketing_Manager BOOLEAN NOT NULL,
    Finance_Manager BOOLEAN NOT NULL,
    HR_Manager BOOLEAN NOT NULL,
    PRIMARY KEY (mgrid),
    FOREIGN KEY (depid) REFERENCES departments (depid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE location(
	locid INT NOT NULL AUTO_INCREMENT,
    Minnesota_MN BOOLEAN NOT NULL,
    Charlotte_NC BOOLEAN NOT NULL,
    Tampa_FL BOOLEAN NOT NULL,
    PRIMARY KEY (locid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE job(
	jobid INT NOT NULL AUTO_INCREMENT,
    net_developer BOOLEAN NOT NULL,
    front_end_developer BOOLEAN NOT NULL,
    hr_expert BOOLEAN NOT NULL,
    marketing BOOLEAN NOT NULL,
    accounting BOOLEAN NOT NULL,
    back_end_developer BOOLEAN NOT NULL,
    PRIMARY KEY(jobid),
    FOREIGN KEY (depid) REFERENCES departments (depid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
