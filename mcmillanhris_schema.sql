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
  employee_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(45) NOT NULL,
  last_name VARCHAR(45) NOT NULL,
  address VARCHAR(45) NOT NULL,
  city VARCHAR(45) NOT NULL,
  state VARCHAR(2) NOT NULL,
  zip VARCHAR(10) NOT NULL,
  email VARCHAR(50) NOT NULL,
  phone VARCHAR(15) NOT NULL,
  employment_status VARCHAR(45) NOT NULL,
  PRIMARY KEY  (employee_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Table structure for table `statetax`
--

CREATE TABLE state_tax(
state_tax_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
employee_id INT UNSIGNED NOT NULL,
required BOOLEAN NOT NULL,
rate DOUBLE NOT NULL,
period DATE NOT NULL,
PRIMARY KEY  (state_tax_id),
FOREIGN KEY (employee_id) REFERENCES employee(employee_id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Table structure for table `federaltax`
--

CREATE TABLE federal_tax(
federal_tax_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
employee_id INT UNSIGNED NOT NULL,
bracket VARCHAR(10) NOT NULL,
rate DOUBLE NOT NULL,
period DATE NOT NULL,
PRIMARY KEY  (federal_tax_id),
FOREIGN KEY (employee_id) REFERENCES employee(employee_id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Table structure for table `payroll`
--

CREATE TABLE payroll(
payroll_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
employee_id INT UNSIGNED NOT NULL,
rates DOUBLE NOT NULL,
rates_overtime DOUBLE NOT NULL,
total_hours DOUBLE NOT NULL,
total_overtime DOUBLE NOT NULL,
period DATE NOT NULL,
PRIMARY KEY  (payroll_id),
FOREIGN KEY (employee_id) REFERENCES employee(employee_id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


--
-- Table structure for table `benefits`
--

CREATE TABLE benefit(
benefit_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
employee_id INT UNSIGNED UNIQUE NOT NULL,
healthcare VARCHAR(45) NOT NULL,
dentalcare VARCHAR(45) NOT NULL,
annual_vacation_days INT NOT NULL,
annual_sick_days INT NOT NULL,
pension DOUBLE NOT NULL,
ira VARCHAR(45) NOT NULL,
maternity_leave VARCHAR(45) NOT NULL,
PRIMARY KEY  (benefit_id),
FOREIGN KEY (employee_id) REFERENCES employee(employee_id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table 'location'

CREATE TABLE location(
	location_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    address VARCHAR (50) NOT NULL,
    city VARCHAR (50) NOT NULL,
    state VARCHAR (50) NOT NULL,
    PRIMARY KEY (location_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table 'departments'

CREATE TABLE department(
	department_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    location_id INT UNSIGNED NOT NULL,
    title VARCHAR (45) NOT NULL,
    employee_count INT NOT NULL,
    PRIMARY KEY (department_id),
    FOREIGN KEY (location_id) REFERENCES location(location_id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table 'job'

CREATE TABLE job(
	job_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	department_id INT UNSIGNED NOT NULL,
    employee_id INT UNSIGNED NOT NULL,
    title VARCHAR (50) NOT NULL,
    started DATE NOT NULL,
    ended DATE,
    PRIMARY KEY(job_id),
    FOREIGN KEY (employee_id) REFERENCES employee(employee_id) ON DELETE CASCADE,
    FOREIGN KEY (department_id) REFERENCES department(department_id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table `certifications`

CREATE TABLE certification(
certification_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
employee_id INT UNSIGNED NOT NULL,
certification_type VARCHAR (100) NOT NULL,
expiration DATE,
PRIMARY KEY (certification_id),
FOREIGN KEY (employee_id) REFERENCES employee(employee_id) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table `training`

CREATE TABLE training(
training_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
employee_id INT UNSIGNED NOT NULL,
training_type VARCHAR (100) NOT NULL,
date_completed DATE,
PRIMARY KEY (training_id),
FOREIGN KEY (employee_id) REFERENCES employee(employee_id) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table `qualifications`

CREATE TABLE qualification(
qualification_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
employee_id INT UNSIGNED NOT NULL,
qualification_type VARCHAR (100) NOT NULL,
institution VARCHAR(50) NOT NULL,
year_accomplished DATE,
PRIMARY KEY (qualification_id),
FOREIGN KEY (employee_id) REFERENCES employee(employee_id) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table `applicant tracking`

CREATE TABLE applicant_tracking(
applicant_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
employee_id INT UNSIGNED UNIQUE NOT NULL,
date_applied DATE NOT NULL,
date_hired DATE,
PRIMARY KEY (applicant_id),
FOREIGN KEY (employee_id) REFERENCES employee(employee_id) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table 'application stage'

CREATE TABLE application_stage(
application_stage_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
applicant_id INT UNSIGNED NOT NULL,
application_stage VARCHAR (50) NOT NULL,
started DATE NOT NULL,
ended DATE,
interview_notes VARCHAR (50) NOT NULL,
passed BOOLEAN,
PRIMARY KEY  (application_stage_id),
FOREIGN KEY (applicant_id) REFERENCES applicant_tracking(applicant_id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table 'Evaluation'

CREATE TABLE evaluation(
evaluation_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
employee_id INT UNSIGNED NOT NULL,
communication INT NOT NULL,
job_knowledge INT NOT NULL,
punctuality INT NOT NULL,
dependability INT NULL,
overall INT NOT NULL,
evaluator VARCHAR (50) NOT NULL,
date_written DATE NOT NULL,
comments VARCHAR (50) NOT NULL,
PRIMARY KEY (evaluation_id),
CONSTRAINT FOREIGN KEY (employee_id) REFERENCES employee(employee_id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE VIEW paycheck
AS
SELECT employee.employee_id AS ID, payroll.payroll_id AS payroll,  payroll.period AS period,
(payroll.total_hours * payroll.rates) + (payroll.total_overtime * payroll.rates_overtime) AS gross_pay,
(((payroll.total_hours * payroll.rates) + (payroll.total_overtime * payroll.rates_overtime)) - 
(((payroll.total_hours * payroll.rates) + (payroll.total_overtime * payroll.rates_overtime)) * state_tax.rate / 100) - 
(((payroll.total_hours * payroll.rates) + (payroll.total_overtime * payroll.rates_overtime)) * federal_tax.rate) / 100) AS net_pay
FROM employee
JOIN payroll ON employee.employee_id = payroll.employee_id
JOIN state_tax ON state_tax.employee_id = employee.employee_id AND state_tax.period = payroll.period
JOIN federal_tax ON federal_tax.employee_id = employee.employee_id AND federal_tax.period = payroll.period;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
