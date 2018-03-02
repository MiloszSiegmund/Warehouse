package com.app.service;

import com.app.dao.ProductDao;
import com.app.dto.Converter;
import com.app.dto.ProductDTO;
import com.app.model.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;
    private Converter converter;

    @Autowired
    public ProductServiceImpl(ProductDao productDao, Converter converter) {
        this.productDao = productDao;
        this.converter = converter;
    }

    @Override
    public void saveProduct(Product product) {
        productDao.save(product);

    }

    @Override
    public void modifyProduct(Product product) {
        productDao.modify(product);

    }

    @Override
    public void deleteProduct(Long id) {
        productDao.delete(id);

    }

    @Override
    public Optional<Product> getById(Long id) {
        return productDao.getById(id);
    }

    @Override
    public List<ProductDTO> getAll() {
        List<Product> products = productDao.getAll();
        return products.stream().map(p -> converter.convertFromProductToProductDto(p)).collect(Collectors.toList());
    }
}
