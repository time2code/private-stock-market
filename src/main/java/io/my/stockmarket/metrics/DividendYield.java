package io.my.stockmarket.metrics;

import io.my.stockmarket.domain.Stock;
import io.my.stockmarket.registry.LatestDividendRegistry;

import javax.inject.Inject;
import java.math.BigDecimal;

/**
 * Dividend Yield
 */
@DividentYieldCS
public class DividendYield implements CommonStock {

    @Inject
    private LatestDividendRegistry ldRegistry;

    public BigDecimal evaluate(Stock stock) {
        return ldRegistry.find(stock.getId())
                .divide(stock.getParValue(), 2, BigDecimal.ROUND_HALF_UP);
    }
}

