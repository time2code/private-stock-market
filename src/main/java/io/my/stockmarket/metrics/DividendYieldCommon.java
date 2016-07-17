package io.my.stockmarket.metrics;

import io.my.stockmarket.domain.Stock;
import io.my.stockmarket.registry.LastDividendRegistry;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Map;

/**
 * Dividend Yield. For common stocks
 */
public class DividendYieldCommon implements FinOp {

    static final String NAME = "Dividend Yield. Common stocks";

    @Inject
    private LastDividendRegistry ldRegistry;

    public BigDecimal evaluate(Stock stock, BigDecimal price) {
        return ldRegistry.find(stock.getTicker())
                .divide(price, 2, BigDecimal.ROUND_HALF_UP);
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

