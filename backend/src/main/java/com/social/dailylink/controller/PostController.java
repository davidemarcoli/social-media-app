package com.social.dailylink.controller;

import com.social.dailylink.generic.AbstractEntityController;
import com.social.dailylink.generic.AbstractEntityService;
import com.social.dailylink.generic.DTOMapper;
import com.social.dailylink.model.Post;
import com.social.dailylink.model.dto.PostDTO;
import com.social.dailylink.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/posts")
public class PostController extends AbstractEntityController<Post, PostDTO> {

    PostService postService;

    public PostController(AbstractEntityService<Post> service, DTOMapper<Post, PostDTO> mapper) {
        super(service, mapper);
        this.postService = (PostService) service;
    }

    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @postRepository.findById(#id).get().author.username == authentication.name")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping("/")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Collection<PostDTO>> findAll() {
        return super.findAll();
    }

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<PostDTO> findById(@PathVariable String id) {
        return super.findById(id);
    }

    @Override
    @PostMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<PostDTO> create(@RequestBody PostDTO dto) {
        return super.create(dto);
    }

    @Override
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #dto.author.username == authentication.name")
    public ResponseEntity<PostDTO> updateById(@PathVariable String id, @RequestBody PostDTO dto) {
        return super.updateById(id, dto);
    }

    @GetMapping("/user/{username}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Collection<PostDTO>> findAllByUsername(@PathVariable String username) {
        return ResponseEntity.ok(mapper.toDTOs(postService.findAllByUsername(username)));
    }
}
