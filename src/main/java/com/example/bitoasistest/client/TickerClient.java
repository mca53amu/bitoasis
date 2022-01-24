package com.example.bitoasistest.client;

import com.example.bitoasistest.pojo.Ticker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

@Component
public class TickerClient {
    private String url;

    private RestTemplate restTemplate;

    public TickerClient(@Value("${ticker.url}") String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    public Ticker fetchTickerData() throws Exception {
        ResponseEntity<List<BigDecimal>> tickerResponse =
                restTemplate.exchange(url,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<BigDecimal>>() {
                        });
        List<BigDecimal> list = tickerResponse.getBody();
        Ticker build = Ticker.builder().bid(list.get(0)).bidSize(list.get(1)).ask(list.get(2))
                .askSize(list.get(3)).dailyChange(list.get(4)).dailyChangeRealTime(list.get(5)).
                        lastPrice(list.get(6)).volume(list.get(7)).high(list.get(8)).low(list.get(9)).build();
        return build;

    }
}
