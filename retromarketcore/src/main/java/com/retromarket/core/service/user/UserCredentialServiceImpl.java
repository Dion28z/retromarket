package com.retromarket.core.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.retromarket.core.model.user.UserCredential;
import com.retromarket.core.repository.user.UserCredentialRepository;

@Service
public class UserCredentialServiceImpl implements UserCredentialService {

  private UserCredentialRepository userCredentialRepository;

  public UserCredential getUserCredential(final String userId) {
    return this.userCredentialRepository.findByUser(userId);
  }

  public void create(final String userId, final String password) {
    final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    final UserCredential credential = new UserCredential(userId, passwordEncoder.encode(password));
    this.userCredentialRepository.save(credential);
  }
}
