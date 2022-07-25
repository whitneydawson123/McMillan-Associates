SET NAMES utf8mb4;
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';


DROP SCHEMA IF EXISTS mcmillanhris;
CREATE SCHEMA mcmillanhris;
USE mcmillanhris;

--
-- Table structure for table `employee`
--

CREATE TABLE employee(
  emplid INT UNSIGNED NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(45) NOT NULL,
  last_name VARCHAR(45) NOT NULL,
  address VARCHAR(45) NOT NULL,
  city VARCHAR(45) NOT NULL,
  state VARCHAR(2) NOT NULL,
  zip VARCHAR(10) NOT NULL,
  email VARCHAR(50) NOT NULL,
  phone VARCHAR(15) NOT NULL,
  employment_status VARCHAR(45) NOT NULL,
  PRIMARY KEY  (emplid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Table structure for table `statetax`
--

CREATE TABLE state_tax(
statetaxid INT UNSIGNED NOT NULL AUTO_INCREMENT,
emplid INT UNSIGNED NOT NULL,
required BOOLEAN,
rate DOUBLE NOT NULL,
PRIMARY KEY  (statetaxid),
FOREIGN KEY (emplid) REFERENCES employee(emplid) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Table structure for table `federaltax`
--

CREATE TABLE federal_tax(
fedtaxid INT UNSIGNED NOT NULL AUTO_INCREMENT,
emplid INT UNSIGNED NOT NULL,
bracket VARCHAR(10) NOT NULL,
rate DOUBLE NOT NULL,
PRIMARY KEY  (fedtaxid),
FOREIGN KEY (emplid) REFERENCES employee(emplid) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Table structure for table `payroll`
--

CREATE TABLE payroll(
payid INT UNSIGNED NOT NULL AUTO_INCREMENT,
emplid INT UNSIGNED NOT NULL,
rates DOUBLE NOT NULL,
rates_overtime DOUBLE NOT NULL,
total_hours INT NOT NULL,
total_overtime INT NOT NULL,
grosspay DOUBLE NOT NULL,
period DATE,
PRIMARY KEY  (payid),
FOREIGN KEY (emplid) REFERENCES employee(emplid) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


--
-- Table structure for table `benefits`
--

CREATE TABLE benefits(
benid INT UNSIGNED NOT NULL AUTO_INCREMENT,
emplid INT UNSIGNED NOT NULL,
healthcare VARCHAR(45) NOT NULL,
dentalcare VARCHAR(45) NOT NULL,
annual_vacation_days INT NOT NULL,
annual_sick_days INT NOT NULL,
pension DOUBLE NOT NULL,
ira VARCHAR(45) NOT NULL,
maternity_leave VARCHAR(45) NOT NULL,
PRIMARY KEY  (benid),
FOREIGN KEY (emplid) REFERENCES employee(emplid) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table 'location'

CREATE TABLE location(
	locid INT UNSIGNED NOT NULL AUTO_INCREMENT,
    address VARCHAR (50) NOT NULL,
    city VARCHAR (50) NOT NULL,
    state VARCHAR (50) NOT NULL,
    PRIMARY KEY (locid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table 'departments'

CREATE TABLE departments(
	depid INT UNSIGNED NOT NULL AUTO_INCREMENT,
    locid INT UNSIGNED NOT NULL,
    title VARCHAR (45) NOT NULL,
    employee_count INT NOT NULL,
    PRIMARY KEY (depid),
    FOREIGN KEY (locid) REFERENCES location(locid) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table 'job'

CREATE TABLE job(
	jobid INT UNSIGNED NOT NULL AUTO_INCREMENT,
	depid INT UNSIGNED NOT NULL,
    emplid INT UNSIGNED NOT NULL,
    title VARCHAR (50) NOT NULL,
    started DATE,
    ended DATE,
    PRIMARY KEY(jobid),
    FOREIGN KEY (emplid) REFERENCES employee(emplid) ON DELETE CASCADE,
    FOREIGN KEY (depid) REFERENCES departments(depid) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table `certifications`

CREATE TABLE certifications(
certid INT UNSIGNED NOT NULL AUTO_INCREMENT,
emplid INT UNSIGNED NOT NULL,
certification_type VARCHAR (100) NOT NULL,
experation DATE,
PRIMARY KEY (certid),
FOREIGN KEY (emplid) REFERENCES employee(emplid) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table `training`

CREATE TABLE training(
trainid INT UNSIGNED NOT NULL AUTO_INCREMENT,
emplid INT UNSIGNED NOT NULL,
training_type VARCHAR (100) NOT NULL,
date_completed DATE,
PRIMARY KEY (trainid),
FOREIGN KEY (emplid) REFERENCES employee(emplid) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table `qualifications`

CREATE TABLE qualifications(
qualid INT UNSIGNED NOT NULL AUTO_INCREMENT,
emplid INT UNSIGNED NOT NULL,
qualification_type VARCHAR (100) NOT NULL,
institution VARCHAR(50) NOT NULL,
year_accomplished DATE,
PRIMARY KEY (qualid),
FOREIGN KEY (emplid) REFERENCES employee(emplid) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table `applicant tracking`

CREATE TABLE applicant_tracking(
appid INT UNSIGNED NOT NULL AUTO_INCREMENT,
emplid INT UNSIGNED NOT NULL,
date_applied DATE,
date_hired DATE,
PRIMARY KEY (appid),
FOREIGN KEY (emplid) REFERENCES employee(emplid) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table 'application stage'

CREATE TABLE application_stage(
appstageid INT UNSIGNED NOT NULL AUTO_INCREMENT,
appid INT UNSIGNED NOT NULL,
application_stage VARCHAR (50) NOT NULL,
started DATE,
ended DATE,
interview_notes VARCHAR (50) NOT NULL,
passed BOOLEAN,
PRIMARY KEY  (appstageid),
FOREIGN KEY (appid) REFERENCES applicant_tracking(appid) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table 'Evaluation'

CREATE TABLE evaluation(
evalid INT UNSIGNED NOT NULL AUTO_INCREMENT,
emplid INT UNSIGNED NOT NULL,
communication INT NOT NULL,
job_knowledge INT NOT NULL,
punctuality INT NOT NULL,
dependability INT NULL,
overall INT NOT NULL,
evaluator VARCHAR (50) NOT NULL,
date_written DATE,
comments VARCHAR (50) NOT NULL,
PRIMARY KEY (evalid),
CONSTRAINT FOREIGN KEY (emplid) REFERENCES employee(emplid) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;