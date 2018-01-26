package com.app.service;

import com.app.dto.CategoryDTO;
import com.app.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    void saveCategory(Category category);
    void modifyCategory(Category category);
    void deleteCategory(Long id);
    Optional<Category> getById(Long id);
    List<CategoryDTO> getAll();
}
