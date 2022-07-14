package com.titanium.user.repository;

import com.titanium.user.model.BankUser;
import com.titanium.user.model.UserToken;
import org.springframework.data.repository.CrudRepository;

public interface UserTokenRepository extends CrudRepository<UserToken, Integer> {
    boolean existsByConfirmationToken(String confirmationToken);

    UserToken findByConfirmationToken(String confirmationToken);

    UserToken findByBankUser(BankUser user);

    UserToken findById(int id);
}
