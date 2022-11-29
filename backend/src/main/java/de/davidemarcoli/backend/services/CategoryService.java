package de.davidemarcoli.backend.services;

import de.davidemarcoli.backend.exception.EntityAlreadyExistsException;
import de.davidemarcoli.backend.exception.EntityInUseException;
import de.davidemarcoli.backend.generic.CrudService;
import de.davidemarcoli.backend.models.Category;
import de.davidemarcoli.backend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
