CREATE TABLE departments(
	depid INT NOT NULL AUTO_INCREMENT,
    IT BOOLEAN NOT NULL,
    Marketing BOOLEAN NOT NULL,
    Finance BOOLEAN NOT NULL,
    HR BOOLEAN NOT NULL,
    PRIMARY KEY (depid),
    FOREIGN KEY (mgrid) REFERENCES manager(mgrid)
);

CREATE TABLE location(
	locid INT NOT NULL AUTO_INCREMENT,
    Minnesota_MN BOOLEAN NOT NULL,
    Charlotte_NC BOOLEAN NOT NULL,
    Tampa_FL BOOLEAN NOT NULL,
    PRIMARY KEY (locid)
);

CREATE TABLE job(
	jobid INT NOT NULL AUTO_INCREMENT,
    net_developer BOOLEAN NOT NULL,
    front_end_developer BOOLEAN NOT NULL,
    hr_expert BOOLEAN NOT NULL,
    marketing BOOLEAN NOT NULL,
    accounting BOOLEAN NOT NULL,
    back_end_developer BOOLEAN NOT NULL,
    PRIMARY KEY(jobid),
    FOREIGN KEY (depid) REFERENCES departments (depid)
);

CREATE TABLE manager(
	mgrid INT NOT NULL AUTO_INCREMENT,
    IT_Manager BOOLEAN NOT NULL,
    Marketing_Manager BOOLEAN NOT NULL,
    Finance_Manager BOOLEAN NOT NULL,
    HR_Manager BOOLEAN NOT NULL,
    PRIMARY KEY (mgrid),
    FOREIGN KEY (depid) REFERENCES departments (depid)
);
