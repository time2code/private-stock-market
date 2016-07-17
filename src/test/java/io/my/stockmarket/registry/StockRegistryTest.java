package io.my.stockmarket.registry;

import io.my.stockmarket.domain.Stock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static io.my.stockmarket.domain.StockType.COMMON;
import static io.my.stockmarket.registry.Ticker.ALE;
import static io.my.stockmarket.registry.Ticker.POP;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class StockRegistryTest {

    StockRegistry stockRegistry = new StockRegistry();

    @Test
    public void commonStock() throws Exception {
        Ticker ticker = ALE;
        Stock stock = stockRegistry.commonStock(ticker.name());
        assertEquals(ticker.name(), stock.getTicker());
        assertEquals(new BigDecimal("60"), stock.getParValue());
        assertEquals(COMMON, stock.getStockType());

        ticker = POP;
        stock = stockRegistry.commonStock(ticker.name());
        assertEquals(ticker.name(), stock.getTicker());
        assertEquals(new BigDecimal("100"), stock.getParValue());
        assertEquals(COMMON, stock.getStockType());
    }

    @Test
    public void allCommonStocksInital() throws Exception {
        assertTrue(stockRegistry.allCommonStocks().size() == 5);
    }
}