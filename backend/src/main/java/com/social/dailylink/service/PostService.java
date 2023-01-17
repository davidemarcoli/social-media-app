package com.social.dailylink.service;

import com.social.dailylink.generic.AbstractEntityService;
import com.social.dailylink.model.Post;

import java.util.Collection;

public interface PostService extends AbstractEntityService<Post> {
    Collection<Post> findAllByUsername(String username);
}
