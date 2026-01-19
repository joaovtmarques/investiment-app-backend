package com.nerycash.server.domain.product.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Table(name = "product_metrics")
public class ProductMetrics {

  @EqualsAndHashCode.Include
  @Id
  private UUID id;

  @JsonIgnore
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id", nullable = false, updatable = false)
  private Product product;

  @Column(name = "current_min_investment", nullable = false)
  private BigDecimal currentMinInvestment;

  @Column(name = "current_roi", nullable = false)
  private BigDecimal currentRoi;

  @Column(name = "current_margin", nullable = false)
  private BigDecimal currentMargin;

  @Column(name = "demand_index", nullable = false)
  private BigDecimal demandIndex;

  @Column(name = "updated_at", nullable = false)
  private Instant updatedAt;

}
