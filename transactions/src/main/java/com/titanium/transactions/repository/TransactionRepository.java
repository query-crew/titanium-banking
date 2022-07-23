package com.titanium.transactions.repository;

import com.titanium.transactions.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    Transaction findById(int id);
    List<Transaction> findAll();
    List<Transaction> findByAccountToId(int id);
    List<Transaction> findByAccountFromId(int id);
    List<Transaction> findByTransactionType(int transactionType);
    List<Transaction> findByTransactionDate(Timestamp transactionDate);
}