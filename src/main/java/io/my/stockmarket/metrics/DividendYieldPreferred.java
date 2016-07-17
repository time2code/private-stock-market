package io.my.stockmarket.metrics;

import io.my.stockmarket.domain.Stock;
import io.my.stockmarket.registry.FixedDividendRegistry;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Map;

/**
 * Dividend Yield. For preferred stocks
 */
public class DividendYieldPreferred implements FinOp {

    static final String NAME = "Dividend Yield. Preferred stocks";

    @Inject
    private FixedDividendRegistry fdRegistry;

    public BigDecimal evaluate(Stock stock, BigDecimal price) {
        return
        price.compareTo(BigDecimal.ZERO) > 0
        ? fdRegistry.find(stock.getTicker()).divide(new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_UP).multiply(stock.getParValue())
                .divide(price, 2, BigDecimal.ROUND_HALF_UP)
        : BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public BigDecimal evaluate(Stock stock, Map<String, Object> params) {
        throw new UnsupportedOperationException("Operation is not supported");
    }

    @Override
    public String name() {
        return NAME;
    }
}
