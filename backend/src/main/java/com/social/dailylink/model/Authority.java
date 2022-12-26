package com.social.dailylink.model;

import com.social.dailylink.generic.AbstractEntity;
import com.social.dailylink.global.GlobalStrings;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Table(name = "authorities", schema = GlobalStrings.SCHEMA_NAME)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Authority extends AbstractEntity {
    String name;
}
