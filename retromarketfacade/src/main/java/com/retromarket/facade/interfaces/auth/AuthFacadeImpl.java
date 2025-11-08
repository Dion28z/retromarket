package com.retromarket.facade.interfaces.auth;

import com.retromarket.core.enumeration.user.UserStatus;
import com.retromarket.core.model.user.User;
import com.retromarket.core.model.user.UserCredential;
import com.retromarket.core.service.auth.AuthService;
import com.retromarket.core.service.jwt.JwtService;
import com.retromarket.core.service.user.UserCredentialService;
import com.retromarket.core.service.user.UserService;
import com.retromarket.facade.model.common.GenericResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Service
public class AuthFacadeImpl implements AuthFacade {

  @Autowired
  private AuthService authService;

  @Autowired
  private UserService userService;

  @Autowired
  private JwtService jwtService;

  @Autowired
  private UserCredentialService credentialService;


  public GenericResponseData authenticate(final String username, String email, final String password) {
    GenericResponseData commonResponseData = new GenericResponseData();
    commonResponseData.setResult(false);
    User user = null;

    if (StringUtils.hasText(username)) {
      user = this.userService.findByUsername(username);
    } else if (StringUtils.hasText(email)) {
      user = this.userService.findByEmail(email);
    }

    if (Objects.isNull(user)) {
      commonResponseData.setMessage("auth.account.notfound");
      return commonResponseData;
    }

    if (user.getStatus() == UserStatus.INACTIVE) {
      commonResponseData.setMessage("auth.account.disabled");
      return commonResponseData;
    }

    final UserCredential credential = this.credentialService.getUserCredential((user.getId()));
    if (Objects.isNull(credential)) {
      commonResponseData.setMessage("auth.credential.notfound");
      return commonResponseData;
    }

    final Boolean authenticated = this.authService.authenticate(credential.getPassword(), password);
    if (!authenticated) {
      commonResponseData.setMessage("auth.credential.incorrect");
      return commonResponseData;
    }
    final String token = this.jwtService.generate(username);
    commonResponseData.setData(token);
    commonResponseData.setResult(true);
    return commonResponseData;
  }
}
