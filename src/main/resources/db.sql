CREATE DATABASE IF NOT EXISTS traveldb;
USE traveldb;

DROP TABLE IF EXISTS operation_log;
DROP TABLE IF EXISTS attraction;
DROP TABLE IF EXISTS city;
DROP TABLE IF EXISTS user_account;

CREATE TABLE user_account (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(30) NOT NULL DEFAULT 'admin',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE city (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    province VARCHAR(100) NOT NULL,
    created_by INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (created_by) REFERENCES user_account(id)
);

CREATE TABLE attraction (
    id INT AUTO_INCREMENT PRIMARY KEY,
    city_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    description TEXT,
    image_path VARCHAR(255),
    created_by INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (city_id) REFERENCES city(id),
    FOREIGN KEY (created_by) REFERENCES user_account(id)
);

CREATE TABLE operation_log (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    entity_type VARCHAR(50) NOT NULL,
    entity_id INT,
    action VARCHAR(50) NOT NULL,
    detail VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user_account(id)
);

INSERT INTO user_account (username, password) VALUES ('admin', 'admin');

INSERT INTO city (name, province, created_by) VALUES ('Beijing', 'Beijing', 1);
INSERT INTO city (name, province, created_by) VALUES ('Shanghai', 'Shanghai', 1);
INSERT INTO city (name, province, created_by) VALUES ('Xi''an', 'Shaanxi', 1);

INSERT INTO attraction (city_id, name, price, description, created_by) VALUES 
(1, 'The Great Wall', 40.00, 'A series of fortifications built across the northern borders of ancient Chinese states.', 1),
(1, 'Forbidden City', 60.00, 'Former imperial palace from the Ming dynasty to the end of Qing.', 1),
(2, 'The Bund', 0.00, 'Central Shanghai waterfront area.', 1),
(3, 'Terracotta Army', 120.00, 'Terracotta sculptures depicting Qin Shi Huang''s armies.', 1);
