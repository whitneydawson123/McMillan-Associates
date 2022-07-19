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
  mgrid INT UNSIGNED,
  employment_status VARCHAR(45) NOT NULL,
  qualid INT UNSIGNED,
  certid INT UNSIGNED,
  jobid INT UNSIGNED,
  trainid INT UNSIGNED,
  PRIMARY KEY  (emplid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Table structure for table `statetax`
--

CREATE TABLE state_tax(
statetaxid INT UNSIGNED NOT NULL AUTO_INCREMENT,
emplid INT UNSIGNED,
required BOOLEAN,
rate DOUBLE NOT NULL,
PRIMARY KEY  (statetaxid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Table structure for table `federaltax`
--

CREATE TABLE federal_tax(
fedtaxid INT UNSIGNED NOT NULL AUTO_INCREMENT,
emplid INT UNSIGNED,
bracket VARCHAR(10) NOT NULL,
rate DOUBLE NOT NULL,
PRIMARY KEY  (fedtaxid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Table structure for table `payroll`
--

CREATE TABLE payroll(
payrollid INT UNSIGNED NOT NULL AUTO_INCREMENT,
emplid INT UNSIGNED,
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
emplid INT UNSIGNED,
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
    emplid INT UNSIGNED,
    IT BOOLEAN NOT NULL,
    Marketing BOOLEAN NOT NULL,
    Finance BOOLEAN NOT NULL,
    HR BOOLEAN NOT NULL,
    PRIMARY KEY (depid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table 'manager'

CREATE TABLE manager(
    mgrid INT UNSIGNED NOT NULL AUTO_INCREMENT,
    depid INT UNSIGNED,
    emplid INT UNSIGNED,
    IT_Manager BOOLEAN NOT NULL,
    Marketing_Manager BOOLEAN NOT NULL,
    Finance_Manager BOOLEAN NOT NULL,
    HR_Manager BOOLEAN NOT NULL,
    PRIMARY KEY (mgrid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table 'location'

CREATE TABLE location(
    locid INT UNSIGNED NOT NULL AUTO_INCREMENT,
    emplid INT UNSIGNED,
    Minnesota_MN BOOLEAN NOT NULL,
    Charlotte_NC BOOLEAN NOT NULL,
    Tampa_FL BOOLEAN NOT NULL,
    PRIMARY KEY (locid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table 'job'

CREATE TABLE job(
    jobid INT UNSIGNED NOT NULL AUTO_INCREMENT,
    depid INT UNSIGNED,
    emplid INT UNSIGNED,
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
emplid INT UNSIGNED,
appid INT UNSIGNED,	
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
emplid INT UNSIGNED,
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
emplid INT UNSIGNED,
appid INT UNSIGNED,
associates_degree BOOLEAN NOT NULL,
associates_degree_school VARCHAR(100),
associates_degree_date DATE,
bachelors_degree BOOLEAN NOT NULL,
bachelors_degree_school VARCHAR(100),
bachelors_degree_date DATE,
masters_degree BOOLEAN NOT NULL,
masters_degree_school VARCHAR(100),
masters_degree_date DATE,
experience INT NOT NULL,
language_ability BOOLEAN NOT NULL,
PRIMARY KEY (qualid)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table `applicant tracking`

CREATE TABLE applicant_tracking(
appid INT UNSIGNED NOT NULL AUTO_INCREMENT,
emplid INT UNSIGNED,
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

CREATE TABLE evaluation(
evalid INT UNSIGNED NOT NULL AUTO_INCREMENT,
emplid INT UNSIGNED,
communication INT NOT NULL,
job_knowledge INT NOT NULL,
punctuality INT NOT NULL,
depentability INT NOT NULL,
overall INT NOT NULL,
evaluator VARCHAR(50) NOT NULL,
mgrid INT UNSIGNED,
datewritten date NOT NULL,
comment VARCHAR(100),
PRIMARY KEY (evalid)
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

ALTER TABLE departments
ADD FOREIGN KEY (emplid) REFERENCES employee(emplid);

ALTER TABLE location
ADD FOREIGN KEY (emplid) REFERENCES employee(emplid);

ALTER TABLE manager
ADD FOREIGN KEY (depid) REFERENCES departments(depid),
ADD FOREIGN KEY (emplid) REFERENCES employee(emplid);

ALTER TABLE job
ADD FOREIGN KEY (depid) REFERENCES departments(depid),
ADD FOREIGN KEY (emplid) REFERENCES employee(emplid);

ALTER TABLE certifications
ADD FOREIGN KEY (emplid) REFERENCES employee(emplid),
ADD FOREIGN KEY (appid) REFERENCES applicant_tracking(appid);

ALTER TABLE training
ADD FOREIGN KEY (emplid) REFERENCES employee(emplid);

ALTER TABLE qualifications
ADD FOREIGN KEY (emplid) REFERENCES employee(emplid),
ADD FOREIGN KEY (appid) REFERENCES applicant_tracking(appid);

ALTER TABLE applicant_tracking
ADD FOREIGN KEY (qualid) REFERENCES qualifications(qualid),
ADD FOREIGN KEY (certid) REFERENCES certifications(certid),
ADD FOREIGN KEY (emplid) REFERENCES employee(emplid);

ALTER TABLE evaluation
ADD FOREIGN KEY (emplid) REFERENCES employee(emplid),
ADD FOREIGN KEY (mgrid) REFERENCES manager(mgrid);
