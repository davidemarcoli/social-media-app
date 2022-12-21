package com.social.dailylink.controllers;

import com.social.dailylink.generic.AbstractEntityController;
import com.social.dailylink.generic.AbstractEntityService;
import com.social.dailylink.generic.DTOMapper;
import com.social.dailylink.models.Category;
import com.social.dailylink.models.dto.CategoryDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController extends AbstractEntityController<Category, CategoryDTO> {

    public CategoryController(AbstractEntityService<Category> service, DTOMapper<Category, CategoryDTO> mapper) {
        super(service, mapper);
    }


    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDTO> create(@RequestBody Category category) {
        return new ResponseEntity<>(mapper.toDTO(service.save(category)), HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDTO> updateById(@PathVariable String id, @RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(mapper.toDTO(service.updateById(id, mapper.fromDTO(categoryDTO))));
    }

    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
