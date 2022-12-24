package com.social.dailylink.model.dto;

import com.social.dailylink.generic.AbstractEntityDTO;
import com.social.dailylink.model.Category;
import com.social.dailylink.model.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostDTO extends AbstractEntityDTO {
    private String title;
    private String content;
    private User author;
    private Set<Category> categories;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
