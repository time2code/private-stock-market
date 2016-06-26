package io.my.stockmarket.metrics;

import io.my.stockmarket.domain.Stock;

import java.math.BigDecimal;

/**
 * Stock valuation method
 */
public interface CommonStock {

    BigDecimal evaluate(Stock stock);
}
