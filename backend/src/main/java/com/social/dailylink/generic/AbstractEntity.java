package com.social.dailylink.generic;


import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    protected UUID id;

    public UUID getId() {
        return id;
    }

    public AbstractEntity setId(UUID id) {
        this.id = id;
        return this;
    }
}
