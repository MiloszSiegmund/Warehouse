package com.app.service;

import com.app.dto.TradeDTO;
import com.app.model.Trade;

import java.util.List;
import java.util.Optional;

public interface TradeService {
    void saveTrade(Trade trade);
    void modifyTrade(Trade trade);
    void deleteTrade(Long id);
    Optional<Trade> getById(Long id);
    List<TradeDTO> getAll();
}
