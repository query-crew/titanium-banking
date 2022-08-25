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

INSERT INTO test_tbl_transactions(TRANSACTION_TYPE, TRANSACTION_DATE, TRANSACTION_DESCRIPTION, TRANSACTION_AMOUNT, ACCOUNT_FROM_ID, ACCOUNT_TO_ID) VALUES (1, '2022-07-12 00:00:00', 'Amazon Purchase', 10000, 1, 2);
INSERT INTO test_tbl_transactions(TRANSACTION_TYPE, TRANSACTION_DATE, TRANSACTION_DESCRIPTION, TRANSACTION_AMOUNT, ACCOUNT_FROM_ID, ACCOUNT_TO_ID) VALUES (1, '2022-07-13 00:00:00', 'Amazon Refund', 10000, 2, 1);
INSERT INTO test_tbl_transactions(TRANSACTION_TYPE, TRANSACTION_DATE, TRANSACTION_DESCRIPTION, TRANSACTION_AMOUNT, ACCOUNT_FROM_ID, ACCOUNT_TO_ID) VALUES (1, '2022-07-15 00:00:00', 'Amazon Purchase', 30000, 1, 2);
INSERT INTO test_tbl_transactions(TRANSACTION_TYPE, TRANSACTION_DATE, TRANSACTION_DESCRIPTION, TRANSACTION_AMOUNT, ACCOUNT_FROM_ID, ACCOUNT_TO_ID) VALUES (1, '2022-07-18 00:00:00', 'Amazon Purchase', 2999, 1, 2);
INSERT INTO test_tbl_transactions(TRANSACTION_TYPE, TRANSACTION_DATE, TRANSACTION_DESCRIPTION, TRANSACTION_AMOUNT, ACCOUNT_FROM_ID, ACCOUNT_TO_ID) VALUES (1, '2022-06-29 00:00:00', 'Amazon Purchase', 1299, 1, 2);
INSERT INTO test_tbl_transactions(TRANSACTION_TYPE, TRANSACTION_DATE, TRANSACTION_DESCRIPTION, TRANSACTION_AMOUNT, ACCOUNT_FROM_ID, ACCOUNT_TO_ID) VALUES (1, '2022-07-13 00:00:00', 'Paypal transfer', 51321, 6, 1);
INSERT INTO test_tbl_transactions(TRANSACTION_TYPE, TRANSACTION_DATE, TRANSACTION_DESCRIPTION, TRANSACTION_AMOUNT, ACCOUNT_FROM_ID, ACCOUNT_TO_ID) VALUES (1, '2022-07-14 00:00:00', 'Store purchase', 14999, 1, 4);
INSERT INTO test_tbl_transactions(TRANSACTION_TYPE, TRANSACTION_DATE, TRANSACTION_DESCRIPTION, TRANSACTION_AMOUNT, ACCOUNT_FROM_ID, ACCOUNT_TO_ID) VALUES (1, '2022-07-15 00:00:00', 'Mechanic fee', 20000, 1, 5);
INSERT INTO test_tbl_transactions(TRANSACTION_TYPE, TRANSACTION_DATE, TRANSACTION_DESCRIPTION, TRANSACTION_AMOUNT, ACCOUNT_FROM_ID, ACCOUNT_TO_ID) VALUES (1, '2022-07-16 00:00:00', 'Walmart Purchase', 24999, 1, 8);
INSERT INTO test_tbl_transactions(TRANSACTION_TYPE, TRANSACTION_DATE, TRANSACTION_DESCRIPTION, TRANSACTION_AMOUNT, ACCOUNT_FROM_ID, ACCOUNT_TO_ID) VALUES (1, '2022-07-12 00:00:00', 'Target Purchase', 19999, 1, 7);
INSERT INTO test_tbl_transactions(TRANSACTION_TYPE, TRANSACTION_DATE, TRANSACTION_DESCRIPTION, TRANSACTION_AMOUNT, ACCOUNT_FROM_ID, ACCOUNT_TO_ID) VALUES (1, '2022-07-11 00:00:00', 'CVS Purchase', 34999, 1, 9);
INSERT INTO test_tbl_transactions(TRANSACTION_TYPE, TRANSACTION_DATE, TRANSACTION_DESCRIPTION, TRANSACTION_AMOUNT, ACCOUNT_FROM_ID, ACCOUNT_TO_ID) VALUES (1, '2022-07-14 00:00:00', 'Amazon Purchase', 34999, 1, 2);
INSERT INTO test_tbl_transactions(TRANSACTION_TYPE, TRANSACTION_DATE, TRANSACTION_DESCRIPTION, TRANSACTION_AMOUNT, ACCOUNT_FROM_ID, ACCOUNT_TO_ID) VALUES (1, '2022-07-19 00:00:00', 'Amazon Purchase', 34999, 1, 2);
INSERT INTO test_tbl_transactions(TRANSACTION_TYPE, TRANSACTION_DATE, TRANSACTION_DESCRIPTION, TRANSACTION_AMOUNT, ACCOUNT_FROM_ID, ACCOUNT_TO_ID) VALUES (1, '2022-07-23 00:00:00', 'Amazon Purchase', 34999, 1, 2);
INSERT INTO test_tbl_transactions(TRANSACTION_TYPE, TRANSACTION_DATE, TRANSACTION_DESCRIPTION, TRANSACTION_AMOUNT, ACCOUNT_FROM_ID, ACCOUNT_TO_ID) VALUES (1, '2022-07-26 00:00:00', 'Amazon Purchase', 34999, 1, 2);
INSERT INTO test_tbl_transactions(TRANSACTION_TYPE, TRANSACTION_DATE, TRANSACTION_DESCRIPTION, TRANSACTION_AMOUNT, ACCOUNT_FROM_ID, ACCOUNT_TO_ID) VALUES (1, '2022-07-13 00:00:00', 'Amazon Purchase', 10000, 3, 4);
INSERT INTO test_tbl_transactions(TRANSACTION_TYPE, TRANSACTION_DATE, TRANSACTION_DESCRIPTION, TRANSACTION_AMOUNT, ACCOUNT_FROM_ID, ACCOUNT_TO_ID) VALUES (1, '2022-07-13 00:00:00', 'Amazon Purchase', 10000, 5, 6);
INSERT INTO test_tbl_transactions(TRANSACTION_TYPE, TRANSACTION_DATE, TRANSACTION_DESCRIPTION, TRANSACTION_AMOUNT, ACCOUNT_FROM_ID, ACCOUNT_TO_ID) VALUES (2, '2022-07-13 00:00:00', 'Other type', 10000, 5, 6);