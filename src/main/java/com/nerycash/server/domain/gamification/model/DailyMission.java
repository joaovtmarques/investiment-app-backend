package com.nerycash.server.domain.gamification.model;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "daily_missions")
public class DailyMission {

  @EqualsAndHashCode.Include
  @Id
  private UUID id;

  @Column(length = 100, nullable = false)
  private String title;

  @Column(name = "reward_xp", nullable = false)
  private Long rewardXp;

  @Column(name = "reward_bonus", nullable = false)
  private BigDecimal rewardBonus;

  @Column(nullable = false)
  private Boolean active;

}
