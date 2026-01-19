package com.nerycash.server.domain.transaction.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import com.nerycash.server.domain.investment.model.Investment;
import com.nerycash.server.domain.transaction.enums.Type;
import com.nerycash.server.domain.user.model.User;
import com.nerycash.server.domain.wallet.model.Wallet;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "transactions")
public class Transaction {

  @EqualsAndHashCode.Include
  @Id
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false, updatable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "wallet_id", nullable = false, updatable = false)
  private Wallet wallet;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "investment_id", updatable = false)
  private Investment investment;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Type type;

  @Column(nullable = false)
  private BigDecimal amount;

  @Column(name = "balance_before", nullable = false)
  private BigDecimal balanceBefore;

  @Column(name = "balance_after", nullable = false)
  private BigDecimal balanceAfter;

  @Column(name = "created_at", nullable = false)
  private Instant createdAt;

}
