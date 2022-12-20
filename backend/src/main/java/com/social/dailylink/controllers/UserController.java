package com.social.dailylink.controllers;

import com.social.dailylink.generic.CrudController;
import com.social.dailylink.models.Post;
import com.social.dailylink.models.User;
import com.social.dailylink.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController implements CrudController<User, UUID> {

    @Autowired
    UserService userService;

    @Override
    @PostMapping("/")
    public ResponseEntity<User> create(@RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @Override
    @PutMapping("/{uuid}")
    public ResponseEntity<User> update(@PathVariable UUID uuid, @RequestBody User user) {
        return ResponseEntity.ok(userService.update(uuid, user));
    }

    @Override
    @DeleteMapping("/{uuid}")
    // preAuthorize: only admin or owner can delete a post
    @PreAuthorize("hasRole('ADMIN') or @postRepository.findById(#id).get().user.username == authentication.name")
    public ResponseEntity<Void> deleteById(@PathVariable UUID uuid) {
        userService.deleteById(uuid);
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping("/{uuid}")
    public ResponseEntity<User> findById(@PathVariable UUID uuid) {
        return ResponseEntity.ok(userService.findById(uuid));
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }
}
