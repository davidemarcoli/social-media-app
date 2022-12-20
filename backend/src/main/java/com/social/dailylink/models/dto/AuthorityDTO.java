package com.social.dailylink.models.dto;

import com.social.dailylink.generic.AbstractEntityDTO;
import com.social.dailylink.models.Authority;
import com.social.dailylink.models.ERole;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthorityDTO extends AbstractEntityDTO {
    String name;
}