package com.titanium.transactions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.titanium.transactions.service.TransactionService;
import com.titanium.transactions.model.Transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

@SpringBootTest
class TransactionsApplicationTests {

/* 	@Test
	void contextLoads() {
	} */

	@Autowired
	private TransactionService transactionService;

	@Test
	void readTransactionTest() {
		Transaction transaction = transactionService.getTransaction(1);
		assertEquals(transaction.getTransactionId(), 1);
	}

	@Test
	void readAllTransactionsTest() {
		List<Transaction> transactions = transactionService.getAllTransactions();
		assertEquals(transactions.size(), 4);
	}

	@Test
	void createTransactionTest() {
		Transaction transaction = transactionService.getTransaction(1);
		Transaction addedTransaction = new Transaction(4, 1, transaction.getTransactionDate(), transaction.getDescription(), transaction.getAmount(), transaction.getAccountFromId(), transaction.getAccountToId());
		transactionService.createTransaction(addedTransaction);
		Transaction retrievedTransaction = transactionService.getTransaction(4);
		assertEquals(retrievedTransaction.getTransactionId(), 4);
	}

}
