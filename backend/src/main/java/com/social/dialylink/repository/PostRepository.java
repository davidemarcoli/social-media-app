package com.social.dialylink.repository;

import com.social.dialylink.generic.CrudRepository;
import com.social.dialylink.models.Post;

import java.util.Optional;

public interface PostRepository extends CrudRepository<Post, Integer> {
    Optional<Post> findByTitle(String title);
}
