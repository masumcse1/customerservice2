-- Table creation for Sale
CREATE TABLE Sale (
    id SERIAL PRIMARY KEY,
    route INT NOT NULL,
    totalSaleAmount DECIMAL(10, 2) NOT NULL,
    saleDate DATE NOT NULL
);

