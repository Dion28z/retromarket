package com.retromarket.web.controller.signup;

import com.retromarket.facade.interfaces.user.UserFacade;
import com.retromarket.facade.model.common.GenericResponseData;
import com.retromarket.facade.model.user.UserData;
import com.retromarket.web.model.common.GenericResponseDTO;
import com.retromarket.web.model.user.UserRegistrationDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SignUpController {

  @Autowired
  private UserFacade userFacade;


  @PostMapping("/signup")
  public GenericResponseDTO signUp(@RequestBody UserRegistrationDTO userRegistration) {

    final GenericResponseDTO response = new GenericResponseDTO(false);
    response.setMessages(new ArrayList<>());

    final List<String> missingFields = validate(userRegistration);

    if (!missingFields.isEmpty()) {
      response.getMessages().addAll(missingFields);
      return response;
    }

    final GenericResponseData uniqueDataValidation = this.userFacade.validateUniqueData(
        userRegistration.getUsername(),
        userRegistration.getEmail(),
        userRegistration.getPhone()
    );
    if (StringUtils.hasText(uniqueDataValidation.getMessage())) {
      response.getMessages().add(uniqueDataValidation.getMessage());
      return response;
    }

    UserData userData = new UserData();
    BeanUtils.copyProperties(userRegistration, userData);

    final GenericResponseData registration = this.userFacade.register(userData, userRegistration.getPassword());
    response.setResult(registration.getResult());

    if (registration.getResult())
      response.setData(registration.getData());
    else
      response.getMessages().add(registration.getMessage());

    return response;
  }

  private List<String> validate(final UserRegistrationDTO signUp) {
    final List<String> missingData = new ArrayList<>();

    if (!StringUtils.hasText(signUp.getEmail())) {
      missingData.add("common.validation.missingParameter");
    }
    if (!StringUtils.hasText(signUp.getUsername())) {
      missingData.add( "common.validation.missingParameter");
    }
    if (!StringUtils.hasText(signUp.getPhone())) {
      missingData.add( "common.validation.missingParameter");
    }
    if (!StringUtils.hasText(signUp.getPassword())) {
      missingData.add( "common.validation.missingParameter");
    }
    return missingData;
  }
}
