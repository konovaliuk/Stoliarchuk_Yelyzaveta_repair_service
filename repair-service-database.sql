-- create database for the repair service
DROP DATABASE IF EXISTS `repair_service`;
CREATE DATABASE `repair_service`;
USE `repair_service`;

-- create tables for database
DROP TABLE IF EXISTS roles;
CREATE TABLE roles (
    `role_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    CONSTRAINT pk_roles PRIMARY KEY (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS users;
CREATE TABLE users (
    `user_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `role_id` BIGINT UNSIGNED NOT NULL DEFAULT '1',
    `email` VARCHAR(50) NOT NULL, 
    `username` VARCHAR(50) NOT NULL, 
    `password` VARCHAR(50) NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (user_id),
    CONSTRAINT fk_users_roles FOREIGN KEY (role_id) REFERENCES roles (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS statuses;
CREATE TABLE statuses (
    `status_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    CONSTRAINT pk_statuses PRIMARY KEY (status_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS requests;
CREATE TABLE requests (
    `request_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_id` BIGINT UNSIGNED NOT NULL,
    `status_id` BIGINT UNSIGNED NOT NULL DEFAULT '1',
    `request_description` TEXT NOT NULL,
    `product_name` VARCHAR(50) NOT NULL, 
    `product_model` VARCHAR(50) NOT NULL, 
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `repair_cost` INT UNSIGNED,
    `declination_reason` VARCHAR(255),
    CONSTRAINT pk_requests PRIMARY KEY (request_id),
    CONSTRAINT fk_requests_users FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT fk_requests_statuses FOREIGN KEY (status_id) REFERENCES statuses (status_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS feedbacks;
CREATE TABLE feedbacks (
    `feedback_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `request_id` BIGINT UNSIGNED NOT NULL,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `rating` TINYINT NOT NULL,
    `feedback_description` TEXT NOT NULL,
    CONSTRAINT pk_feedbacks PRIMARY KEY (feedback_id),
    CONSTRAINT fk_feedbacks_requests FOREIGN KEY (request_id) REFERENCES requests (request_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- fill tables with sample data
INSERT INTO roles (name) 
VALUES ('user'), 
       ('manager'),
       ('master');

INSERT INTO users (role_id, username, email, password) 
VALUES (1, 'john_smith', 'john.smith@gmail.com', 'password'),
       (1, 'emma_swan', 'emma.swan@gmail.com', 'password'),        
       (2, 'sara_johnson', 'sara.johnson@gmail.com', 'password'),        
       (3, 'david_lee', 'david.lee@gmail.com', 'password');

INSERT INTO statuses (name) 
VALUES ('pending'),
       ('accepted'),        
       ('rejected'),        
       ('completed');

INSERT INTO requests (user_id, request_description, product_name, product_model)
VALUES (1, 'My laptop is not turning on.', 'laptop', 'MacBook Pro 2023'),
       (1, 'My phone screen is cracked.', 'phone', 'iPhone 20 Pro Max'),        
       (2, 'My printer is not printing.', 'printer', 'Xiaomi PT2023'),        
       (2, 'My TV has no sound.', 'TV', 'Xiaomi TV AA2023');

UPDATE requests     
    SET status_id = 2, repair_cost = 12000     
    WHERE request_id = 2;

UPDATE requests     
    SET status_id = 4, repair_cost = 5000     
    WHERE request_id = 3;

UPDATE requests     
    SET status_id = 3, declination_reason = 'No masters with needed qualification.'     
    WHERE request_id = 4;

INSERT INTO feedbacks (request_id, rating, feedback_description)
VALUES (3, 5, 'Very good work!');