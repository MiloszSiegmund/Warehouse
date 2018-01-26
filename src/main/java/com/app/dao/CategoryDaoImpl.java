package com.app.dao;

import com.app.model.Category;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
public class CategoryDaoImpl implements CategoryDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void save(Category category) {
        if (category != null)
        {
            entityManager.persist(category);
        }

    }

    @Override
    public void modify(Category category) {
        if (category != null)
        {
            Category categoryDB = entityManager.find(Category.class, category.getId());
            categoryDB.setName(category.getName());
        }

    }

    @Override
    public void delete(Long id) {
        if(id != null)
        {
            Category categoryDB = entityManager.find(Category.class, id);
            entityManager.remove(categoryDB);
        }

    }

    @Override
    public List<Category> getAll() {
        Query query = entityManager.createQuery("select c from Category c");
        return query.getResultList();
    }

    @Override
    public Optional<Category> getById(Long id) {
        Category category = entityManager.find(Category.class, id);
        if( category.getName() != null)
        {
            category.getName().length();
        }
        return Optional.ofNullable(category);
    }
}
