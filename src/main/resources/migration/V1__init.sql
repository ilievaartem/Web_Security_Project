-- Flyway V1: initial schema
CREATE TABLE IF NOT EXISTS games (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    platform VARCHAR(50) NOT NULL,
    price DOUBLE NOT NULL,
    seller VARCHAR(255)
);

