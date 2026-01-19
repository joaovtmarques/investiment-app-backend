package com.nerycash.server.domain.community.model;

import java.time.Instant;

import com.nerycash.server.domain.user.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
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
@Table(name = "follows")
public class Follow {

  @EqualsAndHashCode.Include
  @EmbeddedId
  private FollowId id;

  @MapsId("followerId")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "follower_id", nullable = false, updatable = false)
  private User follower;

  @MapsId("followingId")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "following_id", nullable = false, updatable = false)
  private User following;

  @Column(name = "created_at", nullable = false)
  private Instant createdAt;

  public Follow(User follower, User following) {
    if (follower.getId().equals(following.getId())) {
      throw new IllegalArgumentException("O usuário não pode seguir ele mesmo");
    }

    this.follower = follower;
    this.following = following;
    this.id = new FollowId(follower.getId(), following.getId());
    this.createdAt = Instant.now();
  }

}
