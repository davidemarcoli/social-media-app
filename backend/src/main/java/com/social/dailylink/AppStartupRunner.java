package com.social.dailylink;

import com.social.dailylink.models.ERole;
import com.social.dailylink.models.Role;
import com.social.dailylink.repository.RoleRepository;
import com.social.dailylink.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class AppStartupRunner implements ApplicationRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Application started with option names : " + args.getOptionNames());
        if (roleRepository.count() == 0) {
            System.out.println("No roles found, creating default roles");
            roleRepository.saveAll(Arrays.stream(ERole.values()).map(role -> {
                Role newRole = new Role();
                newRole.setName(role);
                return newRole;
            }).toList());
        }
        if (userDetailsService.getUserCount() == 0) {
            System.out.println("No users found, creating default users");
            userDetailsService.createDefaultUsers();
        }
    }
}
