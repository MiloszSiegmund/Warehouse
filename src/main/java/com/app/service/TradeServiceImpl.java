package com.app.service;

import com.app.dao.TradeDao;
import com.app.dto.TradeDTO;
import com.app.model.Trade;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TradeServiceImpl implements TradeService {

    private TradeDao tradeDao;
    private ModelMapper modelMapper;

    @Autowired
    public TradeServiceImpl(TradeDao tradeDao, ModelMapper modelMapper) {
        this.tradeDao = tradeDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveTrade(Trade trade) {
        tradeDao.save(trade);

    }

    @Override
    public void modifyTrade(Trade trade) {
        tradeDao.modify(trade);

    }

    @Override
    public void deleteTrade(Long id) {
        tradeDao.delete(id);

    }

    @Override
    public Optional<Trade> getById(Long id) {
        return tradeDao.getById(id);
    }

    @Override
    public List<TradeDTO> getAll() {
        List<Trade> trades = tradeDao.getAll();
        return trades.stream().map(t -> modelMapper.map(t, TradeDTO.class)).collect(Collectors.toList());
    }
}
