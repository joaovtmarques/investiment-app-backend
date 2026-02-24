package com.nerycash.server.domain.user.usecase.get;

import com.nerycash.server.domain.level.model.Level;
import com.nerycash.server.domain.user.enums.AuthProvider;
import com.nerycash.server.domain.wallet.model.Wallet;

import java.util.UUID;

public record GetUserResult(
        UUID id,
        String name,
        String email,
        String phone,
        AuthProvider provider,
        Level level,
        Long xp,
        Boolean isActive,
        Wallet wallet) {
}
