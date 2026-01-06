CREATE TABLE product_registry (
id BIGSERIAL PRIMARY KEY,
client_id VARCHAR(12) NOT NULL,
account_id BIGINT NOT NULL,
product_id BIGINT NOT NULL,
interest_rate DECIMAL(5,2) NOT NULL,
open_date DATE NOT NULL
);

