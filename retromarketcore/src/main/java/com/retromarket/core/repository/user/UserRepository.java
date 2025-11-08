package com.retromarket.core.repository.user;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.retromarket.core.model.user.User;

public interface UserRepository extends MongoRepository<User, String> {

  User findByUsername(String username);
  User findByEmail(String email);
  User findByPhone(String phone);

}
