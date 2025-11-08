package com.retromarket.web.controller.auth;

import com.retromarket.facade.interfaces.auth.AuthFacade;
import com.retromarket.web.model.auth.AuthenticationDTO;
import com.retromarket.facade.model.common.GenericResponseData;
import com.retromarket.web.model.common.GenericResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Objects;

@RestController
public class AuthController {

  @Autowired
  private AuthFacade authFacade;

  @PostMapping("/auth")
  public GenericResponseDTO login(@RequestBody AuthenticationDTO auth) {
    GenericResponseDTO response = new GenericResponseDTO(false);
    response.setMessages(new ArrayList<>());

    if (StringUtils.hasText(auth.getEmail()) ) {
      response.getMessages().add("common.validation.missingParameter");
    }
    if (StringUtils.hasText(auth.getPassword())) {
      response.getMessages().add("common.validation.missingParameter");
    }

    if (!response.getMessages().isEmpty()) {
      return response;
    }

    final GenericResponseData authentication = this.authFacade.authenticate(
        auth.getUsername(),
        auth.getEmail(),
        auth.getPassword()
    );

    response.setResult(authentication.getResult());

    if (authentication.getResult())
      response.setData(authentication.getData());
    else
      response.getMessages().add(authentication.getMessage());

    return response;
  }
}
