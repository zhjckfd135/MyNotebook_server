CREATE DATABASE user_manager;

USE user_manager;

GRANT ALL PRIVILEGES ON user_manager.* TO 'root'@'localhost' IDENTITY BY 'Iopjkl135@';

CREATE TABLE users(
    user_id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP,
    PRIMARY KEY(user_id)
);