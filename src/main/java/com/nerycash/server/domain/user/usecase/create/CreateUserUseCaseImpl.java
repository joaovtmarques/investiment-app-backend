package com.nerycash.server.domain.user.usecase.create;

import org.springframework.transaction.annotation.Transactional;

import com.nerycash.server.domain.level.enums.LevelName;
import com.nerycash.server.domain.level.model.Level;
import com.nerycash.server.domain.level.usecase.LevelRepository;
import com.nerycash.server.domain.user.model.User;
import com.nerycash.server.domain.user.usecase.UserRepository;
import com.nerycash.server.domain.wallet.model.Wallet;
import com.nerycash.server.domain.wallet.usecase.WalletRepository;

@Transactional
public class CreateUserUseCaseImpl implements CreateUserUseCase {

  private UserRepository userRepository;
  private WalletRepository walletRepository;
  private LevelRepository levelRepository;

  public CreateUserUseCaseImpl(UserRepository repository, WalletRepository walletRepository,
      LevelRepository levelRepository) {
    this.userRepository = repository;
    this.walletRepository = walletRepository;
    this.levelRepository = levelRepository;
  }

  @Override
  public CreateUserResult execute(CreateUserCommand command) {

    Level level = levelRepository.findByName(LevelName.BRONZE);

    if (level == null)
      throw new IllegalStateException("Nível BRONZE não encontrado");

    User user = new User(
        command.getName(),
        command.getEmail(),
        command.getPhone(),
        command.getPasswordHash(),
        command.getProvider(),
        level);

    userRepository.save(user);

    Wallet wallet = Wallet.createFor(user);

    walletRepository.save(wallet);

    return new CreateUserResult(
        user.getId(),
        user.getEmail(),
        user.getName(),
        user.getPhone(),
        user.getProvider(),
        level.getId(),
        level.getName(),
        user.getXp());
  }

}
