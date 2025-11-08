package com.retromarket.facade.interfaces.auth;

import com.retromarket.facade.model.common.GenericResponseData;

public interface AuthFacade {

  GenericResponseData authenticate(final String username, String email, final String password);
}
