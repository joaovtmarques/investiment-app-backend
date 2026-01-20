package com.nerycash.server.domain.user.usecase.create;

import com.nerycash.server.domain.user.enums.AuthProvider;

public class CreateUserCommand {

  private final String name;
  private final String email;
  private final String phone;
  private final String passwordHash;
  private final AuthProvider provider;

  public CreateUserCommand(String name, String email, String phone, String passwordHash, AuthProvider provider) {
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.passwordHash = passwordHash;
    this.provider = provider;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getPhone() {
    return phone;
  }

  public String getPasswordHash() {
    return passwordHash;
  }

  public AuthProvider getProvider() {
    return provider;
  }
}
