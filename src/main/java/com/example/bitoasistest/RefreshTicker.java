package com.example.bitoasistest;

import com.example.bitoasistest.client.TickerClient;
import com.example.bitoasistest.pojo.Ticker;
import com.example.bitoasistest.repo.TickerRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RefreshTicker {

    private TickerRepository tickerRepository;
    private TickerClient tickerClient;

    public RefreshTicker(TickerRepository tickerRepository, TickerClient tickerClient) {
        this.tickerRepository = tickerRepository;
        this.tickerClient = tickerClient;
    }

    @Async
    @Scheduled(fixedRate = 10000)
    public void reportCurrentTime() throws InterruptedException {
        Ticker tickerData = tickerClient.fetchTickerData();
        tickerRepository.save(tickerData);
    }
}
