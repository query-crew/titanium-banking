package com.titanium.user.security;

import com.titanium.user.model.BankUser;
import com.titanium.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserDetails user;
    if (userRepository.existsByUsername(username)) {
      BankUser bankUser = userRepository.findByUsername(username);
      user = User.withUsername(bankUser.getUsername()).password(bankUser.getPassword()).roles(bankUser.getUserType()).build();
    }
    else {
      throw new UsernameNotFoundException("User Not Found with username: " + username);
    }
    return user;
  }

}
