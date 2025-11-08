package com.retromarket.facade.interfaces.user;

import com.retromarket.core.model.user.User;
import com.retromarket.facade.model.common.GenericResponseData;
import com.retromarket.facade.model.user.UserData;

public interface UserFacade {
    User findByUsername(final String username);
    User findByEmail(final String email);
    User findByPhone(final String phone);
    GenericResponseData validateUniqueData(final String username, final String email, final String phone);
    GenericResponseData register(final UserData userData, final String password);
}
