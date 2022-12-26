package com.social.dailylink.model.dto;

import com.social.dailylink.generic.AbstractEntityDTO;
import com.social.dailylink.model.Post;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryDTO extends AbstractEntityDTO {

    String name;
    String color;
    Set<Post> posts;

}
