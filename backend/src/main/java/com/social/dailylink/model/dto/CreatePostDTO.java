package com.social.dailylink.model.dto;

import com.social.dailylink.generic.AbstractEntityDTO;
import com.social.dailylink.model.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreatePostDTO {
    String content;
    String username;
    byte[] media;
}
