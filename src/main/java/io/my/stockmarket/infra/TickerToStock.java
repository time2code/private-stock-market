package io.my.stockmarket.infra;

import io.my.stockmarket.domain.Stock;
import io.my.stockmarket.registry.StockRegistry;

import static io.my.stockmarket.registry.StockRegistry.valueOf;

/**
 * Ticker to Stock resolver
 */
public class TickerToStock {

    public String exists(String ticker) {
        return valueOf(ticker).getTicker();
    }

    public Stock resolve(String ticker) {
        StockRegistry stockInfo = valueOf(ticker);
        return Stock.builder()
                .parValue(stockInfo.getParValue())
                .ticker(stockInfo.getTicker())
                .stockType(stockInfo.getStockType())
                .build();
    }
}
