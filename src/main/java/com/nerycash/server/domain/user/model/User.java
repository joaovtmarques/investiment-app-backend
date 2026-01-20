package com.nerycash.server.domain.user.model;

import java.time.Instant;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nerycash.server.domain.level.model.Level;
import com.nerycash.server.domain.user.enums.AuthProvider;
import com.nerycash.server.domain.wallet.model.Wallet;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "users")
public class User {

  public User(
      String name,
      String email,
      String phone,
      String passwordHash,
      AuthProvider provider,
      Level level) {
    this.id = UUID.randomUUID();
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.passwordHash = passwordHash;
    this.provider = provider;
    this.level = level;
    this.xp = 0L;
  }

  @EqualsAndHashCode.Include
  @Id
  private UUID id;

  @Column(nullable = false)
  private String name;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false, unique = true)
  private String phone;

  @JsonIgnore
  @Column(name = "password_hash")
  private String passwordHash;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private AuthProvider provider;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "level_id", nullable = false, updatable = false)
  private Level level;

  @Column(nullable = false)
  private Long xp;

  @Column(name = "created_at", nullable = false, updatable = false, insertable = false)
  private Instant createdAt;

  @Column(name = "updated_at", nullable = false, insertable = false)
  private Instant updatedAt;

  @Column(name = "is_active", nullable = false, insertable = false)
  private Boolean isActive;

  @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
  private Wallet wallet;

}
