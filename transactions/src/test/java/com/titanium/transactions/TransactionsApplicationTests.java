package com.titanium.transactions;

import com.titanium.transactions.exception.InvalidSortPropException;
import com.titanium.transactions.exception.TransactionNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.titanium.transactions.service.TransactionService;
import com.titanium.transactions.model.Transaction;

import java.sql.Timestamp;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransactionsApplicationTests {

	@Autowired
	private TransactionService transactionService;

	@BeforeEach
	@AfterEach
	void deleteAllTransactions() {
		transactionService.removeAllTransactions();
	}
	@Test
	void readTransactionTest() {
		Transaction addedTransaction1 = new Transaction(
				1,
				new Timestamp(0),
				"Test Transaction",
				10000,
				49,
				50
		);
		Transaction addedTransaction2 = new Transaction(
				1,
				new Timestamp(0),
				"Test Transaction2",
				10000,
				50,
				49
		);
		transactionService.createTransaction(addedTransaction1);
		transactionService.createTransaction(addedTransaction2);
		assertDoesNotThrow(() -> {
			transactionService.getTransaction(addedTransaction1.getTransactionId());
		});
	}

	@Test
	void readAllTransactionsTest() {
		Transaction addedTransaction1 = new Transaction(
				1,
				new Timestamp(0),
				"Test Transaction",
				10000,
				49,
				50
		);
		Transaction addedTransaction2 = new Transaction(
				1,
				new Timestamp(0),
				"Test Transaction2",
				10000,
				50,
				49
		);
		transactionService.createTransaction(addedTransaction1);
		transactionService.createTransaction(addedTransaction2);
		Map<String, Object> serviceResponse = transactionService.getAllTransactions(0 , 5);
		List<Transaction> list = new ArrayList<>((Collection<Transaction>) serviceResponse.get("transactions"));
		assertNotEquals(0, list.size());
	}

	@Test
	void createTransactionTest() {
		Transaction addedTransaction = new Transaction(
			1,
			new Timestamp(0),
			"Test Transaction",
			10000,
			7,
			8
		);
		transactionService.createTransaction(addedTransaction);
		assertDoesNotThrow(() -> {
			transactionService.getTransaction(addedTransaction.getTransactionId());
		});
	}

	@Test
	void readTransactionByAccountFromIdTest() {
		Transaction addedTransaction1 = new Transaction(
				1,
				new Timestamp(0),
				"Test Transaction",
				10000,
				49,
				50
		);
		Transaction addedTransaction2 = new Transaction(
				1,
				new Timestamp(0),
				"Test Transaction2",
				10000,
				50,
				49
		);
		transactionService.createTransaction(addedTransaction1);
		transactionService.createTransaction(addedTransaction2);
		Map<String, Object> serviceResponse = transactionService.retrieveTransactionByAccountFromId(49, "", "",0 , 5);
		List<Transaction> list = new ArrayList<>((Collection<Transaction>) serviceResponse.get("transactions"));
		assertNotEquals(0, list.size());
	}

	@Test
	void readTransactionWithInvalidAccountFromIdTest() {
		Map<String, Object> serviceResponse = transactionService.retrieveTransactionByAccountFromId(-1, "", "",0 , 5);
		List<Transaction> list = new ArrayList<>((Collection<Transaction>) serviceResponse.get("transactions"));
		assertEquals(0, list.size());
	}

	@Test
	void readTransactionByAccountToIdTest() {
		Transaction addedTransaction1 = new Transaction(
				1,
				new Timestamp(0),
				"Test Transaction",
				10000,
				49,
				50
		);
		Transaction addedTransaction2 = new Transaction(
				1,
				new Timestamp(0),
				"Test Transaction2",
				10000,
				50,
				49
		);
		transactionService.createTransaction(addedTransaction1);
		transactionService.createTransaction(addedTransaction2);
		Map<String, Object> serviceResponse = transactionService.retrieveTransactionByAccountToId(49, "", "", 0 , 5);
		List<Transaction> list = new ArrayList<>((Collection<Transaction>) serviceResponse.get("transactions"));
		assertNotEquals(0, list.size());
	}

	@Test
	void readTransactionWithInvalidAccountToIdTest() {
		Map<String, Object> serviceResponse = transactionService.retrieveTransactionByAccountToId(-1, "", "", 0 , 5);
		List<Transaction> list = new ArrayList<>((Collection<Transaction>) serviceResponse.get("transactions"));
		assertEquals(0, list.size());
	}

	@Test
	void readTransactionByTypeTest() {
		Transaction addedTransaction1 = new Transaction(
				1,
				new Timestamp(0),
				"Test Transaction",
				10000,
				49,
				50
		);
		Transaction addedTransaction2 = new Transaction(
				1,
				new Timestamp(0),
				"Test Transaction2",
				10000,
				50,
				49
		);
		transactionService.createTransaction(addedTransaction1);
		transactionService.createTransaction(addedTransaction2);

		Map<String, Object> serviceResponse = transactionService.retrieveTransactionByType(1, 0 , 5);
		List<Transaction> list = new ArrayList<>((Collection<Transaction>) serviceResponse.get("transactions"));
		assertNotEquals(0, list.size());
	}

	@Test
	void readTransactionWithInvalidTypeTest() {
		Map<String, Object> serviceResponse = transactionService.retrieveTransactionByType(-1, 0 , 5);
		List<Transaction> list = new ArrayList<>((Collection<Transaction>) serviceResponse.get("transactions"));
		assertEquals(0, list.size());
	}

	@Test
	void readTransactionByDateTest() {
		Timestamp time = Timestamp.valueOf("2022-07-13 00:00:00");
		Transaction addedTransaction1 = new Transaction(
				1,
				time,
				"Test Transaction",
				10000,
				49,
				50
		);
		Transaction addedTransaction2 = new Transaction(
				1,
				new Timestamp(0),
				"Test Transaction2",
				10000,
				50,
				49
		);
		transactionService.createTransaction(addedTransaction1);
		transactionService.createTransaction(addedTransaction2);
		Map<String, Object> serviceResponse = transactionService.retrieveTransactionByDate(time, 0 , 5);
		List<Transaction> list = new ArrayList<>((Collection<Transaction>) serviceResponse.get("transactions"));
		assertNotEquals(0, list.size());
	}

	@Test
	void readTransactionWithInvalidDateTest() {
		Timestamp time = Timestamp.valueOf("2022-07-01 00:00:00");
		Map<String, Object> serviceResponse = transactionService.retrieveTransactionByDate(time, 0 , 5);
		List<Transaction> list = new ArrayList<>((Collection<Transaction>) serviceResponse.get("transactions"));
		assertEquals(0, list.size());
	}

	@Test
	void cannotFindTransactionTest() {
		assertThrows(TransactionNotFoundException.class, () -> transactionService.getTransaction(-100));
	}

	@Test
	void deleteAllTransactionsTest() {
		Transaction addedTransaction = new Transaction(
				1,
				new Timestamp(0),
				"Test Transaction",
				10000,
				49,
				50
		);
		transactionService.createTransaction(addedTransaction);
		assertDoesNotThrow(() -> {
			transactionService.getTransaction(addedTransaction.getTransactionId());
		});
		transactionService.removeAllTransactions();
		assertThrows(TransactionNotFoundException.class, () -> transactionService.getTransaction(-100));

		Map<String, Object> serviceResponse = transactionService.getAllTransactions(0 , 5);
		List<Transaction> list = new ArrayList<>((Collection<Transaction>) serviceResponse.get("transactions"));
		assertEquals(0, list.size());
	}
	@Test
	void readTransactionByAccountIdTest() {
		Transaction addedTransaction1 = new Transaction(
				1,
				new Timestamp(0),
				"Test Transaction",
				10000,
				49,
				50
		);
		Transaction addedTransaction2 = new Transaction(
				1,
				new Timestamp(0),
				"Test Transaction2",
				10000,
				50,
				49
		);
		transactionService.createTransaction(addedTransaction1);
		transactionService.createTransaction(addedTransaction2);
		Map<String, Object> serviceResponse = transactionService.retrieveTransactionByAccountId(49, null, null, 0, 5);
		List<Transaction> list = new ArrayList<>((Collection<Transaction>) serviceResponse.get("transactions"));
		assertEquals(2, list.size());
	}
	@Test
	void readTransactionByAccountIdSortAmountTest() {
		Transaction addedTransaction1 = new Transaction(
				1,
				new Timestamp(0),
				"Test Transaction",
				20000,
				49,
				50
		);
		Transaction addedTransaction2 = new Transaction(
				1,
				new Timestamp(0),
				"Test Transaction2",
				10000,
				50,
				49
		);
		transactionService.createTransaction(addedTransaction1);
		transactionService.createTransaction(addedTransaction2);
		Map<String, Object> serviceResponseAsc = transactionService.retrieveTransactionByAccountId(49, null, "amounta", 0, 5);
		List<Transaction> listAsc = new ArrayList<>((Collection<Transaction>) serviceResponseAsc.get("transactions"));
		Map<String, Object> serviceResponseDesc = transactionService.retrieveTransactionByAccountId(49, null, "amountd", 0, 5);
		List<Transaction> listDesc = new ArrayList<>((Collection<Transaction>) serviceResponseDesc.get("transactions"));
		//assert first element of listAsc is min amount
		assertEquals(addedTransaction2.getTransactionId(), listAsc.get(0).getTransactionId());
		//assert first element of listDesc is max amount
		assertEquals(addedTransaction1.getTransactionId(), listDesc.get(0).getTransactionId());
	}
	@Test
	void readTransactionByAccountIdSortDateTest() {
		Timestamp time1 = Timestamp.valueOf("2022-07-01 00:00:00");
		Timestamp time2 = Timestamp.valueOf("2022-07-02 00:00:00");
		Transaction addedTransaction1 = new Transaction(
				1,
				time1,
				"Test Transaction",
				20000,
				49,
				50
		);
		Transaction addedTransaction2 = new Transaction(
				1,
				time2,
				"Test Transaction2",
				10000,
				50,
				49
		);
		transactionService.createTransaction(addedTransaction1);
		transactionService.createTransaction(addedTransaction2);

		Map<String, Object> serviceResponseAsc = transactionService.retrieveTransactionByAccountId(49, null, "datea", 0, 5);
		List<Transaction> listAsc = new ArrayList<>((Collection<Transaction>) serviceResponseAsc.get("transactions"));
		Map<String, Object> serviceResponseDesc = transactionService.retrieveTransactionByAccountId(49, null, "dated", 0, 5);
		List<Transaction> listDesc = new ArrayList<>((Collection<Transaction>) serviceResponseDesc.get("transactions"));
		//assert first element of listAsc is the earlier date (addedTransaction1)
		assertEquals(addedTransaction1.getTransactionId(), listAsc.get(0).getTransactionId());
		//assert first element of listDesc is the later date (addedTransaction2)
		assertEquals(addedTransaction2.getTransactionId(), listDesc.get(0).getTransactionId());
		//assert InvalidSortPropException is thrown when unrecognized sortProp "blah" is entered
		assertThrows(InvalidSortPropException.class, () -> transactionService.retrieveTransactionByAccountId(49, null, "blah", 0, 5));
	}

	@Test
	void readTransactionByAccountIdSearchTest() {
		Timestamp time1 = Timestamp.valueOf("2022-07-01 00:00:00");
		Timestamp time2 = Timestamp.valueOf("2022-07-02 00:00:00");
		Transaction addedTransaction1 = new Transaction(
				1,
				time1,
				"Test Transaction",
				20000,
				49,
				50
		);
		Transaction addedTransaction2 = new Transaction(
				1,
				time2,
				"Test Transaction2",
				10000,
				50,
				49
		);
		Transaction addedTransaction3 = new Transaction(
				1,
				time2,
				"This should not appear",
				10000,
				50,
				49
		);
		transactionService.createTransaction(addedTransaction1);
		transactionService.createTransaction(addedTransaction2);
		transactionService.createTransaction(addedTransaction3);

		Map<String, Object> serviceResponse = transactionService.retrieveTransactionByAccountId(49, "test trAnsACTION", null, 0, 5);
		List<Transaction> list = new ArrayList<>((Collection<Transaction>) serviceResponse.get("transactions"));
		Map<String, Object> serviceResponseEmpty = transactionService.retrieveTransactionByAccountId(49, "blah", null, 0, 5);
		List<Transaction> listEmpty = new ArrayList<>((Collection<Transaction>) serviceResponseEmpty.get("transactions"));
		//assert result contains 2 elements when searching "test transaction"
		assertEquals(2, list.size());
		//assert result is empty when searching "blah"
		assertEquals(0, listEmpty.size());
	}
	@Test
	void readTransactionByAccountIdSearchAndSortTest() {
		Timestamp time1 = Timestamp.valueOf("2022-07-01 00:00:00");
		Timestamp time2 = Timestamp.valueOf("2022-07-02 00:00:00");
		Transaction addedTransaction1 = new Transaction(
				1,
				time1,
				"Test Transaction",
				20000,
				49,
				50
		);
		Transaction addedTransaction2 = new Transaction(
				1,
				time2,
				"Test Transaction2",
				10000,
				50,
				49
		);
		Transaction addedTransaction3 = new Transaction(
				1,
				time2,
				"This should not appear",
				10000,
				50,
				49
		);
		transactionService.createTransaction(addedTransaction1);
		transactionService.createTransaction(addedTransaction2);
		transactionService.createTransaction(addedTransaction3);

		Map<String, Object> serviceResponseDateAsc = transactionService.retrieveTransactionByAccountId(49, "test transaction", "datea", 0, 5);
		List<Transaction> listDateAsc = new ArrayList<>((Collection<Transaction>) serviceResponseDateAsc.get("transactions"));
		Map<String, Object> serviceResponseDateDesc = transactionService.retrieveTransactionByAccountId(49, "test transaction", "dated", 0, 5);
		List<Transaction> listDateDesc = new ArrayList<>((Collection<Transaction>) serviceResponseDateDesc.get("transactions"));
		Map<String, Object> serviceResponseAmountAsc = transactionService.retrieveTransactionByAccountId(49, "test transaction", "amounta", 0, 5);
		List<Transaction> listAmountAsc = new ArrayList<>((Collection<Transaction>) serviceResponseAmountAsc.get("transactions"));
		Map<String, Object> serviceResponseAmountDesc = transactionService.retrieveTransactionByAccountId(49, "test transaction", "amountd", 0, 5);
		List<Transaction> listAmountDesc = new ArrayList<>((Collection<Transaction>) serviceResponseAmountDesc.get("transactions"));

		//assert first element of listDateAsc is the earlier date (addedTransaction1)
		assertEquals(addedTransaction1.getTransactionId(), listDateAsc.get(0).getTransactionId());
		//assert first element of listDateDesc is the later date (addedTransaction2)
		assertEquals(addedTransaction2.getTransactionId(), listDateDesc.get(0).getTransactionId());
		//assert first element of listAmountAsc is min amount
		assertEquals(addedTransaction2.getTransactionId(), listAmountAsc.get(0).getTransactionId());
		//assert first element of listAmountDesc is max amount
		assertEquals(addedTransaction1.getTransactionId(), listAmountDesc.get(0).getTransactionId());
		//assert InvalidSortPropException is thrown when unrecognized sortProp "blah" is entered
		assertThrows(InvalidSortPropException.class, () -> transactionService.retrieveTransactionByAccountId(49, "test transaction", "blah", 0, 5));
	}
}
