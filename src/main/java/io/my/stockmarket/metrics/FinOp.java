package io.my.stockmarket.metrics;

import io.my.stockmarket.domain.Stock;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Stock valuation method
 */
public interface FinOp {

    BigDecimal evaluate(Stock stock);
    BigDecimal evaluate(Stock stock, Map<String, Object> params);
    String name();
}
