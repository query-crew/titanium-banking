package com.titanium.user.repository;

import java.util.List;

import com.titanium.user.model.BankUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<BankUser, Integer> {
    void deleteAll();
    void deleteById(Integer id);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);

    BankUser findByUsername(String username);
    List<BankUser> findAll();
    BankUser findByUserId(int id);

}
