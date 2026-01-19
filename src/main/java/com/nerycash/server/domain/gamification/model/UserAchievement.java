package com.nerycash.server.domain.gamification.model;

import java.time.Instant;
import java.util.UUID;

import com.nerycash.server.domain.user.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "user_achievements")
public class UserAchievement {

  @EqualsAndHashCode.Include
  @Id
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false, updatable = false)
  private User user;

  @Column(length = 50, nullable = false)
  private String code;

  private String title;

  @Column(name = "earned_at", nullable = false)
  private Instant earnedAt;

}
