package com.social.dailylink;

import com.social.dailylink.model.User;
import com.social.dailylink.repository.UserRepository;
import lombok.NonNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class SpringSecurityAuditorAware implements AuditorAware<User> {

    final UserRepository userRepository;

    public SpringSecurityAuditorAware(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @NonNull
    public Optional<User> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        return userRepository.findByUsername(authentication.getName());
    }
}