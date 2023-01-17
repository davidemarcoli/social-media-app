package com.social.dailylink.service;

import com.social.dailylink.generic.AbstractEntityRepository;
import com.social.dailylink.generic.AbstractEntityServiceImpl;
import com.social.dailylink.model.Post;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PostServiceImpl extends AbstractEntityServiceImpl<Post> implements PostService {
    public PostServiceImpl(AbstractEntityRepository<Post> repository) {
        super(repository);
    }
}
