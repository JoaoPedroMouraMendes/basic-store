CREATE TABLE product (
    id UUID PRIMARY KEY UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    price_in_cents INTEGER NOT NULL,
    department_id UUID,
    FOREIGN KEY (department_id) REFERENCES department(id) ON DELETE CASCADE
);