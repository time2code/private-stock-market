package io.my.stockmarket.metrics;

import io.my.stockmarket.domain.Stock;
import io.my.stockmarket.registry.LastDividendRegistry;

import javax.inject.Inject;
import java.math.BigDecimal;

/**
 * Geometric Mean
 */
public class GeometricMean implements CommonStock {

    @Inject
    private LastDividendRegistry ldRegistry;

    public BigDecimal evaluate(Stock stock) {
        return BigDecimal.ONE;
    }
}
