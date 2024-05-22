CREATE DATABASE user_manager;

USE user_manager;

CREATE TABLE users(
    user_id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP,
    PRIMARY KEY(user_id)
);

CREATE TABLE records(
    record_id INT NOT NULL AUTO_INCREMENT,
    data TEXT,
    PRIMARY KEY(record_id)
);

CREATE TABLE user_records_access(
    user_id INT NOT NULL,
    record_id INT NOT NULL,
    PRIMARY KEY(user_id)
);

CREATE TABLE role(
    role_id INT NOT NULL auto_increment,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY(role_id)
);

CREATE TABLE user_role(
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY(user_id)
);
insert into my_notebook.role(name) VALUES("student"); #1
insert into my_notebook.role(name) VALUES("teacher"); #2
SELECT role_id,name from my_notebook.role