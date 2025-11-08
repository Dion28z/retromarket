package com.retromarket.core.enumeration.user;

public enum UserStatus {
  ACTIVE("Active User", 1),
  INACTIVE("Inactive User", 0),
  PENDING("Pending Approval", 2);

  private final String description;
  private final int code;

  UserStatus(String description, int code) {
    this.description = description;
    this.code = code;
  }

  public String getDescription() {
    return description;
  }

  public int getCode() {
    return code;
  }
}
