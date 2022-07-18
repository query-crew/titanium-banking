package com.titanium.transactions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.titanium.transactions.service.TransactionService;
import com.titanium.transactions.model.Transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;
import java.util.Map;

@SpringBootTest
class TransactionsApplicationTests {

	@Autowired
	private TransactionService transactionService;

	@Test
	void readTransactionTest() {
		ResponseEntity<Map<String, Object>> serviceResponse = transactionService.getTransaction(1);
		assertEquals(serviceResponse.getStatusCode(), HttpStatus.OK);
	}

	@Test
	void readAllTransactionsTest() {
		ResponseEntity<Map<String, Object>> serviceResponse = transactionService.getAllTransactions();
		assertEquals(serviceResponse.getStatusCode(), HttpStatus.OK);
	}

	@Test
	void createTransactionTest() {
		Transaction addedTransaction = new Transaction(
			4,
			1,
			new Timestamp(0),
			"Test Transaction",
			100.00f,
			7,
			8
		);
		ResponseEntity<Map<String, Object>> serviceResponse = transactionService.createTransaction(addedTransaction);
		assertEquals(serviceResponse.getStatusCode(), HttpStatus.CREATED);
	}

	@Test
	void readTransactionByAccountFromIdTest() {
		ResponseEntity<Map<String, Object>> serviceResponse = transactionService.retrieveTransactionByAccountFromId(1);
		assertEquals(serviceResponse.getStatusCode(), HttpStatus.OK);
	}

	@Test
	void readTransactionByAccountToIdTest() {
		ResponseEntity<Map<String, Object>> serviceResponse = transactionService.retrieveTransactionByAccountToId(2);
		assertEquals(serviceResponse.getStatusCode(), HttpStatus.OK);
	}

	@Test
	void readTransactionByTypeTest() {
		ResponseEntity<Map<String, Object>> serviceResponse = transactionService.retrieveTransactionByType(1);
		assertEquals(serviceResponse.getStatusCode(), HttpStatus.OK);
	}

	@Test
	void cannotFindTransactionTest() {
		ResponseEntity<Map<String, Object>> serviceResponse = transactionService.getTransaction(100);
		assertEquals(serviceResponse.getStatusCode(), HttpStatus.NOT_FOUND);
	}
}
