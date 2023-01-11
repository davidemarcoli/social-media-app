package com.social.dailylink.service;

import com.social.dailylink.generic.AbstractEntityService;
import com.social.dailylink.model.Post;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Collection;

public interface PostService extends AbstractEntityService<Post> {
    Collection<Post> findAllByUsername(String username);
}
