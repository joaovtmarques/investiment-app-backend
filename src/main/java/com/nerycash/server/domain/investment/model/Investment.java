package com.nerycash.server.domain.investment.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import com.nerycash.server.domain.investment.enums.Status;
import com.nerycash.server.domain.product.model.Product;
import com.nerycash.server.domain.user.model.User;

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
@Table(name = "investments")
public class Investment {

  @EqualsAndHashCode.Include
  @Id
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false, updatable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id", nullable = false, updatable = false)
  private Product product;

  @Column(nullable = false)
  private BigDecimal amount;

  @Column(name = "expected_return", nullable = false)
  private BigDecimal expectedReturn;

  @Column(name = "bonus_return", nullable = false)
  private BigDecimal bonusReturn;

  @Enumerated(EnumType.STRING)
  private Status status;

  @Column(name = "start_date", nullable = false)
  private Instant startDate;

  @Column(name = "end_date", nullable = false)
  private Instant endDate;

  @Column(name = "created_at", nullable = false)
  private Instant createdAt;

  @Column(name = "updated_at", nullable = false)
  private Instant updatedAt;

}
