package io.my.stockmarket.metrics;

import io.my.stockmarket.domain.Stock;
import io.my.stockmarket.registry.LastDividendRegistry;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Map;

/**
 * Dividend Yield
 */
public class DividendYield implements FinOp {

    static final String NAME = "Dividend Yield";

    @Inject
    private LastDividendRegistry ldRegistry;

    public BigDecimal evaluate(Stock stock) {
        return ldRegistry.find(stock.getTicker())
                .divide(stock.getParValue(), 2, BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public BigDecimal evaluate(Stock stock, Map<String, Object> params) {
        return evaluate(stock);
    }

    @Override
    public String name() {
        return NAME;
    }
}

