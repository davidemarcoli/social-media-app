package com.social.dailylink.service;

import com.social.dailylink.generic.AbstractEntityRepository;
import com.social.dailylink.generic.AbstractEntityServiceImpl;
import com.social.dailylink.model.User;
import com.social.dailylink.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

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

    @Override
    public User follow(String id, String username) {
        User user = findByUsername(username);
        Optional<User> userToFollow = repository.findById(UUID.fromString(id));
        if (userToFollow.isPresent()) {
            User u = userToFollow.get();
            System.out.println(u.getFollowers());
            if (u.getFollowers().contains(user)) {
                u.getFollowers().remove(user);
            } else {
                u.getFollowers().add(user);
            }
            return save(u);
        } else {
            throw new EntityNotFoundException("User not found");
        }
    }
}
