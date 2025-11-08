package com.retromarket.core.service.auth;

public interface AuthService {

  Boolean authenticate(final String credential, final String password);
}
