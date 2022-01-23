package com.example.bitoasistest.controller;

import com.example.bitoasistest.pojo.Ticker;
import com.example.bitoasistest.service.TickerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tickers")
public class TickerController {
    private static final Logger log = LogManager.getLogger(TickerController.class);

    private TickerService tickerService;

    public TickerController(TickerService tickerService) {
        this.tickerService = tickerService;
    }

    @GetMapping(value = {"/"})
    public List<Ticker> getTickers(
            @RequestParam(value = "page", required = false, defaultValue = "1") int currentPage,
            @RequestParam(value = "size", required = false, defaultValue = "10") int pageSize
    ) {
        log.debug("Received request page {} size {}", currentPage, pageSize);
        Page<Ticker> tickers = tickerService.getTickers(currentPage - 1, pageSize);
        return tickers.getContent();
    }

    @PutMapping("/")
    public Ticker update(@RequestBody Ticker ticker) throws Exception {
        log.debug("Received request ticker {}", ticker);
        return tickerService.update(ticker);
    }

    @DeleteMapping("/{id}")
    public Ticker delete(@PathVariable Long id) throws Exception {
        log.debug("Received request ticker id", id);
        return tickerService.delete(id);
    }

    @DeleteMapping("/")
    public void deleteAll() throws Exception {
        log.debug("Received request delete all");
        tickerService.deleteAll();
    }

}
