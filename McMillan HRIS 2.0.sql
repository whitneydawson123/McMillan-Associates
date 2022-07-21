DROP SCHEMA IF EXISTS mcmillanhris;
CREATE SCHEMA mcmillanhris;
USE mcmillanhris;

--
-- Table structure for table `employee`
--

CREATE TABLE employee(
  emplid INT UNSIGNED NOT NULL AUTO_INCREMENT,
  fname VARCHAR(45) NOT NULL,
  lname VARCHAR(45) NOT NULL,
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
PRIMARY KEY  (statetaxid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Table structure for table `federaltax`
--

CREATE TABLE federal_tax(
fedtaxid INT UNSIGNED NOT NULL AUTO_INCREMENT,
emplid INT UNSIGNED NOT NULL,
bracket VARCHAR(10) NOT NULL,
rate DOUBLE NOT NULL,
PRIMARY KEY  (fedtaxid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Table structure for table `payroll`
--

CREATE TABLE payroll(
payid INT UNSIGNED NOT NULL AUTO_INCREMENT,
emplid INT UNSIGNED NOT NULL,
rates DOUBLE NOT NULL,
rates_overtime DOUBLE NOT NULL,
totalhours INT NOT NULL,
totalovertime INT NOT NULL,
grosspay DOUBLE NOT NULL,
period DATE,
PRIMARY KEY  (payid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


--
-- Table structure for table `benefits`
--

CREATE TABLE benefits(
benid INT UNSIGNED NOT NULL AUTO_INCREMENT,
emplid INT UNSIGNED NOT NULL,
healthcare VARCHAR(45) NOT NULL,
dentalcare VARCHAR(45) NOT NULL,
annual_vacationdays INT NOT NULL,
annual_sickdays INT NOT NULL,
pension DOUBLE NOT NULL,
ira VARCHAR(45) NOT NULL,
maternityleave VARCHAR(45) NOT NULL,
PRIMARY KEY  (benid)
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
    employeecount INT NOT NULL,
    PRIMARY KEY (depid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table 'job'

CREATE TABLE job(
	jobid INT UNSIGNED NOT NULL AUTO_INCREMENT,
	depid INT UNSIGNED NOT NULL,
    emplid INT UNSIGNED NOT NULL,
    title VARCHAR (50) NOT NULL,
    started DATE,
    ended DATE,
    PRIMARY KEY(jobid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table `certifications`

CREATE TABLE certifications(
certid INT UNSIGNED NOT NULL AUTO_INCREMENT,
emplid INT UNSIGNED NOT NULL,
cert_type VARCHAR (100) NOT NULL,
experation DATE,
PRIMARY KEY (certid)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table `training`

CREATE TABLE training(
trainid INT UNSIGNED NOT NULL AUTO_INCREMENT,
emplid INT UNSIGNED NOT NULL,
training_type VARCHAR (100) NOT NULL,
date_completed DATE,
PRIMARY KEY (trainid)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table `qualifications`

CREATE TABLE qualifications(
qualid INT UNSIGNED NOT NULL AUTO_INCREMENT,
emplid INT UNSIGNED NOT NULL,
qual_type VARCHAR (100) NOT NULL,
institution VARCHAR(50) NOT NULL,
qual_year DATE,
PRIMARY KEY (qualid)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table `applicant tracking`

CREATE TABLE applicant_tracking(
appid INT UNSIGNED NOT NULL AUTO_INCREMENT,
emplid INT UNSIGNED NOT NULL,
date_applied DATE,
date_hired DATE,
PRIMARY KEY (appid)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table 'application stage'

CREATE TABLE application_stage(
appstageid INT UNSIGNED NOT NULL AUTO_INCREMENT,
appid INT UNSIGNED NOT NULL,
app_stage VARCHAR (50) NOT NULL,
started DATE,
ended DATE,
interview_notes VARCHAR (50) NOT NULL,
passed BOOLEAN,
PRIMARY KEY  (appstageid)
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
PRIMARY KEY (evalid)
);

-- table alterations to add foreign keys

ALTER TABLE state_tax
ADD FOREIGN KEY (emplid) REFERENCES employee(emplid);

ALTER TABLE federal_tax
ADD FOREIGN KEY (emplid) REFERENCES employee(emplid);

ALTER TABLE payroll
ADD FOREIGN KEY (emplid) REFERENCES employee(emplid);

ALTER TABLE benefits
ADD FOREIGN KEY (emplid) REFERENCES employee(emplid);

ALTER TABLE job
ADD FOREIGN KEY (depid) REFERENCES departments(depid),
ADD FOREIGN KEY (emplid) REFERENCES employee(emplid);

ALTER TABLE certifications
ADD FOREIGN KEY (emplid) REFERENCES employee(emplid);

ALTER TABLE training
ADD FOREIGN KEY (emplid) REFERENCES employee(emplid);

ALTER TABLE qualifications
ADD FOREIGN KEY (emplid) REFERENCES employee(emplid);

ALTER TABLE applicant_tracking
ADD FOREIGN KEY (emplid) REFERENCES employee(emplid);

ALTER TABLE application_stage
ADD FOREIGN KEY (appid) REFERENCES applicant_tracking(appid);

ALTER TABLE evaluation
ADD FOREIGN KEY (emplid) REFERENCES employee(emplid);
