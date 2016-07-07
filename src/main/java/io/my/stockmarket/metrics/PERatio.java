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
        BigDecimal dividend = ldRegistry.find(stock.getTicker());
        return dividend.compareTo(BigDecimal.ZERO) > 0
                ? stock.getParValue().divide(dividend, 2, BigDecimal.ROUND_HALF_UP)
                : stock.getParValue();
    }

    @Override
    public String name() {
        return P_R_RATIO;
    }
}
