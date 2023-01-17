package com.social.dailylink.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.social.dailylink.generic.AbstractEntity;
import com.social.dailylink.global.GlobalStrings;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users", schema = GlobalStrings.SCHEMA_NAME,
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email"),
        })
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends AbstractEntity {
    @NotBlank
    @Size(max = 25)
    @Column(name = "username", nullable = false)
    String username;

    @NotBlank
    @Email
    @Column(name = "email", unique = true, nullable = false)
    String email;

    @NotBlank
    @Size(max = 120)
    @Column(name = "password", nullable = false)
    String password;

    @NotBlank
    @Column(name = "profile_picture_url", nullable = false)
    String profilePictureURL;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            schema = GlobalStrings.SCHEMA_NAME,
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<Role> roles = new HashSet<>();

    @JsonIgnoreProperties("author")
    @OneToMany(mappedBy = "author")
    Set<Post> posts;

    @ManyToMany(mappedBy = "likes")
    Set<Post> likedPosts;

    public User(String username, String email, String password, String profilePictureURL) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.profilePictureURL = profilePictureURL;
    }
}
