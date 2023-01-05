package com.social.dailylink.controller;

import com.social.dailylink.generic.AbstractEntityController;
import com.social.dailylink.generic.AbstractEntityService;
import com.social.dailylink.generic.DTOMapper;
import com.social.dailylink.model.User;
import com.social.dailylink.model.dto.UserDTO;
import com.social.dailylink.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController extends AbstractEntityController<User, UserDTO> {
    private final UserService userService;

    public UserController(AbstractEntityService<User> userService, DTOMapper<User, UserDTO> mapper) {
        super(userService, mapper);
        this.userService = (UserService) userService;
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> findByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.findByUsername(username));
    }

    @Override
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<UserDTO> updateById(@PathVariable String id, @RequestBody UserDTO dto) {
        return super.updateById(id, dto);
    }

    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        return super.deleteById(id);
    }
}