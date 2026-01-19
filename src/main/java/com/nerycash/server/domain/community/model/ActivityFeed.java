package com.nerycash.server.domain.community.model;

import java.time.Instant;
import java.util.UUID;

import com.nerycash.server.domain.community.enums.ActivityType;
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

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "activity_feed")
public class ActivityFeed {

  @EqualsAndHashCode.Include
  @Id
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false, updatable = false)
  private User user;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private ActivityType type;

  @Column(name = "reference_id", nullable = false)
  private UUID referenceId;

  private String content;

  @Column(name = "created_at", nullable = false)
  private Instant createdAt;

}
