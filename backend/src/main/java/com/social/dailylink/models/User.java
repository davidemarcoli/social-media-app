package com.social.dailylink.models;

import com.social.dailylink.generic.AbstractEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import com.social.dailylink.global.GlobalStrings;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "users", schema = GlobalStrings.SCHEMA_NAME,
    uniqueConstraints = { 
      @UniqueConstraint(columnNames = "username"),
      @UniqueConstraint(columnNames = "email"),
    })
public class User extends AbstractEntity {
  @NotBlank
  @Size(max = 25)
  @Column(name = "username", nullable = false)
  private String username;

  @NotBlank
  @Email
  @Column(name = "email", unique = true, nullable = false)
  private String email;

  @NotBlank
  @Size(max = 120)
  @Column(name = "password", nullable = false)
  private String password;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(  name = "user_roles",
          schema = GlobalStrings.SCHEMA_NAME,
          joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  public User(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
  }
}
