DROP TABLE test_tbl_transactions;

CREATE TABLE IF NOT EXISTS test_tbl_transactions(
  TRANSACTION_ID int NOT NULL UNIQUE AUTO_INCREMENT,
  TRANSACTION_TYPE int NOT NULL,
  TRANSACTION_DATE DATETIME NOT NULL,
  TRANSACTION_DESCRIPTION VARCHAR(160),
  TRANSACTION_AMOUNT int NOT NULL,
  ACCOUNT_FROM_ID int NOT NULL,
  ACCOUNT_TO_ID int NOT NULL,
  PRIMARY KEY (TRANSACTION_ID)
);

INSERT INTO test_tbl_transactions(TRANSACTION_TYPE, TRANSACTION_DATE, TRANSACTION_DESCRIPTION, TRANSACTION_AMOUNT, ACCOUNT_FROM_ID, ACCOUNT_TO_ID) VALUES (1, '2022-07-13 00:00:00', 'Amazon Purchase', 10000, 1, 2);
INSERT INTO test_tbl_transactions(TRANSACTION_TYPE, TRANSACTION_DATE, TRANSACTION_DESCRIPTION, TRANSACTION_AMOUNT, ACCOUNT_FROM_ID, ACCOUNT_TO_ID) VALUES (1, '2022-07-13 00:00:00', 'Amazon Purchase', 10000, 3, 4);
INSERT INTO test_tbl_transactions(TRANSACTION_TYPE, TRANSACTION_DATE, TRANSACTION_DESCRIPTION, TRANSACTION_AMOUNT, ACCOUNT_FROM_ID, ACCOUNT_TO_ID) VALUES (1, '2022-07-13 00:00:00', 'Amazon Purchase', 10000, 5, 6);