package com.nerycash.server.domain.product.model;

import java.time.Instant;
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
@Table(name = "categories")
public class Category {

  @EqualsAndHashCode.Include
  @Id
  private UUID id;

  @Column(nullable = false, unique = true, length = 50)
  private String name;

  @Column(nullable = false, unique = true, length = 50)
  private String slug;

  @Column(name = "created_at", nullable = false)
  private Instant createdAt;
}
