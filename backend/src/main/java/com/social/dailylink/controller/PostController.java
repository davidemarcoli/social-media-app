package com.social.dailylink.controller;

import com.social.dailylink.generic.AbstractEntityController;
import com.social.dailylink.generic.AbstractEntityService;
import com.social.dailylink.generic.DTOMapper;
import com.social.dailylink.model.Post;
import com.social.dailylink.model.dto.CreatePostDTO;
import com.social.dailylink.model.dto.PostDTO;
import com.social.dailylink.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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

    @PostMapping("/")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<PostDTO> create(@RequestBody CreatePostDTO dto)  {
        PostDTO result = new PostDTO();
        result.setContent(dto.getContent());
        result.setMedia(dto.getMedia());

        return super.create(new PostDTO());
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

//    @PutMapping("/like/{postId}")
//    @PreAuthorize("hasRole('USER')")
//    public ResponseEntity<PostDTO> likePost(@PathVariable String postId, Authentication authentication) {
//        return ResponseEntity.ok(mapper.toDTO(postService.like(postId, authentication.getName())));
//    }

    @PutMapping("/like")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<PostDTO> likePost(@RequestBody PostDTO post, Authentication authentication) {
        System.out.println("Request Body: " + post.getLikes());
        Post postEntity = postService.like(post.getId().toString(), authentication.getName());
        System.out.println("Updated Entity: " + postEntity.getLikes());
        PostDTO postDTO = mapper.toDTO(postEntity);
        System.out.println("Updated DTO: " + postDTO.getLikes());
        return ResponseEntity.ok(postDTO);
    }
}
