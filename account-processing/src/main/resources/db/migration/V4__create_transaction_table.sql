CREATE TABLE transactions (
id BIGSERIAL PRIMARY KEY,
account_id BIGSERIAL,
card_id BIGINT,
type VARCHAR(20) NOT NULL,
amount DECIMAL(15, 2) NOT NULL,
status VARCHAR NOT NULL CHECK(status IN ('ALLOWED','PROCESSING','COMPLETE','BLOCKED','CANCELLED')),
time_stamp TIMESTAMP NOT NULL,
CONSTRAINT fk_transaction_account FOREIGN KEY (account_id) REFERENCES account(id),
CONSTRAINT fk_transaction_card FOREIGN KEY (card_id) REFERENCES card(id)
);

