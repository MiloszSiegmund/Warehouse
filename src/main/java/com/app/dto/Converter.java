package com.app.dto;

import com.app.model.Product;
import org.springframework.stereotype.Component;

@Component
public class Converter {
    public ProductDTO convertFromProductToProductDto(Product product)
    {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getColour(),
                product.getDateOfProduction(),
                product.getProducer().getId(),
                product.getProducer().getName(),
                product.getProducer().getCity(),
                product.getCategory().getId(),
                product.getCategory().getName()
        );
    }
}
