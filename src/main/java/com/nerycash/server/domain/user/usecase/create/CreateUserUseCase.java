package com.nerycash.server.domain.user.usecase.create;

public interface CreateUserUseCase {
  CreateUserResult execute(CreateUserCommand command);
}
