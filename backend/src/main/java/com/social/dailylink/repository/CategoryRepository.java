package com.social.dailylink.repository;

import com.social.dailylink.generic.CrudRepository;
import com.social.dailylink.models.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {

    Optional<Category> findByName(String name);

    boolean existsByColor(String color);

    @Query(value = "CALL insert_category (?, ?)", nativeQuery = true)
    Category callInsertProcedure(String name, String color);

}
