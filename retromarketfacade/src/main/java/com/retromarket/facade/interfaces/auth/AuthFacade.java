package com.retromarket.facade.interfaces.auth;

import com.retromarket.facade.model.common.MessageResultDTO;

public interface AuthFacade {

  MessageResultDTO authenticate(final String username, String email, final String password);
}
