package com.social.dailylink.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.social.dailylink.generic.AbstractEntityDTO;
import com.social.dailylink.global.GlobalStrings;
import com.social.dailylink.models.Post;
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
