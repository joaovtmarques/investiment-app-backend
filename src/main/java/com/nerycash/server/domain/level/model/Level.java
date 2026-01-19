package com.nerycash.server.domain.level.model;

import java.util.UUID;

import com.nerycash.server.domain.level.enums.LevelName;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
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
@Table(name = "levels")
public class Level {

  @EqualsAndHashCode.Include
  @Id
  private UUID id;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, unique = true)
  private LevelName name;

  @Column(name = "min_xp", nullable = false)
  private Long minXp;

}
