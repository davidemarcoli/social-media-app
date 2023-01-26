package com.social.dailylink.service;

import com.social.dailylink.generic.AbstractEntityRepository;
import com.social.dailylink.generic.AbstractEntityServiceImpl;
import com.social.dailylink.model.Post;
import com.social.dailylink.model.User;
import com.social.dailylink.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class PostServiceImpl extends AbstractEntityServiceImpl<Post> implements PostService {

    UserService userService;

    public PostServiceImpl(AbstractEntityRepository<Post> repository, UserService userService) {
        super(repository);
        this.userService = userService;
    }

    @Override
    public Collection<Post> findAllByUsername(String username) {
        return ((PostRepository) repository).findAllByAuthorUsername(username);
    }

    @Override
    public Post like(String id, String username) {
        User user = userService.findByUsername(username);
        Optional<Post> post = repository.findById(UUID.fromString(id));
        if (post.isPresent()) {
            Post p = post.get();
            System.out.println("Found Entity: " + p.getLikes());
            if (p.getLikes().contains(user)) {
                p.getLikes().remove(user);
            } else {
                p.getLikes().add(user);
            }
            return save(p);
        } else {
            throw new EntityNotFoundException("Post not found");
        }
    }

    @Override
    public Collection<Post> findPostsBySearchTerm(String searchTerm) {
        return ((PostRepository) repository).findPostsBySearchTerm(searchTerm);
    }
}
