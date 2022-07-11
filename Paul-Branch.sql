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
