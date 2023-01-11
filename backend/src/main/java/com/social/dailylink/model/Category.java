package com.social.dailylink.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.social.dailylink.generic.AbstractEntity;
import com.social.dailylink.global.GlobalStrings;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "category", schema = GlobalStrings.SCHEMA_NAME)
public class Category extends AbstractEntity {

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true)
    private String color;

    @JsonIgnore
    @ManyToMany(mappedBy = "categories")
    private Set<Post> posts;

}
