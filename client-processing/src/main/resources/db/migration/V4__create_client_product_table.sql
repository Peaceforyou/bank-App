CREATE TABLE client_product (
id BIGSERIAL PRIMARY KEY,
client_id VARCHAR(12)  NOT NULL,
product_id BIGINT NOT NULL,
open_date DATE NOT NULL DEFAULT CURRENT_DATE,
close_date DATE,
status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE' CHECK (status IN ('ACTIVE', 'CLOSED', 'BLOCKED', 'ARRESTED')),
CONSTRAINT fk_client_product_client FOREIGN KEY (client_id) REFERENCES client(client_id),
CONSTRAINT fk_client_product_product FOREIGN KEY (product_id) REFERENCES product(id)
);

