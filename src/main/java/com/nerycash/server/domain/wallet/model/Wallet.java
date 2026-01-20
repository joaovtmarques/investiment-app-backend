package com.nerycash.server.domain.wallet.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nerycash.server.domain.user.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "wallets")
public class Wallet {

  public Wallet(
      User user,
      BigDecimal availableBalance,
      BigDecimal investedBalance,
      BigDecimal receivableBalance) {
    this.id = UUID.randomUUID();
    this.user = user;
    this.availableBalance = availableBalance;
    this.investedBalance = investedBalance;
    this.receivableBalance = receivableBalance;
  }

  @EqualsAndHashCode.Include
  @Id
  private UUID id;

  @JsonIgnore
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false, updatable = false)
  private User user;

  @Column(name = "available_balance", nullable = false)
  private BigDecimal availableBalance;

  @Column(name = "invested_balance", nullable = false)
  private BigDecimal investedBalance;

  @Column(name = "receivable_balance", nullable = false)
  private BigDecimal receivableBalance;

  @Column(name = "updated_at", nullable = false)
  private Instant updatedAt;

  public static Wallet createFor(User user) {
    return new Wallet(
        user,
        BigDecimal.ZERO,
        BigDecimal.ZERO,
        BigDecimal.ZERO);
  }
}
