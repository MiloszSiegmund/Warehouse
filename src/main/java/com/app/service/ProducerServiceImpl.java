package com.app.service;

import com.app.dao.ProducerDao;
import com.app.dto.ProducerDTO;
import com.app.model.Producer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProducerServiceImpl implements ProducerService {

    private ProducerDao producerDao;
    private ModelMapper modelMapper;

    @Autowired
    public ProducerServiceImpl(ProducerDao producerDao, ModelMapper modelMapper) {
        this.producerDao = producerDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveProducer(Producer producer) {
        producerDao.save(producer);

    }

    @Override
    public void modifyProducer(Producer producer) {
        producerDao.modify(producer);
    }

    @Override
    public void deleteProducer(Long id) {
        producerDao.delete(id);

    }

    @Override
    public Optional<Producer> getById(Long id) {
        return producerDao.getById(id);
    }

    @Override
    public List<ProducerDTO> getAll() {
        List<Producer> producers = producerDao.getAll();
        return producers.stream().map(p -> modelMapper.map(p, ProducerDTO.class)).collect(Collectors.toList());

    }
}
