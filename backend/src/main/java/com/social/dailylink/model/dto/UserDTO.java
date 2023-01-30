package com.social.dailylink.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.social.dailylink.generic.AbstractEntityDTO;
import com.social.dailylink.global.GlobalStrings;
import com.social.dailylink.model.Post;
import com.social.dailylink.model.Role;
import com.social.dailylink.model.User;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO extends AbstractEntityDTO {
    String username;
    String email;
    String profilePictureURL;
    Set<Role> roles;
    Set<Post> posts;
    Set<Post> likedPosts;
    Set<User> followers;
    Set<User> following;
}
