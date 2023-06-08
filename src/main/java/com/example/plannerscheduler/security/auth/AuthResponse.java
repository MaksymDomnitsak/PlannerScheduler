package com.example.plannerscheduler.security.auth;

import com.example.plannerscheduler.enums.UserRole;


public class AuthResponse {

  private Long userId;

  private String email;

  private String userName;
  private String groupId;

  private UserRole role;


  public AuthResponse(Long userId, String email, String userName, UserRole role) {
    this.userId = userId;
    this.email = email;
    this.userName = userName;
    this.groupId = null;
    this.role = role;
  }

  public AuthResponse() {
  }

  public AuthResponse(Long userId, String groupId, String email, String userName, UserRole role) {
    this.userId = userId;
    this.groupId = groupId;
    this.userName = userName;
    this.email = email;
    this.role = role;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public UserRole getRole() {
    return role;
  }

  public void setRole(UserRole role) {
    this.role = role;
  }

  public String getGroupId() {
    return groupId;
  }

  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
}
