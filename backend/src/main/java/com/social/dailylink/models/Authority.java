package com.social.dailylink.models;

import com.social.dailylink.generic.AbstractEntity;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Authority extends AbstractEntity {
    String name;
}
