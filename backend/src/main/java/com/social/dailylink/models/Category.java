package com.social.dailylink.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.social.dailylink.global.GlobalStrings;
import lombok.*;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Table(name = "category", schema = GlobalStrings.SCHEMA_NAME)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true)
    private String color;

    @JsonIgnore
    @ManyToMany(mappedBy = "categories")
    private Set<Post> posts;

}
