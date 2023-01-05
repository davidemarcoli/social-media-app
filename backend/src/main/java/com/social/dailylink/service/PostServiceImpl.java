package com.social.dailylink.service;

import com.social.dailylink.exception.exitems.EntityAlreadyExistsException;
import com.social.dailylink.generic.AbstractEntityRepository;
import com.social.dailylink.generic.AbstractEntityServiceImpl;
import com.social.dailylink.model.Post;
import com.social.dailylink.model.User;
import com.social.dailylink.repository.PostRepository;
import com.social.dailylink.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PostServiceImpl extends AbstractEntityServiceImpl<Post> implements PostService {

    UserRepository userRepository;

    public PostServiceImpl(AbstractEntityRepository<Post> repository, UserRepository userRepository) {
        super(repository);
        this.userRepository = userRepository;
    }

    @Override
    public Post create(Post entity) {
        // get current user
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("username: " + username);
        User author = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User with username " + username + " not found"));
        entity.setAuthor(author);

        return super.create(entity);
    }

    @Override
    public List<Post> findAll() {
        return super.findAll();
    }

    @Override
    public Post save(Post entity) {
        return super.save(entity);
    }

    @Override
    public Post updateById(String id, Post entity) throws EntityNotFoundException {
        return super.updateById(id, entity);
    }

}