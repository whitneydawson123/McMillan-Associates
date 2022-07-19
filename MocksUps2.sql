-- Person 1
SET FOREIGN_KEY_CHECKS=0;

INSERT INTO benefits(emplid, healthcare, dentalcare, annual_sickdays, annual_vacationdays, pension, ira, maternityleave)
VALUES(1, "Blue Cross Blue Shield", "Blue Cross Blue Shield",3, 20, 1026.2,"true", "20 paid days, 60 unpaid days");

INSERT INTO state_tax(emplid, required, rate)
VALUES(1, true, 6.8);

INSERT INTO federal_tax(emplid, bracket, rate)
VALUES(1, "40320", 12);

INSERT INTO payroll(emplid, rates, rates_overtime, totalhours, totalovertime, grosspay)
VALUES(1, 21, 31.5, 40, 0, 40320);

INSERT INTO departments(emplid, IT, Marketing, Finance, HR)
VALUES(1, false, false, true, false);

INSERT INTO manager(emplid, depid, IT_Manager, Marketing_Manager, Finance_Manager, HR_Manager)
VALUES(1, 1, false, false, true, false);

INSERT INTO location(emplid, Minnesota_MN, Charlotte_NC, Tampa_FL)
VALUES(1, true, false, false);

INSERT INTO job(emplid, depid, net_developer, front_end_developer, hr_expert, marketing, accounting, back_end_developer)
VALUES(1, 1, false, false, false, false, true, false);

INSERT INTO applicant_tracking(emplid, app_fname, app_lname, app_email, app_phase1, app_submission, app_submission_date, app_resume, qualid, certid,
 app_phase2, app_interview, app_interview_date, interviewer_notes, app_phase3, app_hired, app_hired_date)
 VALUES(1, "Sarah", "Brown", "sarahbrown@gmail.com", true, true, '2020-03-05', true, 1, 1, true, true, '2020-03-05', "Seems like a good candidate",
 true, true, '2020-04-05');

INSERT INTO certifications(emplid, appid, Cloud_Architect, Cloud_Architect_Experation, Solutions_Architect, Solutions_Architect_Experation,
 Attribution_Testing, Attribution_Testing_Experation, Automation_Artificial, Automation_Artificial_Experation, Financial_Planner, 
 Financial_Planner_Experation, Financial_Consultant, Financial_Consultant_Experation, HRCI_Certification, HRCI_Certification_Experation,
 SHRM_Certification, SHRM_Certification_Experation)
 VALUES(1, 1, false, NULL, false, NULL, false, NULL, false, NULL, true, '2025-09-28', true, '2025-09-28', false, NULL, false, NULL);
 
 INSERT INTO training(emplid, cybersec, cybersecdate, harrassment, harassmentdate, companyculture, companyculturedate, intellectualproperty,
 intellectualpropertydate)
 VALUES(1, false, NULL, true, '2020-03-09', true, '2020-03-09', false, NULL);
 
 INSERT INTO qualifications(emplid, associates_degree, associates_degree_school, associates_degree_date, bachelors_degree, bachelors_degree_school,
 bachelors_degree_date, masters_degree, masters_degree_school, masters_degree_date, experience, language_ability)
 VALUES(1, false, NULL, NULL, true, "Columbia University", '2018-06-09', false, NULL, NULL, 5, true);
 
 INSERT INTO evaluation(emplid, communication, job_knowledge, punctuality, depentability, overall, evaluator, mgrid, datewritten, comment)
 VALUES(1, 5, 6, 10, 10, 9, "They do a good job", 1, '2020-05-06', "Good worker");
