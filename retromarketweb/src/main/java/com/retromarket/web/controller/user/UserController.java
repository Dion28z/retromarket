package com.retromarket.web.controller.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

//import backoffice.service.user.UserService;

@RestController
public abstract class UserController {

  // private final UserService userFacade;

  @PostMapping("/users")
  public String add() {
    return "Add User";
  }

  @PutMapping("/users")
  public String update() {
    return "Add User";
  }

  @GetMapping("/users")
  public String getAll() {
    return "Add User";
  }

  @GetMapping("/users/{code}")
  public String getByCode() {
    return "Add User";
  }
}
