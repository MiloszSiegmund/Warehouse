package com.app.dao;

import com.app.model.Producer;

import java.util.List;
import java.util.Optional;

public interface ProducerDao {
    void save(Producer producer);
    void modify(Producer producer);
    void delete(Long id);
    List<Producer> getAll();
    Optional<Producer> getById(Long id);
}
