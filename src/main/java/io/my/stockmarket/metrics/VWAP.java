package io.my.stockmarket.metrics;

import java.math.BigDecimal;

/**
 * Volume Weighted Stock Price,
 * Volume Weighted Average Price
 */
public class VWAP implements StockValuation {
    public BigDecimal evaluate() {
        return BigDecimal.ZERO;
    }
}