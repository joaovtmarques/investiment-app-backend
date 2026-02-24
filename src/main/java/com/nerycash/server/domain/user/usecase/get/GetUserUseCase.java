package com.nerycash.server.domain.user.usecase.get;

import java.util.UUID;

public interface GetUserUseCase {

    GetUserResult execute(UUID id);

}
