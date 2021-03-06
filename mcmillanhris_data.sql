SET FOREIGN_KEY_CHECKS=1;

INSERT INTO mcmillanhris.employee(first_name, last_name, address, city, state, zip, email, phone, employment_status)
VALUES
('Sarah', 'Brown', '77 High Street', 'Minneapolis', 'MN', '56792', 'sarahbrown@gmail.com', '123-456-7890', 'intern'),
('Penny', 'Smart', '99 12th Street', 'Tampa', 'FL', '56792', 'pennysmart@gmail.com', '234-567-8901', 'intern'),
('Andrew', 'Nimble', '776 Willow Street', 'Tampa', 'FL', '56793', 'andrewnimble@gmail.com', '345-678-9012', 'full time'),
('Terry', 'Gatlin', '555 Main Street', 'Charlotte', 'NC', '32978', 'terrygatlin@gmail.com', '456-789-0123', 'full time'),
('Martin', 'Finefield', '42 N Southeast Street', 'Charlotte', 'NC', '32975', 'martinfinefield@gmail.com', '567-890-1234', 'part time'),
('Rueben', 'Flack', '36 N Cumberland Street', 'Charlotte', 'NC', '32978', 'ruebenflack@gmail.com', '678-901-2345', 'part time'),
('Curtis', 'Stack', '47 Market Street', 'Minneapolis', 'MN', '47965', 'curtisstack@gmail.com', '789-012-3456', 'trainee full time'),
('Conor', 'Phalegn', '35 Maple Street', 'Minneapolis', 'MN', '47966', 'conorphalegan@gmail.com', '890-123-4567', 'trainee full time'),
('Paul', 'Gray', '88 Pine Street', 'Tampa', 'FL', '56794', 'paulgray@gmail.com', '901-234-5678', 'trainee part time'),
('Richard', 'Robinson', '96 Jefferson Street', 'Minneapolis', 'MN', '47966', 'richardrobinson@gmail.com', '012-345-6789', 'intern'),
('Adam', 'Huxley', '87 Summit Ave', 'Charlotte', 'NC', '32986', 'adamhuxley@gmail.com', '098-765-4321', 'maternity leave'),
('Adam', 'Reign', '110 Liberty Ave', 'Tampa', 'FL', '56794', 'adamreign@gmail.com', '438-898-8580', 'full time'),
('Ryan', 'Huntsman', '120 Liberty Ave', 'Tampa', 'FL', '56794', 'ryanhuntsman@gmail.com', '597-285-9653', 'part time'),
('Ruth', 'Chestfield', '150 Jeffersonville Ave', 'Charlotte', 'NC', '32986', 'ruthchestfield@gmail.com', '380-570-6840', 'maternity leave'),
('Abagail', 'West', '18 Chestnut Ave', 'Charlotte', 'NC', '32984', 'abagailwest@gmail.com', '475-858-3685', 'full time'),
('Hope', 'Lansidy', '15 Green Street', 'Minneapolis', 'MN', '47966', 'hopelansidy@gmail.com', '483-975-7359', 'suspended'),
('Hazel', 'Wright', '145 Green Street', 'Minneapolis', 'MN', '47966', 'hazelwright@gmail.com', '468-720-8508', 'full time'),
('Ava', 'Smith', '256 Broad Street', 'Tampa', 'FL', '56794', 'avasmith@gmail.com', '486-839-5729', 'full time'),
('Harper', 'Langley', '395 Pearl Street', 'Charlotte', 'NC', '32984', 'harperlangley@gmail.com', '497-868-8357', 'fired'),
('Chloe', 'Hansley', '567 Mill Street', 'Charlotte', 'NC', '32984', 'chloehansley@gmail.com', '597-808-9749', 'fired');

INSERT INTO benefit(employee_id, healthcare, dentalcare, annual_sick_days, annual_vacation_days, pension, ira, maternity_leave)
VALUES
	(1, "not covered", "not covered",3, 0, 0,"false", "20 paid days, 60 unpaid days"),
	(2, "not covered", "not covered",2, 0, 0,"false", "20 paid days, 60 unpaid days"),
	(3, "Blue Cross Blue Shield", "Blue Cross Blue Shield",3, 7, 1026.2,"true", "20 paid days, 60 unpaid days"),
	(4, "Blue Cross Blue Shield", "Blue Cross Blue Shield",3, 12, 1026.2,"true", "20 paid days, 60 unpaid days"),
    (5, "Blue Cross Blue Shield", "Blue Cross Blue Shield",1, 2, 1026.2,"true", "20 paid days, 60 unpaid days"),
    (6, "Blue Cross Blue Shield", "Blue Cross Blue Shield",3, 4, 1026.2,"true", "20 paid days, 60 unpaid days"),
    (7, "Blue Cross Blue Shield", "Blue Cross Blue Shield",3, 20, 1026.2,"true", "20 paid days, 60 unpaid days"),
    (8, "Blue Cross Blue Shield", "Blue Cross Blue Shield",2, 20, 1026.2,"true", "20 paid days, 60 unpaid days"),
    (9, "Blue Cross Blue Shield", "Blue Cross Blue Shield",1, 20, 1026.2,"true", "20 paid days, 60 unpaid days"),
    (10, "not covered", "not covered",1, 0, 1026.2,"false", "20 paid days, 60 unpaid days"),
    (11, "Blue Cross Blue Shield", "Blue Cross Blue Shield",3, 20, 1026.2,"true", "0 paid days, 21 unpaid days"),
    (12, "Blue Cross Blue Shield", "Blue Cross Blue Shield",2, 18, 1026.2,"true", "20 paid days, 60 unpaid days"),
    (13, "Blue Cross Blue Shield", "Blue Cross Blue Shield",1, 7, 1026.2,"true", "20 paid days, 60 unpaid days"),
    (14, "Blue Cross Blue Shield", "Blue Cross Blue Shield",3, 20, 1026.2,"true", "7 paid days, 60 unpaid days"),
    (15, "Blue Cross Blue Shield", "Blue Cross Blue Shield",0, 13, 1026.2,"true", "20 paid days, 60 unpaid days"),
    (16, "Blue Cross Blue Shield", "Blue Cross Blue Shield",0, 1, 1026.2,"true", "20 paid days, 60 unpaid days"),
    (17, "Blue Cross Blue Shield", "Blue Cross Blue Shield",3, 10, 1026.2,"true", "20 paid days, 60 unpaid days"),
    (18, "Blue Cross Blue Shield", "Blue Cross Blue Shield",2, 2, 1026.2,"true", "20 paid days, 60 unpaid days"),
    (19, "none", "none",1, 0, 0,"false", "0 paid days, 0 unpaid days"),
    (20, "none", "none",0, 0, 0,"false", "0 paid days, 0 unpaid days");

INSERT INTO state_tax(employee_id, required, rate, period)
VALUES
	(1, false, 0, '2022-07-01'),
    (2, false, 0, '2022-07-01'),
    (3, true, 6.0, '2022-07-01'),
    (4, true, 4.7, '2022-07-01'),
    (5, true, 4.7, '2022-07-01'),
    (6, true, 4.7, '2022-07-01'),
    (7, true, 6.8, '2022-07-01'),
    (8, true, 6.8, '2022-07-01'),
    (9, true, 6.0, '2022-07-01'),
    (10, false, 0, '2022-07-01'),
    (11, true, 4.7, '2022-07-01'),
    (12, true, 6.0, '2022-07-01'),
    (13, true, 6.0, '2022-07-01'),
    (14, true, 4.7, '2022-07-01'),
    (15, true, 4.7, '2022-07-01'),
    (16, true, 6.8, '2022-07-01'),
    (17, true, 6.8, '2022-07-01'),
    (18, true, 6.0, '2022-07-01'),
    (19, false, 0, '2022-07-01'),
    (20, false, 0, '2022-07-01');

INSERT INTO federal_tax(employee_id, bracket, rate, period)
VALUES
	(1, "Single", 3.1, '2022-07-01'),
	(2, "Married", 2.2, '2022-07-01'),
    (3, "Head", 9.5, '2022-07-01'),
    (4, "Married", 2.1, '2022-07-01'),
    (5, "Married", 5.5, '2022-07-01'),
    (6, "Head", 7.8, '2022-07-01'),
    (7, "Married", 9.2, '2022-07-01'),
    (8, "Married", 1.7, '2022-07-01'),
    (9, "Single", 2.0, '2022-07-01'),
    (10, "Married", 0, '2022-07-01'),
    (11, "Single", 7.9, '2022-07-01'),
    (12, "Married", 5.7, '2022-07-01'),
    (13, "Single", 3.4, '2022-07-01'),
    (14, "Married", 7.2, '2022-07-01'),
    (15, "Married", 5.1, '2022-07-01'),
    (16, "Head", 3.3, '2022-07-01'),
    (17, "Married", 9.0, '2022-07-01'),
    (18, "Head", 9.1, '2022-07-01'),
    (19, "Married", 0.9, '2022-07-01'),
    (20, "Head", 1.9, '2022-07-01');


INSERT INTO payroll(employee_id, rates, rates_overtime, total_hours, total_overtime, period)
VALUES
	(1, 0, 0, 0, 0, '2022-07-01'),
    (2, 0, 0, 0, 0, '2022-07-01'),
    (3, 50, 27, 40, 134400, '2022-07-01'),
    (4, 90, 27, 40, 172800, '2022-07-01'),
    (5, 15, 105, 40, 34560, '2022-07-01'),
    (6, 15, 90, 40, 34560, '2022-07-01'),
    (7, 30, 135, 40, 57600, '2022-07-01'),
    (8, 30, 31.5, 40, 57600, '2022-07-01'),
    (9, 20, 90, 40, 40320, '2022-07-01'),
    (10, 0, 0, 0, 0, '2022-07-01'),
    (11, 70, 31.5, 40, 155200, '2022-07-01'),
    (12, 50, 105, 40, 134400, '2022-07-01'),
    (13, 30, 31.5, 40, 57600, '2022-07-01'),
    (14, 70, 135, 40, 155200, '2022-07-01'),
    (15, 50, 45, 40, 134400, '2022-07-01'),
    (16, 30, 105, 40, 57600, '2022-07-01'),
    (17, 90, 105, 40, 172800, '2022-07-01'),
    (18, 90, 90, 40, 172800, '2022-07-01'),
    (19, 0, 0, 0, 0, '2022-07-01'),
    (20, 0, 0, 0, 0, '2022-07-01');

INSERT INTO location(address, city, state)
VALUES
	("7750 Embul Lane", "Minneapolis", "MN"),
    ("99 Unlan Lane", "Tampa", "FL"),
    ("56 Purdue Lane", "Charlotte", "NC");

INSERT INTO department(location_id, title, employee_count)
VALUES
	(1, "Home Base", 8),
    (2, "Tampa", 6),
    (3, "Charlotte", 6);

INSERT INTO job(department_id, employee_id, title, started, ended)
VALUES
	(1, 1, "accounting", '2020-05-06', '2022-07-21'),
    (2, 2, "marketing", '2020-05-06', '2020-07-21'),
    (2, 3, "hr expert", '2020-05-06', '2022-07-21'),
    (3, 4, "manager", '2020-05-06', '2022-07-21'),
    (3, 5, "front end developer", '2020-05-05', '2022-07-21'),
    (3, 6, "back end developer", '2020-05-06', '2022-07-21'),
    (1, 7, ".net developer", '2020-05-06', '2022-07-21'),
    (1, 8, "accounting", '2020-05-06', '2022-07-21'),
    (2, 9, "back end developer", '2020-05-06', '2022-07-21'),
    (1, 10, "hr expert", '2020-05-06', '2022-07-21'),
    (3, 11, "accounting", '2020-05-06', '2022-07-21'),
    (2, 12, "front end developer", '2020-05-06', '2022-07-21'),
    (2, 13, "accounting", '2020-05-06', '2022-07-21'),
    (3, 14, ".net developer", '2020-05-06', '2022-07-21'),
    (3, 15, "marketing", '2020-05-06', '2022-07-21'),
    (1, 16, "front end developer", '2020-05-06', '2022-07-21'),
    (1, 17, "manager", '2020-05-06', '2022-07-21'),
    (2, 18, "manager", '2020-05-06', '2022-07-21'),
    (3, 19, "fired", '2020-05-06', '2022-07-21'),
    (3, 20, "fired", '2020-05-06', '2022-07-21');

INSERT INTO certification(employee_id, certification_type, expiration)
VALUES
	(1, "ITIL Certification", '2029-09-12'),
    (2, "ITIL Certification", '2029-09-12'),
    (3, "ITIL Certification", '2029-09-12'),
    (4, "ITIL Certification", '2029-09-12'),
    (5, "ITIL Certification", '2029-09-12'),
    (6, "ITIL Certification", '2029-09-12'),
    (7, "ITIL Certification", '2029-09-12'),
    (8, "ITIL Certification", '2029-09-12'),
    (9, "ITIL Certification", '2029-09-12'),
    (10, "ITIL Certification", '2029-09-12'),
    (11, "ITIL Certification", '2029-09-12'),
    (12, "ITIL Certification", '2029-09-12'),
    (13, "ITIL Certification", '2029-09-12'),
    (14, "ITIL Certification", '2029-09-12'),
    (15, "ITIL Certification", '2029-09-12'),
    (16, "ITIL Certification", '2029-09-12'),
    (17, "ITIL Certification", '2029-09-12'),
    (18, "ITIL Certification", '2029-09-12'),
    (19, "ITIL Certification", '2029-09-12'),
    (20, "ITIL Certification", '2029-09-12');
    
INSERT INTO training(employee_id, training_type, date_completed)
VALUES
	(1, "harrassment training", '2021-06-08'),
    (2, "harrassment training", '2021-06-08'),
    (3, "harrassment training", '2021-06-08'),
    (4, "harrassment training", '2021-06-08'),
    (5, "harrassment training", '2021-06-08'),
    (6, "harrassment training", '2021-06-08'),
    (7, "harrassment training", '2021-06-08'),
    (8, "harrassment training", '2021-06-08'),
    (9, "harrassment training", '2021-06-08'),
    (10, "harrassment training", '2021-06-08'),
    (11, "harrassment training", '2021-06-08'),
    (12, "harrassment training", '2021-06-08'),
    (13, "harrassment training", '2021-06-08'),
    (14, "harrassment training", '2021-06-08'),
    (15, "harrassment training", '2021-06-08'),
    (16, "harrassment training", '2021-06-08'),
    (17, "harrassment training", '2021-06-08'),
    (18, "harrassment training", '2021-06-08'),
    (19, "harrassment training", '2021-06-08'),
    (20, "harrassment training", '2021-06-08');
    
INSERT INTO qualification(employee_id, qualification_type, institution, year_accomplished)
VALUES 
	(1, "bachelor's of science", "Cambridge University", '2019-06-07'),
    (2, "bachelor's of science", "Cambridge University", '2019-06-07'),
    (3, "bachelor's of science", "Cambridge University", '2019-06-07'),
    (4, "bachelor's of science", "Cambridge University", '2019-06-07'),
    (5, "bachelor's of science", "Cambridge University", '2019-06-07'),
    (6, "bachelor's of science", "Cambridge University", '2019-06-07'),
    (7, "bachelor's of science", "Cambridge University", '2019-06-07'),
    (8, "bachelor's of science", "Cambridge University", '2019-06-07'),
    (9, "bachelor's of science", "Cambridge University", '2019-06-07'),
    (10, "bachelor's of science", "Cambridge University", '2019-06-07'),
    (11, "bachelor's of science", "Cambridge University", '2019-06-07'),
    (12, "bachelor's of science", "Cambridge University", '2019-06-07'),
    (13, "bachelor's of science", "Cambridge University", '2019-06-07'),
    (14, "bachelor's of science", "Cambridge University", '2019-06-07'),
    (15, "bachelor's of science", "Cambridge University", '2019-06-07'),
    (16, "bachelor's of science", "Cambridge University", '2019-06-07'),
    (17, "bachelor's of science", "Cambridge University", '2019-06-07'),
    (18, "bachelor's of science", "Cambridge University", '2019-06-07'),
    (19, "bachelor's of science", "Cambridge University", '2019-06-07'),
    (20, "bachelor's of science", "Cambridge University", '2019-06-07');
    
INSERT INTO applicant_tracking(employee_id, date_applied, date_hired)
VALUES
	(1, '2020-05-01', '2020-07-01'),
    (2, '2020-05-02', '2020-07-02'),
    (3, '2020-05-03', '2020-07-03'),
    (4, '2020-05-04', '2020-07-04'),
    (5, '2020-05-05', '2020-07-05'),
    (6, '2020-05-06', '2020-07-06'),
    (7, '2020-05-07', '2020-07-07'),
    (8, '2020-05-08', '2020-07-08'),
    (9, '2020-05-09', '2020-07-09'),
    (10, '2020-05-10', '2020-07-10'),
    (11, '2020-05-11', '2020-07-11'),
    (12, '2020-05-12', '2020-07-12'),
    (13, '2020-05-13', '2020-07-13'),
    (14, '2020-05-14', '2020-07-14'),
    (15, '2020-05-15', '2020-07-15'),
    (16, '2020-05-16', '2020-07-16'),
    (17, '2020-05-17', '2020-07-17'),
    (18, '2020-05-18', '2020-07-18'),
    (19, '2020-05-19', '2020-07-19'),
    (20, '2020-05-20', '2020-07-20');
   
INSERT INTO application_stage(applicant_id, application_stage, started, ended, interview_notes, passed)
VALUES
	(1, "hired", '2020-05-07', '2020-07-05', "They had very good communication skills", true),
    (2, "hired", '2020-05-07', '2020-07-05', "They had very good communication skills", true),
    (3, "hired", '2020-05-07', '2020-07-05', "They had very good communication skills", true),
    (4, "hired", '2020-05-07', '2020-07-05', "They had very good communication skills", true),
    (5, "hired", '2020-05-07', '2020-07-05', "They had very good communication skills", true),
    (6, "hired", '2020-05-07', '2020-07-05', "They had very good communication skills", true),
    (7, "hired", '2020-05-07', '2020-07-05', "They had very good communication skills", true),
    (8, "hired", '2020-05-07', '2020-07-05', "They had very good communication skills", true),
    (9, "hired", '2020-05-07', '2020-07-05', "They had very good communication skills", true),
    (10, "hired", '2020-05-07', '2020-07-05', "They had very good communication skills", true),
    (11, "hired", '2020-05-07', '2020-07-05', "They had very good communication skills", true),
    (12, "hired", '2020-05-07', '2020-07-05', "They had very good communication skills", true),
    (13, "hired", '2020-05-07', '2020-07-05', "They had very good communication skills", true),
    (14, "hired", '2020-05-07', '2020-07-05', "They had very good communication skills", true),
    (15, "hired", '2020-05-07', '2020-07-05', "They had very good communication skills", true),
    (16, "hired", '2020-05-07', '2020-07-05', "They had very good communication skills", true),
    (17, "hired", '2020-05-07', '2020-07-05', "They had very good communication skills", true),
    (18, "hired", '2020-05-07', '2020-07-05', "They had very good communication skills", true),
    (19, "hired", '2020-05-07', '2020-07-05', "They had very good communication skills", true),
    (20, "hired", '2020-05-07', '2020-07-05', "They had very good communication skills", true);

INSERT INTO evaluation(employee_id, communication, job_knowledge, punctuality, dependability, overall, evaluator, date_written, comments)
VALUES
	(1, 10, 7, 8, 2, 14, "Sam Higgins", '2020-06-05', "Amazing Candidate"),
    (2, 10, 7, 8, 2, 14, "Sam Higgins", '2020-06-05', "Amazing Candidate"),
    (3, 10, 7, 8, 2, 14, "Sam Higgins", '2020-06-05', "Amazing Candidate"),
    (4, 10, 7, 8, 2, 14, "Sam Higgins", '2020-06-05', "Amazing Candidate"),
    (5, 10, 7, 8, 2, 14, "Sam Higgins", '2020-06-05', "Amazing Candidate"),
    (6, 10, 7, 8, 2, 14, "Sam Higgins", '2020-06-05', "Amazing Candidate"),
    (7, 10, 7, 8, 2, 14, "Sam Higgins", '2020-06-05', "Amazing Candidate"),
    (8, 10, 7, 8, 2, 14, "Sam Higgins", '2020-06-05', "Amazing Candidate"),
    (9, 10, 7, 8, 2, 14, "Sam Higgins", '2020-06-05', "Amazing Candidate"),
    (10, 10, 7, 8, 2, 14, "Sam Higgins", '2020-06-05', "Amazing Candidate"),
    (11, 10, 7, 8, 2, 14, "Sam Higgins", '2020-06-05', "Amazing Candidate"),
    (12, 10, 7, 8, 2, 14, "Sam Higgins", '2020-06-05', "Amazing Candidate"),
    (13, 10, 7, 8, 2, 14, "Sam Higgins", '2020-06-05', "Amazing Candidate"),
    (14, 10, 7, 8, 2, 14, "Sam Higgins", '2020-06-05', "Amazing Candidate"),
    (15, 10, 7, 8, 2, 14, "Sam Higgins", '2020-06-05', "Amazing Candidate"),
    (16, 10, 7, 8, 2, 14, "Sam Higgins", '2020-06-05', "Amazing Candidate"),
    (17, 10, 7, 8, 2, 14, "Sam Higgins", '2020-06-05', "Amazing Candidate"),
    (18, 10, 7, 8, 2, 14, "Sam Higgins", '2020-06-05', "Amazing Candidate"),
    (19, 10, 7, 8, 2, 14, "Sam Higgins", '2020-06-05', "Amazing Candidate"),
    (20, 10, 7, 8, 2, 14, "Sam Higgins", '2020-06-05', "Amazing Candidate");
