package com.social.dailylink.repository;

import com.social.dailylink.generic.AbstractEntityRepository;
import com.social.dailylink.model.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends AbstractEntityRepository<Category> {

    Optional<Category> findByName(String name);

    boolean existsByColor(String color);

    @Query(value = "CALL insert_category (?, ?)", nativeQuery = true)
    Category callInsertProcedure(String name, String color);

}
