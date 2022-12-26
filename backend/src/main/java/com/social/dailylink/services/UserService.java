package com.social.dailylink.services;

import com.social.dailylink.generic.AbstractEntityService;
import com.social.dailylink.models.User;

public interface UserService extends AbstractEntityService<User> {
    User findByUsername(String username);
}
