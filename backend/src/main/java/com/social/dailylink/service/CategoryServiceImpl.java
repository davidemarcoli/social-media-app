package com.social.dailylink.service;

import com.social.dailylink.generic.AbstractEntityRepository;
import com.social.dailylink.generic.AbstractEntityServiceImpl;
import com.social.dailylink.model.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends AbstractEntityServiceImpl<Category> implements CategoryService {
    public CategoryServiceImpl(AbstractEntityRepository<Category> repository) {
        super(repository);
    }
}
