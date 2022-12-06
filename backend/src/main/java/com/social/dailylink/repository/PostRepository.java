package com.social.dailylink.repository;

import com.social.dailylink.generic.CrudRepository;
import com.social.dailylink.models.Post;

import java.util.Optional;

public interface PostRepository extends CrudRepository<Post, Integer> {
    Optional<Post> findByTitle(String title);
}
