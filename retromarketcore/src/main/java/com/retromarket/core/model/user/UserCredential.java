package com.retromarket.core.model.user;

import java.sql.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("UserCredential")
public class UserCredential {

  @Id
  private String id;

  @CreatedDate
  private Date createdAt;

  @LastModifiedDate
  private Date updatedAt;

  private String password;
  private String user;

  public UserCredential(final String user, final String password) {
    this.user = user;
    this.password = password;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public String getPassword() {
    return password;
  }

  public String getUser() {
    return user;
  }
}
