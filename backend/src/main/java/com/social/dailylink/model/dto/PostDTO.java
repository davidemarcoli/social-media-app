package com.social.dailylink.model.dto;

import com.social.dailylink.generic.AbstractEntityDTO;
import com.social.dailylink.model.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostDTO extends AbstractEntityDTO {
    private String content;
    private User author;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private byte[] media;
}
