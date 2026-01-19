package com.nerycash.server.domain.product.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import com.nerycash.server.domain.product.enums.Status;

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
@Table(name = "products")
public class Product {

  @EqualsAndHashCode.Include
  @Id
  private UUID id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String description;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id", nullable = false, updatable = false)
  private Category category;

  @Column(name = "min_investment", nullable = false)
  private BigDecimal minInvestment;

  @Column(name = "max_units", nullable = false)
  private Integer maxUnits;

  @Column(name = "units_sold", nullable = false)
  private Integer unitsSold;

  @Column(name = "estimated_return_percent", nullable = false)
  private BigDecimal estimatedReturnPercent;

  @Column(name = "estimated_days", nullable = false)
  private Integer estimatedDays;

  @Column(name = "risk_level", nullable = false)
  private Integer riskLevel;

  @Column(name = "roi_average_30d", nullable = false)
  private BigDecimal roiAverage30d;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Status status;

  @Column(name = "created_at", nullable = false)
  private Instant createdAt;

  @Column(name = "updated_at", nullable = false)
  private Instant updatedAt;

}
