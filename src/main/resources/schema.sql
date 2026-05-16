DROP DATABASE IF EXISTS product_service_db;
CREATE DATABASE product_service_db;
USE product_service_db;

CREATE TABLE IF NOT EXISTS tb_products (
    product_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL UNIQUE,
    description TEXT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    quantity INT NOT NULL,

    created_by VARCHAR(50),
    created_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(50),
    updated_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE INDEX idx_product_name
ON tb_products(name);

CREATE INDEX idx_product_price
ON tb_products(price);