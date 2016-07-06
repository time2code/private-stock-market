package io.my.stockmarket.metrics;

import io.my.stockmarket.domain.Stock;

import java.math.BigDecimal;

/**
 * Volume Weighted Stock Price,
 * Volume Weighted Average Price
 */
public class VWAP implements FinOp {

    static final String VOLUME_WEIGHTED_STOCK_PRICE = "Volume Weighted Stock Price";

    public BigDecimal evaluate(Stock stock) {
        return BigDecimal.ZERO;
    }

    @Override
    public String name() {
        return VOLUME_WEIGHTED_STOCK_PRICE;
    }
}
