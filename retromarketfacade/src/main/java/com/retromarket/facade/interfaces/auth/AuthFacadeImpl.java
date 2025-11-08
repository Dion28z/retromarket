package com.retromarket.facade.interfaces.auth;

import com.retromarket.core.enumeration.user.UserStatus;
import com.retromarket.core.model.user.User;
import com.retromarket.core.model.user.UserCredential;
import com.retromarket.core.service.auth.AuthService;
import com.retromarket.core.service.jwt.JwtService;
import com.retromarket.core.service.user.UserCredentialService;
import com.retromarket.core.service.user.UserService;
import com.retromarket.facade.model.common.MessageResultDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Service
public class AuthFacadeImpl implements AuthFacade {

  private AuthService authService;
  private UserService userService;
  private JwtService jwtService;
  private UserCredentialService credentialService;


  public MessageResultDTO authenticate(final String username, String email, final String password) {
    MessageResultDTO result = new MessageResultDTO();
    result.setResult(false);
    User user = null;

    if (StringUtils.hasText(username)) {
      user = this.userService.findByUsername(username);
    } else if (StringUtils.hasText(email)) {
      user = this.userService.findByEmail(email);
    }

    if (Objects.isNull(user)) {
      result.setMessage("auth.account.notfound");
      return result;
    }

    if (user.getStatus() == UserStatus.INACTIVE) {
      result.setMessage("auth.account.disabled");
      return result;
    }

    final UserCredential credential = this.credentialService.getUserCredential((user.getId()));
    if (Objects.isNull(credential)) {
      result.setMessage("auth.credential.notfound");
      return result;
    }

    final Boolean authenticated = this.authService.authenticate(credential.getPassword(), password);
    if (!authenticated) {
      result.setMessage("auth.credential.incorrect");
      return result;
    }
    final String token = this.jwtService.generate(username);
    result.setMessage(token);
    result.setResult(true);
    return result;
  }
}
