package com.social.dailylink.repository;

import com.social.dailylink.generic.AbstractEntityRepository;
import com.social.dailylink.model.ERole;
import com.social.dailylink.model.Role;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends AbstractEntityRepository<Role> {
    Optional<Role> findByName(ERole name);
}
