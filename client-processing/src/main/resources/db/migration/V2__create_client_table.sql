CREATE TABLE client (
id BIGSERIAL  PRIMARY KEY,
client_id VARCHAR(12)  NOT NULL UNIQUE,
user_id BIGINT NOT NULL,
first_name VARCHAR(40) NOT NULL,
middle_name VARCHAR(40) NOT NULL,
last_name VARCHAR(40) NOT NULL,
date_of_birth DATE NOT NULL,
document_type VARCHAR(20)  NOT NULL CHECK ( document_type IN ('PASSPORT', 'INT_PASSPORT', 'BIRTH_CERT')),
document_id VARCHAR(50) NOT NULL,
document_prefix VARCHAR(20),
document_suffix VARCHAR(20),
CONSTRAINT fk_client_user FOREIGN KEY (user_id) REFERENCES users(id)
);

