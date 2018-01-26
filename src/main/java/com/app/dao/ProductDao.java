package com.app.dao;

import com.app.model.Product;
import net.bytebuddy.dynamic.DynamicType;

import java.util.List;
import java.util.Optional;

public interface ProductDao {
    void save(Product product);
    void modify(Product product);
    void delete(Long id);
    List<Product> getAll();
    Optional<Product> getById(Long id);
}
