package com.smoothstack.titaniumbanking.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.smoothstack.titaniumbanking.models.Account;

import java.util.List;

// @Repository

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByAccountId(int id);

    Page<Account> findAll(Pageable pageable);

    boolean existsByAccountNumber(String accountNumber);
}
