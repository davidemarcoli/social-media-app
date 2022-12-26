package com.social.dailylink.generic;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface AbstractEntityService<T extends AbstractEntity> {
    List<T> findAll();

    T findById(String id) throws EntityNotFoundException;

    T create(T entity);

    T save(T entity);

    T updateById(String id, T entity) throws EntityNotFoundException;

    void deleteById(String id) throws EntityNotFoundException;

    boolean existsById(String id);

    T createIfNotExist(T entity);

    T findByValue(T entity);
}
