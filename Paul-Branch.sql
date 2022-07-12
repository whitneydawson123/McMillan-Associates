-- Table structure for table `certifications`

CREATE TABLE Certifications(
certid SMALLINT UNSIGNED NOT NULL,
emplid SMALLINT UNSIGNED NOT NULL,
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
PRIMARY KEY (certid))
ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table `training`

CREATE TABLE Training(
trainid SMALLINT UNSIGNED NOT NULL,
emplid SMALLINT UNSIGNED NOT NULL,
cybersec BOOLEAN NOT NULL,
cybersecdate DATE,
harrassment BOOLEAN NOT NULL,
harassmentdate DATE,
companyculture BOOLEAN NOT NULL,
companyculturedate DATE,
intellectualproperty BOOLEAN NOT NULL,
intellectualpropertydate DATE,
PRIMARY KEY (trainid))
ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table `qualifications`

CREATE TABLE Qualifications(
qualid SMALLINT UNSIGNED NOT NULL,
emplid SMALLINT UNSIGNED NOT NULL,
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
PRIMARY KEY (qualid))
ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table `applicant tracking`

CREATE TABLE Applicant_Tracking(
appid SMALLINT UNSIGNED NOT NULL,
app_fname VARCHAR(50) NOT NULL,
app_lname VARCHAR(50) NOT NULL,
app_email VARCHAR(50) NOT NULL,
app_phase1 BOOLEAN NOT NULL,
app_submission BOOLEAN NOT NULL,
app_submission_date DATE,
app_resume BOOLEAN NOT NULL,
qualid SMALLINT UNSIGNED NOT NULL,
certid SMALLINT UNSIGNED NOT NULL,
app_phase2 BOOLEAN NOT NULL,
app_interview BOOLEAN NOT NULL,
app_interview_date DATE,
interviewer_notes VARCHAR(100) NOT NULL,
app_phase3 BOOLEAN NOT NULL,
app_hired BOOLEAN NOT NULL,
app_hired_date DATE,
PRIMARY KEY (appid))
ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- table alterations to add foreign keys

ALTER TABLE certifications
ADD FOREIGN KEY (emplid) REFERENCES employee(emplid);

ALTER TABLE training
ADD FOREIGN KEY (emplid) REFERENCES employee(emplid);

ALTER TABLE qualifications
ADD FOREIGN KEY (emplid) REFERENCES employee(emplid);

ALTER TABLE applicant_tracking
ADD FOREIGN KEY (qualid) REFERENCES qualifications(qualid),
ADD FOREIGN KEY (certid) REFERENCES certifications(certid);
