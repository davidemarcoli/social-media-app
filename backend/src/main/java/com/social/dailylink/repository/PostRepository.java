package com.social.dailylink.repository;

import com.social.dailylink.generic.AbstractEntityRepository;
import com.social.dailylink.model.Post;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface PostRepository extends AbstractEntityRepository<Post> {
    Optional<Post> findByTitle(String title);
    Collection<Post> findAllByAuthorUsername(String username);
}
