package com.example.bitoasistest.service;

import com.example.bitoasistest.pojo.Ticker;
import com.example.bitoasistest.repo.TickerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class TickerService {
    private static final Logger log = LogManager.getLogger(TickerService.class);
    public static final String NOT_FOUND = "Not found";
    private TickerRepository tickerRepository;

    public TickerService(TickerRepository tickerRepository) {
        this.tickerRepository = tickerRepository;
    }


    @Transactional(propagation = Propagation.REQUIRED ,isolation = Isolation.READ_COMMITTED)
    public Page<Ticker> getTickers(int pageNo, int pageSize) {
        log.debug("Received request page {} -- size {}", pageNo, pageSize);
        return tickerRepository.findAll(PageRequest.of(pageNo, pageSize));
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ)
    public Ticker update(Ticker ticker) throws Exception {
        log.debug("Received request ticker {}", ticker);
        Optional<Ticker> byId = tickerRepository.findById(ticker.getId());
        Ticker updateTicker = byId.orElseThrow(() -> new Exception(NOT_FOUND));
        copyValues(ticker, updateTicker);
        Ticker save = tickerRepository.save(updateTicker);
        return save;
    }

    private void copyValues(Ticker ticker, Ticker updateTicker) {
        updateTicker.setAsk(ticker.getAsk());
        updateTicker.setAskSize(ticker.getAskSize());
        updateTicker.setBid(ticker.getBid());
        updateTicker.setBidSize(ticker.getBidSize());
        updateTicker.setDailyChange(ticker.getDailyChange());
        updateTicker.setHigh(ticker.getHigh());
        updateTicker.setLastPrice(ticker.getLastPrice());
        updateTicker.setLow(ticker.getLow());
        updateTicker.setVolume(ticker.getVolume());
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public Ticker delete(Long id) throws Exception {
        log.debug("Received request ticker id {}", id);
        Optional<Ticker> byId = tickerRepository.findById(id);
        Ticker ticker = byId.orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        tickerRepository.delete(ticker);
        return ticker;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public void deleteAll() throws Exception {
        log.debug("Received request delete all");
        tickerRepository.deleteAll();
    }
}
