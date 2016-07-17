package io.my.stockmarket.metrics;

import io.my.stockmarket.domain.Stock;
import io.my.stockmarket.registry.LastDividendRegistry;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Map;

/**
 * Share Price / Earnings ratio
 */
public class PERatio implements FinOp {

    static final String NAME = "P/R ratio";

    @Inject
    private LastDividendRegistry ldRegistry;

    public BigDecimal evaluate(Stock stock) {
        BigDecimal dividend = ldRegistry.find(stock.getTicker());
        return dividend.compareTo(BigDecimal.ZERO) > 0
                ? stock.getParValue().divide(dividend, 2, BigDecimal.ROUND_HALF_UP)
                : stock.getParValue().setScale(2, BigDecimal.ROUND_HALF_UP);
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
