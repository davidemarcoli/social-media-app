package com.social.dailylink.models.dto;

import com.social.dailylink.generic.AbstractEntity;
import com.social.dailylink.generic.AbstractEntityDTO;
import com.social.dailylink.global.GlobalStrings;
import com.social.dailylink.models.Category;
import com.social.dailylink.models.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
