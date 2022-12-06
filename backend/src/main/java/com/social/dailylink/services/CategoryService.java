package com.social.dailylink.services;

import com.social.dailylink.exception.EntityAlreadyExistsException;
import com.social.dailylink.exception.EntityInUseException;
import com.social.dailylink.repository.CategoryRepository;
import com.social.dailylink.generic.CrudService;
import com.social.dailylink.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryService implements CrudService<Category, Integer> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category save(Category category) {
        if (categoryRepository.findByName(category.getName()).isPresent()) {
            throw new EntityAlreadyExistsException("Category " + category.getName() + " already exists");
        } else if (categoryRepository.existsByColor(category.getColor())) {
            throw new EntityAlreadyExistsException("Category with color " + category.getColor() + " already exists");
        }
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Integer id, Category category) {
        category.setId(id);
       if (categoryRepository.findById(category.getId()).isPresent()) {
           return categoryRepository.save(category);
       }
         return null;
    }

    @Override
    public void deleteById(Integer id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            if (category.get().getPosts().isEmpty()) {
                categoryRepository.delete(category.get());
            } else {
                throw new EntityInUseException("Category " + category.get().getName() + " is in use");
            }
        } else {
            throw new EntityNotFoundException("Category with id " + id + " not found");
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public Category findById(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category with id " + id + " not found"));
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
