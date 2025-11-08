package com.retromarket.core.model.user;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import com.retromarket.core.enumeration.user.UserStatus;

import java.util.Date;

@Document("User")
public class User {
  @Id
  private String id;

  @CreatedDate
  private Date createdAt;

  @LastModifiedDate
  private Date updatedAt;

  private String email;
  private String phone;
  private String username;
  private String firstName;
  private String secondName;
  private String lastName;
  private String secondLastName;
  private Date birthDate;
  private String gender;
  private UserStatus status;

  /*
   * public User(
   * final String email,
   * final String phone,
   * final String userName,
   * final String firstName,
   * final String secondName,
   * final String lastName,
   * final String secondLastName,
   * final Date birthDate,
   * final String gender,
   * final UserStatus status) {
   * this.email = email;
   * this.phone = phone;
   * this.userName = userName;
   * this.firstName = firstName;
   * this.secondName = secondName;
   * this.lastName = lastName;
   * this.secondLastName = secondLastName;
   * this.birthDate = birthDate;
   * this.gender = gender;
   * this.status = status;
   * }
   */

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getSecondName() {
    return secondName;
  }

  public void setSecondName(String secondName) {
    this.secondName = secondName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getSecondLastName() {
    return secondLastName;
  }

  public void setSecondLastName(String secondLastName) {
    this.secondLastName = secondLastName;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public UserStatus getStatus() {
    return status;
  }

  public void setStatus(UserStatus status) {
    this.status = status;
  }

}
