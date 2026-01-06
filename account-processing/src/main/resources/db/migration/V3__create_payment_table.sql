CREATE TABLE payment (
id BIGSERIAL PRIMARY KEY,
account_id BIGINT NOT NULL,
payment_date DATE NOT NULL,
amount DECIMAL(15,2) NOT NULL,
is_credit BOOLEAN NOT NULL,
payed_at TIMESTAMP,
type VARCHAR(20) NOT NULL,
CONSTRAINT fk_payment_account FOREIGN KEY (account_id) REFERENCES account(id)

);

