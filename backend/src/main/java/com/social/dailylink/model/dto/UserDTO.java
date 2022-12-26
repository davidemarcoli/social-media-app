package com.social.dailylink.model.dto;

import com.social.dailylink.generic.AbstractEntityDTO;
import com.social.dailylink.model.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO extends AbstractEntityDTO {
  String username;
  String email;
  String profilePictureURL;
  Set<Role> roles = new HashSet<>();
}
