package com.social.dailylink.generic;

import java.util.List;

public interface CrudService<T, D> {
    T save(T t);
    T update(D id, T t);
    void deleteById(D id);
    T findById(D id);

    T findById(Integer id);

    List<T> findAll();
}
