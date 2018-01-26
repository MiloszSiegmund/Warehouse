package com.app.dao;

import com.app.model.Producer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
public class ProducerDaoImpl implements ProducerDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Producer producer) {
        if (producer != null)
        {
            entityManager.persist(producer);
        }

    }

    @Override
    public void modify(Producer producer) {
        if (producer != null)
        {
            Producer producerDB = entityManager.find(Producer.class, producer.getId());
            producerDB.setName(producer.getName());
            producerDB.setCity(producer.getCity());
        }

    }

    @Override
    public void delete(Long id) {
        if (id != null)
        {
            Producer producerDB = entityManager.find(Producer.class, id);
            entityManager.remove(producerDB);
        }

    }

    @Override
    public List<Producer> getAll() {
        Query query = entityManager.createQuery("select p from Producer p");
        return query.getResultList();
    }

    @Override
    public Optional<Producer> getById(Long id) {
        Producer producer = entityManager.find(Producer.class, id);
        if (producer.getName() != null)
        {
            producer.getName().length();
        }
        return Optional.ofNullable(producer);
    }
}
