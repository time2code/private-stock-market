package io.my.stockmarket.metrics;

import java.math.BigDecimal;

/**
 * Share Price / Earnings ratio
 */
public class PERatio implements CommonStock {

    public BigDecimal evaluate() {
        return BigDecimal.ZERO;
    }
}
