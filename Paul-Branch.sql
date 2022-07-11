--
-- Table structure for table `certifications`
--

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
PRIMARY KEY (certid),
FOREIGN KEY (emplid) REFERENCES employee(emplid)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Table structure for table `training`
--

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
PRIMARY KEY (trainid),
FOREIGN KEY (emplid) REFERENCES employee(emplid)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Table structure for table `qualifications`
--

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
PRIMARY KEY (qualid),
FOREIGN KEY (emplid) REFERENCES employee(emplid)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;