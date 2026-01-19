package com.nerycash.server.domain.notification.model;

import java.time.Instant;
import java.util.UUID;

import com.nerycash.server.domain.notification.enums.NotificationType;
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
@Table(name = "notifications")
public class Notification {

  @EqualsAndHashCode.Include
  @Id
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false, updatable = false)
  private User user;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String message;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private NotificationType type;

  @Column(name = "read", nullable = false)
  private Boolean isRead;

  @Column(name = "created_at", nullable = false)
  private Instant createdAt;

  public Notification(User user, String title, String message, NotificationType type) {
    this.id = UUID.randomUUID();
    this.user = user;
    this.title = title;
    this.message = message;
    this.type = type;
    this.isRead = false;
    this.createdAt = Instant.now();
  }

  public void markAsRead() {
    this.isRead = true;
  }

}
