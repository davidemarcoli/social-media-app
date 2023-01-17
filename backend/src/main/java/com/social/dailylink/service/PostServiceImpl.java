package com.social.dailylink.service;

import com.social.dailylink.generic.AbstractEntityRepository;
import com.social.dailylink.generic.AbstractEntityServiceImpl;
import com.social.dailylink.model.Post;
import com.social.dailylink.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Transactional
public class PostServiceImpl extends AbstractEntityServiceImpl<Post> implements PostService {

    public PostServiceImpl(AbstractEntityRepository<Post> repository) {
        super(repository);
    }

    @Override
    public Collection<Post> findAllByUsername(String username) {
        return ((PostRepository) repository).findAllByAuthorUsername(username);
    }
}
