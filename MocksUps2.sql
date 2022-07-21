SET FOREIGN_KEY_CHECKS=0;

INSERT INTO benefits(emplid, healthcare, dentalcare, annual_sickdays, annual_vacationdays, pension, ira, maternityleave)
VALUES
	(1, "Blue Cross Blue Shield", "Blue Cross Blue Shield",3, 20, 1026.2,"true", "20 paid days, 60 unpaid days"),
	(2, "Blue Cross Blue Shield", "Blue Cross Blue Shield",3, 20, 1026.2,"true", "20 paid days, 60 unpaid days"),
	(3, "Blue Cross Blue Shield", "Blue Cross Blue Shield",3, 20, 1026.2,"true", "20 paid days, 60 unpaid days"),
	(4, "Blue Cross Blue Shield", "Blue Cross Blue Shield",3, 20, 1026.2,"true", "20 paid days, 60 unpaid days"),
    (5, "Blue Cross Blue Shield", "Blue Cross Blue Shield",3, 20, 1026.2,"true", "20 paid days, 60 unpaid days"),
    (6, "Blue Cross Blue Shield", "Blue Cross Blue Shield",3, 20, 1026.2,"true", "20 paid days, 60 unpaid days"),
    (7, "Blue Cross Blue Shield", "Blue Cross Blue Shield",3, 20, 1026.2,"true", "20 paid days, 60 unpaid days"),
    (8, "Blue Cross Blue Shield", "Blue Cross Blue Shield",3, 20, 1026.2,"true", "20 paid days, 60 unpaid days"),
    (9, "Blue Cross Blue Shield", "Blue Cross Blue Shield",3, 20, 1026.2,"true", "20 paid days, 60 unpaid days"),
    (10, "Blue Cross Blue Shield", "Blue Cross Blue Shield",3, 20, 1026.2,"true", "20 paid days, 60 unpaid days"),
    (11, "Blue Cross Blue Shield", "Blue Cross Blue Shield",3, 20, 1026.2,"true", "20 paid days, 60 unpaid days"),
    (12, "Blue Cross Blue Shield", "Blue Cross Blue Shield",3, 20, 1026.2,"true", "20 paid days, 60 unpaid days"),
    (13, "Blue Cross Blue Shield", "Blue Cross Blue Shield",3, 20, 1026.2,"true", "20 paid days, 60 unpaid days"),
    (14, "Blue Cross Blue Shield", "Blue Cross Blue Shield",3, 20, 1026.2,"true", "20 paid days, 60 unpaid days"),
    (15, "Blue Cross Blue Shield", "Blue Cross Blue Shield",3, 20, 1026.2,"true", "20 paid days, 60 unpaid days"),
    (16, "Blue Cross Blue Shield", "Blue Cross Blue Shield",3, 20, 1026.2,"true", "20 paid days, 60 unpaid days"),
    (17, "Blue Cross Blue Shield", "Blue Cross Blue Shield",3, 20, 1026.2,"true", "20 paid days, 60 unpaid days"),
    (18, "Blue Cross Blue Shield", "Blue Cross Blue Shield",3, 20, 1026.2,"true", "20 paid days, 60 unpaid days"),
    (19, "Blue Cross Blue Shield", "Blue Cross Blue Shield",3, 20, 1026.2,"true", "20 paid days, 60 unpaid days"),
    (20, "Blue Cross Blue Shield", "Blue Cross Blue Shield",3, 20, 1026.2,"true", "20 paid days, 60 unpaid days");

INSERT INTO state_tax(emplid, required, rate)
VALUES
	(1, true, 6.8),
    (2, true, 6.0),
    (3, true, 6.0),
    (4, true, 4.7),
    (5, true, 4.7),
    (6, true, 4.7),
    (7, true, 6.8),
    (8, true, 6.8),
    (9, true, 6.0),
    (10, true, 6.8),
    (11, true, 4.7),
    (12, true, 6.0),
    (13, true, 6.0),
    (14, true, 4.7),
    (15, true, 4.7),
    (16, true, 6.8),
    (17, true, 6.8),
    (18, true, 6.0),
    (19, true, 4.7),
    (20, true, 4.7);

INSERT INTO federal_tax(emplid, bracket, rate)
VALUES
	(1, "40320", 12),
	(2, "57600", 22),
    (3, "34560", 12),
    (4, "34560", 12),
    (5, "134400", 24),
    (6, "155200", 24),
    (7, "172800", 32),
    (8, "40320", 12),
    (9, "155200", 24),
    (10, "34560", 12),
    (11, "40320", 12),
    (12, "134400", 24),
    (13, "40320", 12),
    (14, "172800", 32),
    (15, "57600", 22),
    (16, "134400", 24),
    (17, "134400", 24),
    (18, "155200", 24),
    (19, "57600", 22),
    (20, "34560", 12);

INSERT INTO payroll(emplid, rates, rates_overtime, totalhours, totalovertime, grosspay)
VALUES
	(1, 21, 31.5, 40, 0, 40320),
    (2, 30, 45, 40, 0, 56700),
    (3, 18, 27, 40, 0, 34560),
    (4, 18, 27, 40, 0, 34560),
    (5, 70, 105, 40, 0, 134400),
    (6, 60, 90, 40, 0, 155200),
    (7, 90, 135, 40, 0, 172800),
    (8, 21, 31.5, 40, 0, 40320),
    (9, 60, 90, 40, 0, 155200),
    (10, 18, 27, 40, 0, 34560),
    (11, 21, 31.5, 40, 0, 40320),
    (12, 70, 105, 40, 0, 134400),
    (13, 21, 31.5, 40, 0, 40320),
    (14, 90, 135, 40, 0, 172800),
    (15, 30, 45, 40, 0, 57600),
    (16, 70, 105, 40, 0, 134400),
    (17, 70, 105, 40, 0, 134400),
    (18, 60, 90, 40, 0, 155200),
    (19, 30, 45, 40, 0, 57600),
    (20, 18, 27, 40, 0, 34560);

INSERT INTO location(address, city, state)
VALUES
	("7750 Embul Lane", "Minneapolis", "MN"),
    ("99 Unlan Lane", "Tampa", "FL"),
    ("56 Purdue Lane", "Charlotte", "NC");

INSERT INTO departments(locid, title, employeecount)
VALUES
	(1, "Home Base", 30),
    (2, "Tampa", 6),
    (3, "Charlotte", 8);

INSERT INTO job(depid, emplid, title, started, ended)
VALUES
	(1, 1, "accounting", '2020-05-06', '2022-07-21'),
    (2, 2, "marketing", '2020-05-06', '2020-07-21'),
    (2, 3, "hr expert", '2020-05-06', '2022-07-21'),
    (3, 4, "hr expert", '2020-05-06', '2022-07-21'),
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
    (1, 17, "front end developer", '2020-05-06', '2022-07-21'),
    (2, 18, "back end developer", '2020-05-06', '2022-07-21'),
    (3, 19, "marketing", '2020-05-06', '2022-07-21'),
    (3, 20, "hr expert", '2020-05-06', '2022-07-21');
