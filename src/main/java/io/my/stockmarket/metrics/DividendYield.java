package io.my.stockmarket.metrics;

import io.my.stockmarket.registry.LatestDividendRegistry;

import java.math.BigDecimal;

/**
 * DividendYield
 */
public class DividendYield implements CommonStock {

    private LatestDividendRegistry latestDividend;

    public BigDecimal evaluate() {
        return BigDecimal.ZERO;
    }
}
