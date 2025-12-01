CREATE DATABASE IF NOT EXISTS traveldb;
USE traveldb;

DROP TABLE IF EXISTS attraction;
DROP TABLE IF EXISTS city;

CREATE TABLE city (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    province VARCHAR(100) NOT NULL
);

CREATE TABLE attraction (
    id INT AUTO_INCREMENT PRIMARY KEY,
    city_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    description TEXT,
    image_path VARCHAR(255),
    FOREIGN KEY (city_id) REFERENCES city(id)
);

INSERT INTO city (name, province) VALUES ('Beijing', 'Beijing');
INSERT INTO city (name, province) VALUES ('Shanghai', 'Shanghai');
INSERT INTO city (name, province) VALUES ('Xi''an', 'Shaanxi');

INSERT INTO attraction (city_id, name, price, description, image_path) VALUES 
(1, 'The Great Wall', 40.00, 'A series of fortifications that were built across the historical northern borders of ancient Chinese states.', NULL),
(1, 'Forbidden City', 60.00, 'The former Chinese imperial palace from the Ming dynasty to the end of the Qing dynasty.', NULL),
(2, 'The Bund', 0.00, 'A waterfront area in central Shanghai.', NULL),
(3, 'Terracotta Army', 120.00, 'A collection of terracotta sculptures depicting the armies of Qin Shi Huang.', NULL);
