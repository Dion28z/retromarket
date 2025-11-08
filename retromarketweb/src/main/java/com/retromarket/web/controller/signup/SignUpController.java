package com.retromarket.web.controller.signup;

import com.retromarket.facade.interfaces.user.UserFacade;
import com.retromarket.facade.model.common.GenericResponseDTO;
import com.retromarket.facade.model.common.MessageResultDTO;
import com.retromarket.facade.model.user.UserRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SignUpController {

  @Autowired
  private UserFacade userFacade;


  @PostMapping("/signup")
  public GenericResponseDTO signUp(@RequestBody UserRegistrationDTO signUp) {

    final GenericResponseDTO response = new GenericResponseDTO(false);
    final String missingFields = validateRequired(signUp);
    if (StringUtils.hasText(missingFields)) {
      response.setMessage(missingFields);
      return response;
    }

    final MessageResultDTO uniqueFieldsData = this.userFacade.validateUniqueData(signUp.getUsername(), signUp.getEmail(), signUp.getPhone());
    if (StringUtils.hasText(uniqueFieldsData.getMessage())) {
      response.setMessage(uniqueFieldsData.getMessage());
      return response;
    }


    final MessageResultDTO registration = this.userFacade.register(signUp);
    response.setResult(registration.getResult());
    if (registration.getResult()) {
      response.setData(registration.getMessage());
    }
    else {
      response.setMessage(registration.getMessage());
    }
    return response;
  }

  private String validateRequired(final UserRegistrationDTO signUp) {
    if (!StringUtils.hasText(signUp.getEmail())) {
      return "common.validation.missingParameter";
    }
    if (!StringUtils.hasText(signUp.getUsername())) {
      return "common.validation.missingParameter";
    }
    if (!StringUtils.hasText(signUp.getPhone())) {
      return "common.validation.missingParameter";
    }
    if (!StringUtils.hasText(signUp.getPassword())) {
      return "common.validation.missingParameter";
    }
    return "";
  }
}
