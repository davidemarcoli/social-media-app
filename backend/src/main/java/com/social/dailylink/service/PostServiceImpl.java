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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PostServiceImpl extends AbstractEntityServiceImpl<Post> implements PostService {
    public PostServiceImpl(AbstractEntityRepository<Post> repository) {
        super(repository);
    }
}
