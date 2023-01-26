package com.social.dailylink.repository;

import com.social.dailylink.generic.AbstractEntityRepository;
import com.social.dailylink.model.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PostRepository extends AbstractEntityRepository<Post> {
    Collection<Post> findAllByAuthorUsername(String username);

    @Query(value = "SELECT * FROM dailylink.post WHERE ts @@ to_tsquery('english', :searchTerm) ORDER BY ts_rank(ts, to_tsquery('english', :searchTerm)) DESC", nativeQuery = true)
    Collection<Post> findPostsBySearchTerm(String searchTerm);
}
