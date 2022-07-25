package com.titanium.transactions.repository;

import com.titanium.transactions.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    Transaction findById(int id);
    List<Transaction> findAll();
    List<Transaction> findByAccountToId(int id);
    List<Transaction> findByAccountFromId(int id);
    List<Transaction> findByTransactionType(int transactionType);
}