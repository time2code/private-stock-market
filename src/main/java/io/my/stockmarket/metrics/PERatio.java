package io.my.stockmarket.metrics;

import io.my.stockmarket.domain.Stock;

import java.math.BigDecimal;

/**
 * Share Price / Earnings ratio
 */
public class PERatio implements CommonStock {

    public BigDecimal evaluate(Stock stock) {
        return BigDecimal.ZERO;
    }
}
