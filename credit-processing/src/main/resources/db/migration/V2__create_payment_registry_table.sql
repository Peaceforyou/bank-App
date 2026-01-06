CREATE TABLE payment_registry (
id BIGSERIAL PRIMARY KEY,
product_registry_id BIGINT  NOT NULL,
payment_date DATE NOT NULL,
amount DECIMAL(15,2) NOT NULL,
interest_rate_amount DECIMAL(15,2) NOT NULL,
debt_amount DECIMAL(15,2) NOT NULL,
expired BOOLEAN NOT NULL DEFAULT FALSE,
payment_expiration_date DATE,
CONSTRAINT fk_product_registry FOREIGN KEY (product_registry_id) REFERENCES product_registry(id)
);

