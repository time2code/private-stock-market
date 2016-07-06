package io.my.stockmarket.metrics;

import io.my.stockmarket.domain.Stock;
import io.my.stockmarket.registry.LastDividendRegistry;

import javax.inject.Inject;
import java.math.BigDecimal;

/**
 * Share Price / Earnings ratio
 */
public class PERatio implements FinOp {

    static final String P_R_RATIO = "P/R ratio";

    @Inject
    private LastDividendRegistry ldRegistry;

    public BigDecimal evaluate(Stock stock) {
        return stock.getParValue()
                .divide(ldRegistry.find(stock.getId()), 2, BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public String name() {
        return P_R_RATIO;
    }
}
