package com.app.dao;

import com.app.model.Trade;

import java.util.List;
import java.util.Optional;

public interface TradeDao {
    void save(Trade trade);
    void modify(Trade trade);
    void delete(Long id);
    List<Trade> getAll();
    Optional<Trade> getById(Long id);

}
