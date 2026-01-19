package com.nerycash.server.domain.community.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class FollowId {

  @Column(name = "follower_id", nullable = false)
  private UUID followerId;

  @Column(name = "following_id", nullable = false)
  private UUID followingId;

}
