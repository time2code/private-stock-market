package io.my.stockmarket.metrics;

import io.my.stockmarket.domain.Stock;
import io.my.stockmarket.registry.LastDividendRegistry;

import javax.inject.Inject;
import java.math.BigDecimal;

/**
 * Geometric Mean
 */
public class GeometricMean implements FinOp {

    static final String GEOMETRIC_MEAN = "Geometric Mean";

    @Inject
    private LastDividendRegistry ldRegistry;

    public BigDecimal evaluate(Stock stock) {
        return BigDecimal.ONE;
    }

    @Override
    public String name() {
        return GEOMETRIC_MEAN;
    }
}
