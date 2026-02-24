package com.nerycash.server.domain.user.usecase.get;

import com.nerycash.server.domain.user.model.User;
import com.nerycash.server.domain.user.usecase.UserRepository;

import java.util.Optional;
import java.util.UUID;

public class GetUserUseCaseImpl implements GetUserUseCase {

    private UserRepository userRepository;

    public GetUserUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public GetUserResult execute(UUID id) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isEmpty()) return null;
        return new GetUserResult(user.get().getId(),
                user.get().getName(),
                user.get().getEmail(),
                user.get().getPhone(),
                user.get().getProvider(),
                user.get().getLevel(),
                user.get().getXp(),
                user.get().getIsActive(),
                user.get().getWallet());
    }
}
