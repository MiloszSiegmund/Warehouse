package com.app.service;

import com.app.dto.ProductDTO;
import com.app.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    void saveProduct(Product product);
    void modifyProduct(Product product);
    void deleteProduct(Long id);
    Optional<Product> getById(Long id);
    List<ProductDTO> getAll();
}
