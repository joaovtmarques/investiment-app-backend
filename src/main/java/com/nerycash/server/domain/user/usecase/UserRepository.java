package com.nerycash.server.domain.user.usecase;

import java.util.Optional;

import com.nerycash.server.domain.user.model.User;

public interface UserRepository {

  User save(User user);

  Optional<User> findByEmail(String email);

}
