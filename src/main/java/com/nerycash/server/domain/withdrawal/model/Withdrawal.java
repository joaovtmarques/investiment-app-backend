package com.nerycash.server.domain.withdrawal.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import com.nerycash.server.domain.user.model.User;
import com.nerycash.server.domain.wallet.model.Wallet;
import com.nerycash.server.domain.withdrawal.enums.Method;
import com.nerycash.server.domain.withdrawal.enums.Status;

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
@Table(name = "withdrawals")
public class Withdrawal {

  @EqualsAndHashCode.Include
  @Id
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false, updatable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "wallet_id", nullable = false, updatable = false)
  private Wallet wallet;

  @Column(nullable = false)
  private BigDecimal amount;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Method method;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Status status = Status.PENDING;

  @Column(name = "requested_at", nullable = false)
  private Instant requestedAt;

  @Column(name = "processed_at")
  private Instant processedAt;

}
