package com.social.dailylink.model.dto;

import com.social.dailylink.generic.AbstractEntityDTO;
import com.social.dailylink.model.Authority;
import com.social.dailylink.model.ERole;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleDTO extends AbstractEntityDTO {
    ERole name;
    Set<Authority> authorities;
}