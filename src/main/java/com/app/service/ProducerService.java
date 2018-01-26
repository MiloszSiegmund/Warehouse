package com.app.service;

import com.app.dto.ProducerDTO;
import com.app.model.Producer;

import java.util.List;
import java.util.Optional;

public interface ProducerService {
    void saveProducer(Producer producer);
    void modifyProducer(Producer producer);
    void deleteProducer(Long id);
    Optional<Producer> getById(Long id);
    List<ProducerDTO> getAll();

}
