package de.davidemarcoli.backend.security.services;

import de.davidemarcoli.backend.models.ERole;
import de.davidemarcoli.backend.models.Role;
import de.davidemarcoli.backend.models.User;
import de.davidemarcoli.backend.repository.RoleRepository;
import de.davidemarcoli.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

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
        user.setUsername("user");
        user.setPassword(encoder.encode("user"));
        user.setEmail("user@user.ch");
        user.setRoles(Set.of(userRole));
        userRepository.save(user);

        User moderator = new User();
        moderator.setUsername("moderator");
        moderator.setPassword(encoder.encode("moderator"));
        moderator.setEmail("moderator@moderator.ch");
        moderator.setRoles(Set.of(userRole, moderatorRole));
        userRepository.save(moderator);

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(encoder.encode("admin"));
        admin.setEmail("admin@admin.ch");
        admin.setRoles(Set.of(userRole, moderatorRole, adminRole));
        userRepository.save(admin);
    }

}
