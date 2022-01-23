package com.example.bitoasistest;

import com.example.bitoasistest.controller.TickerController;
import com.example.bitoasistest.pojo.Ticker;
import com.example.bitoasistest.service.TickerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
class BitoasistestApplicationTests {

    @InjectMocks
    private TickerController tickerController;

    @Mock
    private TickerService tickerService;

    @Test
    public void test_getTickers() {
        List<Ticker> mockedObject = new ArrayList<>();
        mockedObject.add(Ticker.builder().build());
        Page<Ticker> page = new PageImpl(mockedObject);
        when(tickerService.getTickers(0, 10)).thenReturn(page);
        List<Ticker> tickers = tickerController.getTickers(1, 10);
        assertEquals(1, tickers.size());
    }

    @Test
    public void test_update() throws Exception {
        Ticker build = Ticker.builder().build();
        when(tickerService.update(build)).thenReturn(Ticker.builder().build());
        Ticker ticker = tickerController.update(build);
        assertNotNull(ticker);
    }

    @Test
    public void test_delete() throws Exception {
        when(tickerService.delete(123L)).thenReturn(Ticker.builder().build());
        Ticker delete = tickerController.delete(123L);
        assertNotNull(delete);
    }

    @Test
    public void test_deleteAll() throws Exception {
        doNothing().when(tickerService).deleteAll();
        tickerController.deleteAll();
    }


}
