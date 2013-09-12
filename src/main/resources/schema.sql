CREATE SCHEMA bonecptest AUTHORIZATION bonecptest;
SET search_path TO bonecptest;
ALTER USER bonecptest SET search_path to bonecptest;

create table account(
  id bigserial NOT NULL,
  balance int NOT NULL,
  PRIMARY KEY (id)
);

GRANT ALL PRIVILEGES ON TABLE bonecptest.account TO bonecptest;
