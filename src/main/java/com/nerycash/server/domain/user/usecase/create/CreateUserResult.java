package com.nerycash.server.domain.user.usecase.create;

import java.util.UUID;

import com.nerycash.server.domain.level.enums.LevelName;
import com.nerycash.server.domain.user.enums.AuthProvider;

public record CreateUserResult(
		UUID userId,
		String email,
		String name,
		String phone,
		AuthProvider provider,
		UUID levelId,
		LevelName levelName,
		Long xp) {
}
