package com.titanium.transactions.repository;


import com.titanium.transactions.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    Transaction findById(int id);
    List<Transaction> findAll();
}
