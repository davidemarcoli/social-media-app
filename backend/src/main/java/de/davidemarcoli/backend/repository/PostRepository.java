package de.davidemarcoli.backend.repository;

import de.davidemarcoli.backend.generic.CrudRepository;
import de.davidemarcoli.backend.models.Post;

import java.util.Optional;

public interface PostRepository extends CrudRepository<Post, Integer> {
    Optional<Post> findByTitle(String title);
}
