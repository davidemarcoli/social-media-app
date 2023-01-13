package com.social.dailylink;

import com.social.dailylink.model.User;
import com.social.dailylink.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;

@Configuration
@EnableJpaAuditing
@Component
public class JpaEnversConfiguration {

    final UserRepository userRepository;

    public JpaEnversConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public AuditorAware<User> auditorProvider() {
        return new SpringSecurityAuditorAware(userRepository);
    }
}
