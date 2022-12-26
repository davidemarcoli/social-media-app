package com.social.dailylink.controller;

import com.social.dailylink.generic.AbstractEntityController;
import com.social.dailylink.generic.AbstractEntityService;
import com.social.dailylink.generic.DTOMapper;
import com.social.dailylink.model.Post;
import com.social.dailylink.model.dto.PostDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostController extends AbstractEntityController<Post, PostDTO> {

    public PostController(AbstractEntityService<Post> service, DTOMapper<Post, PostDTO> mapper) {
        super(service, mapper);
    }

    @Override
    @DeleteMapping("/{id}")
    // preAuthorize: only admin or owner can delete a post
    @PreAuthorize("hasRole('ADMIN') or @postRepository.findById(#id).get().user.username == authentication.name")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
