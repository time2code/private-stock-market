package io.my.stockmarket.metrics;

import java.math.BigDecimal;

/**
 * DividendYield
 */
public class DividendYield implements StockValuation {

    public BigDecimal evaluate() {
        return BigDecimal.ZERO;
    }
}
