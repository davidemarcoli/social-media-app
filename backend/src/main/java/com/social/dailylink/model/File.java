package com.social.dailylink.model;

import com.social.dailylink.global.GlobalStrings;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "file", schema = GlobalStrings.SCHEMA_NAME)
public class File {
    @Id
    protected UUID id;
    private String name;
    private String type;
    private String path;

    @PrePersist
    public void prePersist() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }
}
