package com.app.dao;

import com.app.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryDao {
    void save(Category category);
    void modify(Category category);
    void delete(Long id);
    List<Category> getAll();
    Optional<Category> getById(Long id);
}
