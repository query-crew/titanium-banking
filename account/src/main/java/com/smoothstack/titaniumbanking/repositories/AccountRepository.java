package com.smoothstack.titaniumbanking.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smoothstack.titaniumbanking.models.Account;

// @Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByAccountId(int id);
}
