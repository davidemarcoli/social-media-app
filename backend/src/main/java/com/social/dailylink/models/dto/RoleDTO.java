package com.social.dailylink.models.dto;

import com.social.dailylink.generic.AbstractEntity;
import com.social.dailylink.generic.AbstractEntityDTO;
import com.social.dailylink.global.GlobalStrings;
import com.social.dailylink.models.Authority;
import com.social.dailylink.models.ERole;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleDTO extends AbstractEntityDTO {
    ERole name;
    Set<Authority> authorities;
}