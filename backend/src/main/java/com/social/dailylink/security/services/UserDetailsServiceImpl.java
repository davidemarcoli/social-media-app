package com.social.dailylink.security.services;

import com.social.dailylink.repository.RoleRepository;
import com.social.dailylink.repository.UserRepository;
import com.social.dailylink.models.ERole;
import com.social.dailylink.models.Role;
import com.social.dailylink.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }

    public long getUserCount() {
        return userRepository.count();
    }

    public void createDefaultUsers() {
        final Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role USER is not found."));
        final Role moderatorRole = roleRepository.findByName(ERole.ROLE_MODERATOR).orElseThrow(() -> new RuntimeException("Error: Role MODERATOR is not found."));
        final Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Error: Role ADMIN is not found."));

        User user = new User();
        user.setId(UUID.fromString("8db5d997-5659-4237-b8b1-2b6593c044d5"));
        user.setUsername("user");
        user.setPassword(encoder.encode("user"));
        user.setEmail("user@user.ch");
        user.setRoles(Set.of(userRole));
        userRepository.save(user);

        User moderator = new User();
        user.setId(UUID.fromString("ae99ef2b-39ff-4589-8f73-2e604a146a1d"));
        moderator.setUsername("moderator");
        moderator.setPassword(encoder.encode("moderator"));
        moderator.setEmail("moderator@moderator.ch");
        moderator.setRoles(Set.of(userRole, moderatorRole));
        userRepository.save(moderator);

        User admin = new User();
        user.setId(UUID.fromString("dc5a75e8-1a09-4aa0-bf16-6be8e6a1db07"));
        admin.setUsername("admin");
        admin.setPassword(encoder.encode("admin"));
        admin.setEmail("admin@admin.ch");
        admin.setRoles(Set.of(userRole, moderatorRole, adminRole));
        userRepository.save(admin);
    }

}
