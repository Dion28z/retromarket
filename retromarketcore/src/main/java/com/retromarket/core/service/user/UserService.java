package com.retromarket.core.service.user;

import com.retromarket.core.model.user.User;

public interface UserService {
    User findByUsername(final String username);
    User findByEmail(final String email);
    User findByPhone(final String phone);
    User register(final User newUser);
}
