package com.social.dailylink.service;

import com.social.dailylink.generic.AbstractEntityService;
import com.social.dailylink.model.Post;
import com.social.dailylink.model.User;

public interface UserService extends AbstractEntityService<User> {
    User findByUsername(String username);
    User follow(String id, String username);
}
