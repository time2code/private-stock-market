package io.my.stockmarket.domain;

import io.my.stockmarket.registry.Ticker;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class StockTest {

    private static final BigDecimal PAR_VALUE = BigDecimal.ONE;
    private static final StockType STOCK_TYPE = StockType.PREFERRED;
    private static final String TICKER = Ticker.POP.name();
    private Stock stock;


    @Before
    public void each() throws Exception {
        stock = Stock.builder()
                .parValue(PAR_VALUE)
                .stockType(STOCK_TYPE)
                .ticker(TICKER)
                .build();
    }

    @Test
    public void ticker() throws Exception {
        assertEquals(TICKER, stock.getTicker());
    }

    @Test
    public void stockType() throws Exception {
        assertEquals(STOCK_TYPE, stock.getStockType());
    }

    @Test
    public void parValue() throws Exception {
        assertEquals(PAR_VALUE, stock.getParValue());
    }

}