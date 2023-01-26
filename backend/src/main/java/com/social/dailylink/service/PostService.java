package com.social.dailylink.service;

import com.social.dailylink.generic.AbstractEntityService;
import com.social.dailylink.model.Post;

import java.util.Collection;

public interface PostService extends AbstractEntityService<Post> {
    Collection<Post> findAllByUsername(String username);
    Post like(String id, String username);
    Collection<Post> findPostsBySearchTerm(String searchTerm);
}
