package com.app.dao;

import com.app.model.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductDaoImpl implements ProductDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Product product) {

        if(product != null)
        {
            entityManager.persist(product);
        }

    }

    @Override
    public void modify(Product product) {

        if (product != null)
        {
            Product productDB = entityManager.find(Product.class, product.getId());
            productDB.setName(product.getName());
            productDB.setPrice(product.getPrice());
            productDB.setColour(product.getColour());
            productDB.setDateOfProduction(product.getDateOfProduction());
        }

    }

    @Override
    public void delete(Long id) {

        if(id != null)
        {
            Product productDB = entityManager.find(Product.class, id);
            entityManager.remove(productDB);
        }

    }

    @Override
    public List<Product> getAll() {

        Query query = entityManager.createQuery("select p from Product p");
        return query.getResultList();
    }

    @Override
    public Optional<Product> getById(Long id) {
        Product product = entityManager.find(Product.class, id);
        if (product.getColour() != null) {
            product.getColour().length();
        }

        return Optional.ofNullable(product);
    }
}
