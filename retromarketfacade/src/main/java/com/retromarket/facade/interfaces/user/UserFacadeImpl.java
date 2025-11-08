package com.retromarket.facade.interfaces.user;

import com.retromarket.core.model.user.User;
import com.retromarket.core.service.jwt.JwtService;
import com.retromarket.core.service.user.UserCredentialService;
import com.retromarket.core.service.user.UserService;
import com.retromarket.facade.model.common.MessageResultDTO;
import com.retromarket.facade.model.user.UserRegistrationDTO;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserFacadeImpl implements UserFacade {

  private UserService userService;
  private JwtService jwtService;
  private UserCredentialService credentialService;

  public User findByUsername(final String username) {
    return this.userService.findByUsername(username);
  }

  public User findByEmail(final String email) {
    return this.userService.findByEmail(email);
  }

  public User findByPhone(final String phone) {
    return this.userService.findByPhone(phone);
  }

  public MessageResultDTO validateUniqueData(final String username, final String email, final String phone) {
    final MessageResultDTO messageResult = new MessageResultDTO();
    messageResult.setResult(false);

    final User usernameTaken = this.findByUsername(username);
    if (Objects.nonNull(usernameTaken)) {
      messageResult.setMessage("user.usernameTaken");
      return messageResult;
    }

    final User emailTaken = this.findByEmail(email);
    if (Objects.nonNull(emailTaken)) {
      messageResult.setMessage("user.emailTaken");
      return messageResult;
    }
    final User phoneTaken = this.findByPhone(phone);
    if (Objects.nonNull(phoneTaken)) {
      messageResult.setMessage("user.phoneTaken");
      return messageResult;
    }

    messageResult.setResult(true);
    return messageResult;
  }

  public MessageResultDTO register(final UserRegistrationDTO signUp) {
    final MessageResultDTO messageResult = new MessageResultDTO();
    final User userRegistration = new User();
    messageResult.setResult(false);
    userRegistration.setEmail(signUp.getEmail());
    userRegistration.setFirstName(signUp.getFirstName());
    userRegistration.setSecondLastName(signUp.getSecondLastName());
    userRegistration.setLastName(signUp.getLastName());
    userRegistration.setSecondLastName(signUp.getSecondLastName());
    userRegistration.setPhone(signUp.getPhone());
    userRegistration.setGender(signUp.getGender());
    final User user = this.userService.register(userRegistration);

    if (Objects.isNull(user)) {
      messageResult.setMessage("user.register.error");
      return messageResult;
    }

    this.credentialService.create(user.getId(), signUp.getPassword());
    final String token = this.jwtService.generate(user.getUsername());
    return messageResult;
  }

}
