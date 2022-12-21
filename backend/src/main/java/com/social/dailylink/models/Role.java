package com.social.dailylink.models;

import com.social.dailylink.generic.AbstractEntity;
import com.social.dailylink.global.GlobalStrings;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Collections;
import java.util.Set;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Table(name = "roles", schema = GlobalStrings.SCHEMA_NAME)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role extends AbstractEntity {
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    ERole name;

    @ManyToMany
    @JoinTable(  name = "role_authorities",
            schema = GlobalStrings.SCHEMA_NAME,
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "authorities_id"))
    Set<Authority> authorities = Collections.emptySet();
}