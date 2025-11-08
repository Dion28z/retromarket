package com.retromarket.web.controller.auth;

import com.retromarket.facade.interfaces.auth.AuthFacade;
import com.retromarket.facade.model.auth.AuthenticationDTO;
import com.retromarket.facade.model.common.GenericResponseDTO;
import com.retromarket.facade.model.common.MessageResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

  @Autowired
  private AuthFacade authFacade;

  @PostMapping("/auth")
  public GenericResponseDTO login(@RequestBody AuthenticationDTO auth) {
    GenericResponseDTO response = new GenericResponseDTO(false);

    if (!StringUtils.hasText(auth.getUsername()) && !StringUtils.hasText(auth.getEmail())) {
      response.setMessage("common.validation.missingParameter");
      return response;
    }
    if (!StringUtils.hasText(auth.getPassword())) {
      response.setMessage("common.validation.missingParameter");
      return response;

    }

    final MessageResultDTO authentication = this.authFacade.authenticate(auth.getUsername(),auth.getEmail(), auth.getPassword());
    response.setResult(authentication.getResult());
    if (authentication.getResult()) {
      response.setData(authentication.getMessage());
    }
    else {
      response.setMessage(authentication.getMessage());
    }
    return response;
  }
}
