package com.social.dailylink.controller;

import com.social.dailylink.global.GlobalStrings;
import com.social.dailylink.model.ERole;
import com.social.dailylink.model.Role;
import com.social.dailylink.model.User;
import com.social.dailylink.payload.request.LoginRequest;
import com.social.dailylink.payload.request.SignupRequest;
import com.social.dailylink.repository.RoleRepository;
import com.social.dailylink.repository.UserRepository;
import com.social.dailylink.security.jwt.JwtUtils;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@Log4j2
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        log.info("New Login Request from " + loginRequest.username());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        return ResponseEntity.status(HttpStatus.OK).body(jwt);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

        System.out.println("New Login Request with username " + signUpRequest.username());

        if (userRepository.existsByUsername(signUpRequest.username())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username is already taken!");
        }

        // Create new user's account
        User user = new User(signUpRequest.username(),
                signUpRequest.email(),
                encoder.encode(signUpRequest.password()), GlobalStrings.DEFAULT_PROFILE_PICTURE_URL);

        Set<String> strRoles = signUpRequest.roles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException(GlobalStrings.ERROR_ROLE_NOT_FOUND));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin" -> {
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException(GlobalStrings.ERROR_ROLE_NOT_FOUND));
                        roles.add(adminRole);
                    }
                    case "mod" -> {
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException(GlobalStrings.ERROR_ROLE_NOT_FOUND));
                        roles.add(modRole);
                    }
                    default -> {
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException(GlobalStrings.ERROR_ROLE_NOT_FOUND));
                        roles.add(userRole);
                    }
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.OK).body("User registered successfully!");
    }
}
