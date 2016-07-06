package io.my.stockmarket.metrics;

import io.my.stockmarket.domain.Stock;
import io.my.stockmarket.registry.LastDividendRegistry;

import javax.inject.Inject;
import java.math.BigDecimal;

/**
 * Dividend Yield
 */
public class DividendYield implements FinOp {

    static final String NAME = "Dividend Yield";

    @Inject
    private LastDividendRegistry ldRegistry;

    public BigDecimal evaluate(Stock stock) {
        //TODO: handle null and consider Optional
        return ldRegistry.find(stock.getId())
                .divide(stock.getParValue(), 2, BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public String name() {
        return NAME;
    }
}

