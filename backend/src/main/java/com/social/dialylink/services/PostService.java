package com.social.dialylink.services;

import com.social.dialylink.exception.EntityAlreadyExistsException;
import com.social.dialylink.repository.PostRepository;
import com.social.dialylink.repository.UserRepository;
import com.social.dialylink.generic.CrudService;
import com.social.dialylink.models.Post;
import com.social.dialylink.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PostService implements CrudService<Post, Integer> {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;
    @Override
    public Post save(Post post) {
        if (postRepository.findByTitle(post.getTitle()).isPresent()) {
            throw new EntityAlreadyExistsException("Post " + post.getTitle() + " already exists");
        }

        // get current user
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("username: " + username);
        User author = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User with username " + username + " not found"));
        post.setAuthor(author);

        return postRepository.save(post);
    }

    @Override
    public Post update(Integer id, Post post) {
        post.setId(id);
        if (postRepository.findById(id).isPresent()) {
            return postRepository.save(post);
        }
        return null;
    }

    @Override
    public void deleteById(Integer id) {
        postRepository.deleteById(id);
    }

    @Override
    public Post findById(Integer id) {
        return postRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Post with id " + id + " not found"));
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }
}
