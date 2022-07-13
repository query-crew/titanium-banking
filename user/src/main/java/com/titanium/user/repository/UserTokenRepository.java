package com.titanium.user.repository;

import java.util.List;

import com.titanium.user.model.User;
import com.titanium.user.model.UserToken;
import org.springframework.data.repository.CrudRepository;

import javax.jws.soap.SOAPBinding;

public interface UserTokenRepository extends CrudRepository<UserToken, Integer> {
    boolean existsByConfirmationToken(String confirmationToken);

    UserToken findByConfirmationToken(String confirmationToken);

    UserToken findByBankUser(User user);

    UserToken findById(int id);
}
