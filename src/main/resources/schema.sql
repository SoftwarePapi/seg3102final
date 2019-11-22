DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Threads;
DROP TABLE IF EXISTS team_members;
DROP TABLE IF EXISTS Courses;
DROP TABLE IF EXISTS Sections;
DROP TABLE IF EXISTS section_students;
DROP TABLE IF EXISTS Comments;
DROP TABLE IF EXISTS Teams;

CREATE TABLE Users (
    user_id SERIAL,
    account_type varchar NOT NULL,
    first_name varchar NOT NULL,
    last_name varchar NOT NULL,
    indentification_number bigint,
    program varchar,
    email varchar NOT NULL,
    password varchar NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE Teams (
    team_id SERIAL,
    team_name varchar NOT NULL,
    creation_date timestamp,
    captain int NOT NULL,
    status varchar NOT NULL,
    min_capacity int NOT NULL,
    max_capacity int NOT NULL,
    PRIMARY KEY (team_id),
    FOREIGN KEY (captain) REFERENCES Users(user_id)
);

CREATE TABLE team_members (
    id SERIAL,
    team_id int NOT NULL,
    user_id int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (team_id) REFERENCES Teams(team_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

CREATE TABLE Courses (
    course_id SERIAL,
    course_code varchar NOT NULL,
    PRIMARY KEY (course_id)
);

CREATE TABLE Sections (
    section_id SERIAL,
    section_name varchar NOT NULL,
    course_id int,
    professor int,
    PRIMARY KEY (section_id),
    FOREIGN KEY (course_id) REFERENCES Courses(course_id),
    FOREIGN KEY (professor) REFERENCES Users(user_id)
);

CREATE TABLE section_students (
    id SERIAL,
    section_id int NOT NULL,
    user_id int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (section_id) REFERENCES Sections(section_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

CREATE TABLE Threads (
    thread_id SERIAL,
    title varchar,
    body_text varchar,
    author int NOT NULL,
    team_id int NOT NULL,
    PRIMARY KEY (thread_id),
    FOREIGN KEY (author) REFERENCES Users(user_id),
    FOREIGN KEY (team_id) REFERENCES Teams(team_id)
);

CREATE TABLE Comments (
    comment_id SERIAL,
    body_text varchar,
    author int NOT NULL,
    thread_id int NOT NULL,
    PRIMARY KEY (comment_id),
    FOREIGN KEY (author) REFERENCES Users(user_id),
    FOREIGN KEY (thread_id) REFERENCES Threads(thread_id)
);

INSERT INTO USERS (user_id, account_type, first_name, last_name, indentification_number,email, password )  
VALUES (2, 'student', 'Mary', 'Poppins', 987654, 'mary@gmail.com', 'Password123');  
INSERT INTO USERS (user_id, account_type, first_name, last_name, indentification_number,email, password )  
VALUES (3, 'student', 'Mahyar', 'Gorji', 987654, 'mgorj014@uottawa.ca', 'Password123');  
INSERT INTO USERS (user_id, account_type, first_name, last_name, indentification_number,email, password )  
VALUES (4, 'student', 'Parastoo', 'Saharkhiz', 987654, 'psaha091@uottawa.ca', 'Password123');
INSERT INTO USERS (user_id, account_type, first_name, last_name, indentification_number,email, password )  
VALUES (10, 'student', 'Jannah', 'Hossein', 987654, 'jhoss000@uottawa.ca', 'Password123');