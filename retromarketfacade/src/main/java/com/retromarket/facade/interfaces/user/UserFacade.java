package com.retromarket.facade.interfaces.user;

import com.retromarket.core.model.user.User;
import com.retromarket.facade.model.common.MessageResultDTO;
import com.retromarket.facade.model.user.UserRegistrationDTO;

public interface UserFacade {
    User findByUsername(final String username);
    User findByEmail(final String email);
    User findByPhone(final String phone);
    MessageResultDTO validateUniqueData(final String username, final String email, final String phone);
    MessageResultDTO register(final UserRegistrationDTO signUp);
}
