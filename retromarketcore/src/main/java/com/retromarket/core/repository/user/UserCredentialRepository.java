package com.retromarket.core.repository.user;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.retromarket.core.model.user.UserCredential;

public interface UserCredentialRepository extends MongoRepository<UserCredential, String> {

  UserCredential findByUser(String user);

}
