package com.social.dailylink.service;

import com.social.dailylink.generic.AbstractEntityRepository;
import com.social.dailylink.generic.AbstractEntityServiceImpl;
import com.social.dailylink.model.User;
import com.social.dailylink.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends AbstractEntityServiceImpl<User> implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(AbstractEntityRepository<User> repository) {
        super(repository);
        this.userRepository = (UserRepository) repository;
    }


    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() ->
                new EntityNotFoundException("User with username " + username + " not found!"));
    }
}
