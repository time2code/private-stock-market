package io.my.stockmarket.metrics;

import io.my.stockmarket.domain.Stock;

import java.math.BigDecimal;

/**
 * Volume Weighted Stock Price,
 * Volume Weighted Average Price
 */
public class VWAP implements CommonStock {
    public BigDecimal evaluate(Stock stock) {
        return BigDecimal.ZERO;
    }
}
