package com.social.dailylink.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.social.dailylink.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
  @Serial
  private static final long serialVersionUID = 1L;

  private final UUID id;

  private final String username;

  private final String email;

  @JsonIgnore
  private final String password;
  private final Collection<? extends GrantedAuthority> authorities;
  private String profilePictureURL;

  public UserDetailsImpl(UUID id, String username, String email, String password, String profilePictureURL,
                         Collection<? extends GrantedAuthority> authorities) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.authorities = authorities;
    this.profilePictureURL = profilePictureURL;
  }

  public static UserDetailsImpl build(User user) {
    List<GrantedAuthority> authorities = user.getRoles().stream().map(role -> role.getAuthorities().stream()
            .map(authority -> new SimpleGrantedAuthority(authority.getName()))
            .collect(Collectors.toList())).flatMap(Collection::stream).collect(Collectors.toList());

    return new UserDetailsImpl(
        user.getId(), 
        user.getUsername(), 
        user.getEmail(),
        user.getPassword(),
        user.getProfilePictureURL(),
        authorities);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  public UUID getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  public String getProfilePictureURL() {
    return profilePictureURL;
  }

  public void setProfilePictureURL(String profilePictureURL) {
    this.profilePictureURL = profilePictureURL;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    UserDetailsImpl user = (UserDetailsImpl) o;
    return Objects.equals(id, user.id);
  }
}
