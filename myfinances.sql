CREATE TABLE transactions (
  id NUMBER(10) NOT NULL,
  account_id NUMBER(10) NOT NULL,
  amount NUMBER(20,2) NOT NULL,
  date TIMESTAMP(6) NOT NULL,
  description VARCHAR2(45) NOT NULL,
  category_id NUMBER(10) NOT NULL,
  CONSTRAINT transactions_pk PRIMARY KEY (id),
  CONSTRAINT transactions_fk_account FOREIGN KEY (account_id)
    REFERENCES accounts (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT transactions_fk_category FOREIGN KEY (category_id)
    REFERENCES categories (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE accounts (
  id NUMBER(10) NOT NULL,
  user_id NUMBER(10) NOT NULL,
  name VARCHAR2(45) NOT NULL,
  balance VARCHAR2(45) NOT NULL,
  type VARCHAR2(45) NOT NULL,
  CONSTRAINT accounts_pk PRIMARY KEY (id),
  CONSTRAINT accounts_fk_user FOREIGN KEY (user_id)
    REFERENCES users (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE categories (
  id NUMBER(10) NOT NULL,
  name VARCHAR2(45) NOT NULL,
  CONSTRAINT categories_pk PRIMARY KEY (id)
);

CREATE TABLE users (
  id NUMBER(10) NOT NULL,
  name VARCHAR2(255) NOT NULL,
  email VARCHAR2(255) NOT NULL,
  password VARCHAR2(85) NOT NULL,
  CONSTRAINT users_pk PRIMARY KEY (id),
  CONSTRAINT users_uk_email UNIQUE (email),
  CONSTRAINT users_uk_id UNIQUE (id)
);

