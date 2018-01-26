package com.app.dao;

import com.app.model.Trade;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
public class TradeDaoImpl implements TradeDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Trade trade) {
        if (trade != null)
        {
            entityManager.persist(trade);
        }

    }

    @Override
    public void modify(Trade trade) {
        if (trade != null)
        {
            Trade tradeDB = entityManager.find(Trade.class, trade.getId());
            tradeDB.setName(trade.getName());
        }

    }

    @Override
    public void delete(Long id) {
        if (id != null)
        {
            Trade tradeDB = entityManager.find(Trade.class, id);
            entityManager.remove(tradeDB);
        }

    }

    @Override
    public List<Trade> getAll() {
        Query query = entityManager.createQuery("select t from Trade t");
        return query.getResultList();
    }

    @Override
    public Optional<Trade> getById(Long id) {
        Trade trade = entityManager.find(Trade.class, id);
        if (trade.getName() != null)
        {
            trade.getName().length();
        }
        return Optional.ofNullable(trade);
    }
}
