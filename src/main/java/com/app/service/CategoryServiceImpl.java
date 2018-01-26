package com.app.service;

import com.app.dao.CategoryDao;
import com.app.dto.CategoryDTO;
import com.app.model.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

    private CategoryDao categoryDao;
    private ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryDao categoryDao, ModelMapper modelMapper) {
        this.categoryDao = categoryDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveCategory(Category category) {
        categoryDao.save(category);

    }

    @Override
    public void modifyCategory(Category category) {
        categoryDao.modify(category);

    }

    @Override
    public void deleteCategory(Long id) {
        categoryDao.delete(id);

    }

    @Override
    public Optional<Category> getById(Long id) {
        return categoryDao.getById(id);
    }

    @Override
    public List<CategoryDTO> getAll() {
        List<Category> categories = categoryDao.getAll();
        return categories.stream().map(c -> modelMapper.map(c, CategoryDTO.class)).collect(Collectors.toList());

    }
}