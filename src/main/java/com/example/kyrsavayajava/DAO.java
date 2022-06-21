package com.example.kyrsavayajava;

import java.util.List;

public interface DAO<T> {
    void create(T entity);

    void update(T entity);

    T findById(Long id);

    List<T> findAll();
}
