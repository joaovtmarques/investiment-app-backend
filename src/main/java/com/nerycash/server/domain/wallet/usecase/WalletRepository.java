package com.nerycash.server.domain.wallet.usecase;

import com.nerycash.server.domain.wallet.model.Wallet;

public interface WalletRepository {

  Wallet save(Wallet wallet);

}
