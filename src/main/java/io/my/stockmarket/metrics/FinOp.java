package io.my.stockmarket.metrics;

import io.my.stockmarket.domain.Stock;

import java.math.BigDecimal;

/**
 * Stock valuation method
 */
public interface FinOp {

    BigDecimal evaluate(Stock stock);
    String name();
}
