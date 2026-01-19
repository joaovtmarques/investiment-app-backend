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
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "user_missions", uniqueConstraints = {
    @UniqueConstraint(columnNames = { "user_id", "mission_id" })
})
public class UserMission {

  public UserMission(User user, DailyMission mission) {
    this.id = UUID.randomUUID();
    this.user = user;
    this.mission = mission;
    this.completed = false;
    this.completedAt = null;
  }

  @EqualsAndHashCode.Include
  @Id
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false, updatable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "mission_id", nullable = false, updatable = false)
  private DailyMission mission;

  @Column(nullable = false)
  private Boolean completed;

  @Column(name = "completed_at")
  private Instant completedAt;

  public void complete() {
    this.completed = true;
    this.completedAt = Instant.now();
  }

}
