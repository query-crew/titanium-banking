package com.titanium.transactions.repository;

import com.titanium.transactions.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    Transaction findById(int id);
    Page<Transaction> findAll(Pageable pageable);
    Page<Transaction> findByAccountFromId(int accountFromId, Pageable pageable);
    Page<Transaction> findByAccountToId(int accountToId, Pageable pageable);
    //Find by accountFromId and Description contains description. No sort
    Page<Transaction> findByAccountFromIdAndDescriptionContainingIgnoreCase(int accountFromId, String description, Pageable pageable);
    //Find by accountToId and Description contains description. No sort
    Page<Transaction> findByAccountToIdAndDescriptionContainingIgnoreCase(int accountToId, String description, Pageable pageable);
    //Find by accountFromId, no description, Sort date asc,
    Page<Transaction> findByAccountFromIdOrderByTransactionDateAsc(int accountFromId, Pageable pageable);
    //Find by accountToId, no description, Sort date asc,
    Page<Transaction> findByAccountToIdOrderByTransactionDateAsc(int accountToId, Pageable pageable);
    //Find by accountFromId, no description, Sort date desc,
    Page<Transaction> findByAccountFromIdOrderByTransactionDateDesc(int accountFromId, Pageable pageable);
    //Find by accountToId, no description, Sort date desc,
    Page<Transaction> findByAccountToIdOrderByTransactionDateDesc(int accountToId, Pageable pageable);
    //Find by accountFromId, no description, Sort amount asc,
    Page<Transaction> findByAccountFromIdOrderByAmountAsc(int accountFromId, Pageable pageable);
    //Find by accountToId, no description, Sort amount asc,
    Page<Transaction> findByAccountToIdOrderByAmountAsc(int accountToId, Pageable pageable);
    //Find by accountFromId, no description, Sort amount desc,
    Page<Transaction> findByAccountFromIdOrderByAmountDesc(int accountFromId, Pageable pageable);
    //Find by accountToId, no description, Sort amount desc,
    Page<Transaction> findByAccountToIdOrderByAmountDesc(int accountToId, Pageable pageable);
    //Find by accountFromId, and description contains description, Sort date asc,
    Page<Transaction> findByAccountFromIdAndDescriptionContainingIgnoreCaseOrderByTransactionDateAsc(int accountFromId, String description, Pageable pageable);
    //Find by accountToId, and description contains description, Sort date asc,
    Page<Transaction> findByAccountToIdAndDescriptionContainingIgnoreCaseOrderByTransactionDateAsc(int accountToId, String description, Pageable pageable);
    //Find by accountFromId, and description contains description, Sort date desc,
    Page<Transaction> findByAccountFromIdAndDescriptionContainingIgnoreCaseOrderByTransactionDateDesc(int accountFromId, String description, Pageable pageable);
    //Find by accountToId, and description contains description, Sort date asc,
    Page<Transaction> findByAccountToIdAndDescriptionContainingIgnoreCaseOrderByTransactionDateDesc(int accountToId, String description, Pageable pageable);
    //Find by accountFromId, and description contains description, Sort amount asc,
    Page<Transaction> findByAccountFromIdAndDescriptionContainingIgnoreCaseOrderByAmountAsc(int accountFromId, String description, Pageable pageable);
    //Find by accountToId, and description contains description, Sort amount asc,
    Page<Transaction> findByAccountToIdAndDescriptionContainingIgnoreCaseOrderByAmountAsc(int accountToId, String description, Pageable pageable);
    //Find by accountFromId, and description contains description, Sort amount desc,
    Page<Transaction> findByAccountFromIdAndDescriptionContainingIgnoreCaseOrderByAmountDesc(int accountFromId, String description, Pageable pageable);
    //Find by accountToId, and description contains description, Sort amount desc,
    Page<Transaction> findByAccountToIdAndDescriptionContainingIgnoreCaseOrderByAmountDesc(int accountToId, String description, Pageable pageable);
    Page<Transaction> findByTransactionType(int transactionType, Pageable pageable);
    Page<Transaction> findByTransactionDate(Timestamp transactionDate, Pageable pageable);
}