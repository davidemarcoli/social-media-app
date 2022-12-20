package com.social.dailylink.models;

import com.social.dailylink.generic.AbstractEntity;
import com.social.dailylink.global.GlobalStrings;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Table(name = "roles", schema = GlobalStrings.SCHEMA_NAME)
public class Role extends AbstractEntity {
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;
}