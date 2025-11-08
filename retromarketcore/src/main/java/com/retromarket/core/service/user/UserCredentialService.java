package com.retromarket.core.service.user;

import com.retromarket.core.model.user.UserCredential;

public interface UserCredentialService {
  UserCredential getUserCredential(final String userId);
  void create(final String userId, final String password);
}
