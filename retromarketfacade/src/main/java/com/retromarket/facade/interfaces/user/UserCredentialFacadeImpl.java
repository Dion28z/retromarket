package com.retromarket.facade.interfaces.user;

import com.retromarket.core.service.user.UserCredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCredentialFacadeImpl implements UserCredentialFacade {

  @Autowired
  private UserCredentialService userCredentialService;

  public void create(final String userId, final String password) {
    this.userCredentialService.create(userId, password);
  }
}
