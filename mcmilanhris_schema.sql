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
  mgrid INT UNSIGNED NOT NULL,
  employment_status VARCHAR(45) NOT NULL,
  qualid INT UNSIGNED NOT NULL,
  certid INT UNSIGNED NOT NULL,
  jobid INT UNSIGNED NOT NULL,
  trainingid INT UNSIGNED NOT NULL,
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
payrollid INT UNSIGNED NOT NULL AUTO_INCREMENT,
emplid INT UNSIGNED NOT NULL,
rates DOUBLE NOT NULL,
rates_overtime DOUBLE NOT NULL,
totalhours INT NOT NULL,
totalovertime INT NOT NULL,
grosspay DOUBLE NOT NULL,
PRIMARY KEY  (payrollid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


--
-- Table structure for table `benefits`
--

CREATE TABLE benefits(
benid INT UNSIGNED NOT NULL AUTO_INCREMENT,
emplid INT UNSIGNED NOT NULL,
healthcare VARCHAR(45) NOT NULL,
dentalcare VARCHAR(45) NOT NULL,
annual_sickdays SMALLINT NOT NULL,
annual_vacationdays SMALLINT NOT NULL,
pension DOUBLE NOT NULL,
ira VARCHAR(45) NOT NULL,
maternityleave VARCHAR(45) NOT NULL,
PRIMARY KEY  (benid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table 'departments'

CREATE TABLE departments(
	depid INT UNSIGNED NOT NULL AUTO_INCREMENT,
    IT BOOLEAN NOT NULL,
    Marketing BOOLEAN NOT NULL,
    Finance BOOLEAN NOT NULL,
    HR BOOLEAN NOT NULL,
    PRIMARY KEY (depid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table 'manager'

CREATE TABLE manager(
	mgrid INT UNSIGNED NOT NULL AUTO_INCREMENT,
    depid INT UNSIGNED NOT NULL,
    IT_Manager BOOLEAN NOT NULL,
    Marketing_Manager BOOLEAN NOT NULL,
    Finance_Manager BOOLEAN NOT NULL,
    HR_Manager BOOLEAN NOT NULL,
    PRIMARY KEY (mgrid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table 'location'

CREATE TABLE location(
	locid INT UNSIGNED NOT NULL AUTO_INCREMENT,
    Minnesota_MN BOOLEAN NOT NULL,
    Charlotte_NC BOOLEAN NOT NULL,
    Tampa_FL BOOLEAN NOT NULL,
    PRIMARY KEY (locid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table 'job'

CREATE TABLE job(
	jobid INT UNSIGNED NOT NULL AUTO_INCREMENT,
	depid INT UNSIGNED NOT NULL,
    net_developer BOOLEAN NOT NULL,
    front_end_developer BOOLEAN NOT NULL,
    hr_expert BOOLEAN NOT NULL,
    marketing BOOLEAN NOT NULL,
    accounting BOOLEAN NOT NULL,
    back_end_developer BOOLEAN NOT NULL,
    PRIMARY KEY(jobid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table `certifications`

CREATE TABLE certifications(
certid INT UNSIGNED NOT NULL AUTO_INCREMENT,
emplid INT UNSIGNED NOT NULL,
Cloud_Architect BOOLEAN NOT NULL,
Cloud_Architect_Experation DATE,
Solutions_Architect BOOLEAN NOT NULL,
Solutions_Architect_Experation DATE,
Attribution_Testing BOOLEAN NOT NULL,
Attribution_Testing_Experation DATE,
Automation_Artificial BOOLEAN NOT NULL,
Automation_Artificial_Experation DATE,
Financial_Planner BOOLEAN NOT NULL,
Financial_Planner_Experation DATE,
Financial_Consultant BOOLEAN NOT NULL,
Financial_Consultant_Experation DATE,
HRCI_Certification BOOLEAN NOT NULL,
HRCI_Certification_Experation DATE,
SHRM_Certification BOOLEAN NOT NULL,
SHRM_Certification_Experation DATE,
PRIMARY KEY (certid)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table `training`

CREATE TABLE training(
trainid INT UNSIGNED NOT NULL AUTO_INCREMENT,
emplid INT UNSIGNED NOT NULL,
cybersec BOOLEAN NOT NULL,
cybersecdate DATE,
harrassment BOOLEAN NOT NULL,
harassmentdate DATE,
companyculture BOOLEAN NOT NULL,
companyculturedate DATE,
intellectualproperty BOOLEAN NOT NULL,
intellectualpropertydate DATE,
PRIMARY KEY (trainid)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table `qualifications`

CREATE TABLE qualifications(
qualid INT UNSIGNED NOT NULL AUTO_INCREMENT,
emplid INT UNSIGNED NOT NULL,
associates_degree BOOLEAN NOT NULL,
associates_degree_school VARCHAR(100) NOT NULL,
associates_degree_date DATE,
bachelors_degree BOOLEAN NOT NULL,
bachelors_degree_school VARCHAR(100) NOT NULL,
bachelors_degree_date DATE,
masters_degree BOOLEAN NOT NULL,
masters_degree_school VARCHAR(100) NOT NULL,
masters_degree_date DATE,
experience INT NOT NULL,
language_ability BOOLEAN NOT NULL,
PRIMARY KEY (qualid)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table `applicant tracking`

CREATE TABLE applicant_tracking(
appid INT UNSIGNED NOT NULL AUTO_INCREMENT,
app_fname VARCHAR(50) NOT NULL,
app_lname VARCHAR(50) NOT NULL,
app_email VARCHAR(50) NOT NULL,
app_phase1 BOOLEAN NOT NULL,
app_submission BOOLEAN NOT NULL,
app_submission_date DATE,
app_resume BOOLEAN NOT NULL,
qualid INT UNSIGNED NOT NULL,
certid INT UNSIGNED NOT NULL,
app_phase2 BOOLEAN NOT NULL,
app_interview BOOLEAN NOT NULL,
app_interview_date DATE,
interviewer_notes VARCHAR(100) NOT NULL,
app_phase3 BOOLEAN NOT NULL,
app_hired BOOLEAN NOT NULL,
app_hired_date DATE,
PRIMARY KEY (appid)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- table alterations to add foreign keys

ALTER TABLE employee
ADD FOREIGN KEY (mgrid) REFERENCES manager(mgrid),
ADD FOREIGN KEY (jobid) REFERENCES job(jobid),
ADD FOREIGN KEY (qualid) REFERENCES qualifications(qualid),
ADD FOREIGN KEY (certid) REFERENCES certifications(certid),
ADD FOREIGN KEY (trainid) REFERENCES training(trainid);

ALTER TABLE state_tax
ADD FOREIGN KEY (emplid) REFERENCES employee(emplid);

ALTER TABLE federal_tax
ADD FOREIGN KEY (emplid) REFERENCES employee(emplid);

ALTER TABLE payroll
ADD FOREIGN KEY (emplid) REFERENCES employee(emplid);

ALTER TABLE benefits
ADD FOREIGN KEY (emplid) REFERENCES employee(emplid);

ALTER TABLE manager
ADD FOREIGN KEY (depid) REFERENCES departments(depid);

ALTER TABLE job
ADD FOREIGN KEY (depid) REFERENCES departments(depid);

ALTER TABLE certifications
ADD FOREIGN KEY (emplid) REFERENCES employee(emplid);

ALTER TABLE training
ADD FOREIGN KEY (emplid) REFERENCES employee(emplid);

ALTER TABLE qualifications
ADD FOREIGN KEY (emplid) REFERENCES employee(emplid);

ALTER TABLE applicant_tracking
ADD FOREIGN KEY (qualid) REFERENCES qualifications(qualid),
ADD FOREIGN KEY (certid) REFERENCES certifications(certid);
