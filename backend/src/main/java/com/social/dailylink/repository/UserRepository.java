package com.social.dailylink.repository;

import com.social.dailylink.generic.AbstractEntityRepository;
import com.social.dailylink.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends AbstractEntityRepository<User> {
  Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
