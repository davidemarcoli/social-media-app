package com.social.dailylink.services;

import com.social.dailylink.models.User;
import com.social.dailylink.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User save(User user) {
        try {
            userRepository.save(user);
            return user;
        } catch (Exception exception) {
            return null;
        }
    }

    public User update(UUID uuid, User user) {
        try {
            User user = userRepository.findById(uuid);
            oldUser =
            if(oldUser.isPresent()) {
                return userRepository.save(oldUser);
            }
            throw new Exception("ERROR: Updated User is empty");
        } catch (Exception exception) {
            return null;
        }
    }

    public void deleteById(UUID uuid) {
        userRepository.deleteById(uuid);
    }

    public User findById(UUID uuid) {
        try {
            return userRepository.findById(uuid);
        } catch(Exception exception) {
            return null;
        }
    }

    public List<User> findAll() {
        try {
            return userRepository.findAll();
        } catch(Exception exception) {
            return null;
        }
    }

}
