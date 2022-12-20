package com.social.dailylink.services;

import com.social.dailylink.generic.AbstractEntityRepository;
import com.social.dailylink.generic.AbstractEntityService;
import com.social.dailylink.generic.AbstractEntityServiceImpl;
import com.social.dailylink.models.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends AbstractEntityServiceImpl<Category> implements CategoryService {
    public CategoryServiceImpl(AbstractEntityRepository<Category> repository) {
        super(repository);
    }
}
