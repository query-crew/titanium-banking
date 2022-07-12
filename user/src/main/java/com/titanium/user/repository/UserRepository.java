package com.titanium.user.repository;

import java.util.List;
import java.util.spi.CurrencyNameProvider;

import com.titanium.user.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    void deleteAll();
    void deleteById(Integer id);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    List<User> findAll();
    User findByUserId(int id);

}
