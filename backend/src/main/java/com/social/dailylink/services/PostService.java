package com.social.dailylink.services;

import com.social.dailylink.generic.AbstractEntityService;
import com.social.dailylink.models.Post;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public interface PostService extends AbstractEntityService<Post> {

}
