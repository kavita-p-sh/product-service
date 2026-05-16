USE product_service_db;

INSERT INTO tb_products
(name, description, price, quantity, created_by, updated_by)
SELECT 'Laptop', 'High performance laptop for development and office work', 55000.00, 10, 'ADMIN', 'ADMIN'
WHERE NOT EXISTS (
    SELECT 1 FROM tb_products WHERE name = 'Laptop'
);

INSERT INTO tb_products
(name, description, price, quantity, created_by, updated_by)
SELECT 'Mobile Phone', 'Smartphone with good battery backup and camera quality', 25000.00, 25, 'ADMIN', 'ADMIN'
WHERE NOT EXISTS (
    SELECT 1 FROM tb_products WHERE name = 'Mobile Phone'
);

INSERT INTO tb_products
(name, description, price, quantity, created_by, updated_by)
SELECT 'Headphones', 'Wireless headphones with noise cancellation support', 3000.00, 50, 'ADMIN', 'ADMIN'
WHERE NOT EXISTS (
    SELECT 1 FROM tb_products WHERE name = 'Headphones'
);

INSERT INTO tb_products
(name, description, price, quantity, created_by, updated_by)
SELECT 'Keyboard', 'Mechanical keyboard suitable for programming and gaming', 2000.00, 30, 'ADMIN', 'ADMIN'
WHERE NOT EXISTS (
    SELECT 1 FROM tb_products WHERE name = 'Keyboard'
);

INSERT INTO tb_products
(name, description, price, quantity, created_by, updated_by)
SELECT 'Mouse', 'Wireless mouse with ergonomic design', 800.00, 40, 'ADMIN', 'ADMIN'
WHERE NOT EXISTS (
    SELECT 1 FROM tb_products WHERE name = 'Mouse'
);