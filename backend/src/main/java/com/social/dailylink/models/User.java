package com.social.dailylink.models;

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
@NoArgsConstructor
@Entity

@Table(name = "users", schema = GlobalStrings.SCHEMA_NAME,
    uniqueConstraints = { 
      @UniqueConstraint(columnNames = "username"),
      @UniqueConstraint(columnNames = "email"),
    })
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

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

  @NotBlank
  @Column(name = "profile_picture_url", nullable = false)
  private String profilePictureURL;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(  name = "user_roles",
          schema = GlobalStrings.SCHEMA_NAME,
          joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  public User(String username, String email, String password, String profilePictureURL) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.profilePictureURL = profilePictureURL;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getProfilePictureURL() {
    return profilePictureURL;
  }

  public void setProfilePictureURL(String profilePictureURL) {
    this.profilePictureURL = profilePictureURL;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }
}
