package com.retromarket.core.service.user;

import com.retromarket.core.enumeration.user.UserStatus;
import com.retromarket.core.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retromarket.core.repository.user.UserRepository;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  public User findByUsername(final String username) {
    return this.userRepository.findByUsername(username);
  }

  public User findByEmail(final String email) {
    return this.userRepository.findByEmail(email);
  }

  public User findByPhone(final String phone) {
    return this.userRepository.findByPhone(phone);
  }

  public User register(final User newUser) {
    newUser.setStatus(UserStatus.PENDING);
    return this.userRepository.save(newUser);
  }
}
