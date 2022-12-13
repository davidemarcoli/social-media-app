package com.social.dailylink.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Table(name = "category", schema = "dailylink")
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
