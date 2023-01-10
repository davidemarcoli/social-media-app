package com.social.dailylink.model.dto;

import com.social.dailylink.generic.AbstractEntityDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthorityDTO extends AbstractEntityDTO {
    String name;
}