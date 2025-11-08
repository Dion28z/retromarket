package com.retromarket.facade.interfaces.user;

import com.retromarket.core.model.user.User;
import com.retromarket.core.service.jwt.JwtService;
import com.retromarket.core.service.user.UserCredentialService;
import com.retromarket.core.service.user.UserService;
import com.retromarket.facade.model.common.GenericResponseData;
import com.retromarket.facade.model.user.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserFacadeImpl implements UserFacade {

  @Autowired
  private UserService userService;

  @Autowired
  private JwtService jwtService;

  @Autowired
  private UserCredentialService credentialService;

  public User findByUsername(final String username) {
    return userService.findByUsername(username);
  }

  public User findByEmail(final String email) {
    return userService.findByEmail(email);
  }

  public User findByPhone(final String phone) {
    return userService.findByPhone(phone);
  }

  public GenericResponseData validateUniqueData(final String username, final String email, final String phone) {
    final GenericResponseData commonResponseData = new GenericResponseData();
    commonResponseData.setResult(false);

    final User usernameTaken = findByUsername(username);
    if (Objects.nonNull(usernameTaken)) {
      commonResponseData.setMessage("user.usernameTaken");
      return commonResponseData;
    }

    final User emailTaken = findByEmail(email);
    if (Objects.nonNull(emailTaken)) {
      commonResponseData.setMessage("user.emailTaken");
      return commonResponseData;
    }
    final User phoneTaken = findByPhone(phone);
    if (Objects.nonNull(phoneTaken)) {
      commonResponseData.setMessage("user.phoneTaken");
      return commonResponseData;
    }

    commonResponseData.setResult(true);
    return commonResponseData;
  }

  public GenericResponseData register(final UserData userData, final String password) {
    final GenericResponseData commonResponseData = new GenericResponseData();
    final User userRegistration = new User();
    commonResponseData.setResult(false);
    userRegistration.setEmail(userData.getEmail());
    userRegistration.setFirstName(userData.getFirstName());
    userRegistration.setSecondLastName(userData.getSecondLastName());
    userRegistration.setLastName(userData.getLastName());
    userRegistration.setSecondLastName(userData.getSecondLastName());
    userRegistration.setPhone(userData.getPhone());
    userRegistration.setGender(userData.getGender());
    final User user = userService.register(userRegistration);

    if (Objects.isNull(user)) {
      commonResponseData.setMessage("user.register.error");
      return commonResponseData;
    }

    credentialService.create(user.getId(), password);
    final String token = jwtService.generate(user.getUsername());
    commonResponseData.setData(token);
    commonResponseData.setResult(true);
    return commonResponseData;
  }
}
