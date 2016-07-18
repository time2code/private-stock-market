package io.my.stockmarket.domain;

import io.my.stockmarket.registry.Ticker;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TradeTxTest {

    private static final BigDecimal SOME_PRICE = BigDecimal.ONE;
    private static final int SOME_QUANTITY = 2;
    private static final String TICKER = Ticker.POP.name();
    private static final LocalDateTime NOW = LocalDateTime.now();


    private TradeTx tradeTx;

    @Before
    public void each() throws Exception {
        tradeTx = TradeTx.builder()
                        .txType(TradeTxType.SELL)
                        .price(SOME_PRICE)
                        .quantity(SOME_QUANTITY)
                        .stockTicker(TICKER)
                        .time(NOW)
                        .build();
    }

    @Test
    public void stockTicker() throws Exception {
        assertEquals(TICKER, tradeTx.getStockTicker());
    }

    @Test
    public void price() throws Exception {
        assertEquals(SOME_PRICE, tradeTx.getPrice());
    }

    @Test
    public void Quantity() throws Exception {
        assertEquals(SOME_QUANTITY, tradeTx.getQuantity());
    }

    @Test
    public void time() throws Exception {
        assertEquals(NOW, tradeTx.getTime());
    }

    @Test
    public void txType() throws Exception {
        assertEquals(TradeTxType.SELL, tradeTx.getTxType());
    }

}