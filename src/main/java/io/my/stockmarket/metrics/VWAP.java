package io.my.stockmarket.metrics;

import io.my.stockmarket.domain.Stock;
import io.my.stockmarket.domain.TradeTx;
import io.my.stockmarket.registry.TradeTxRegistry;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.Map;
import java.util.function.Consumer;

import static io.my.stockmarket.util.BDScale.defaultScale;

/**
 * Volume Weighted Stock Price,
 * Volume Weighted Average Price
 */
public class VWAP implements FinOp {

    static final String NAME = "Volume Weighted Stock Price";

    @Inject
    private TradeTxRegistry tradeTxRegistry;

    @Override
    public BigDecimal evaluate(Stock stock) {
        throw new UnsupportedOperationException("Operation is not supported ");
    }

    @Override
    public BigDecimal evaluate(Stock stock, Map<String, Object> params) {
        Duration minutes = Duration.ofMinutes(((Long) params.get("timePeriod")));
        Averager averager =
                tradeTxRegistry.stockTxsWithin(stock.getTicker(), minutes)
                .stream()
                .collect(Averager::new, Averager::accept, Averager::combine);
        return averager.average();
    }

    @Override
    public String name() {
        return NAME;
    }

    private class Averager implements Consumer<TradeTx> {

        private BigDecimal txSum = BigDecimal.ZERO;
        private int txQuantity = 0;

        public BigDecimal average() {
            return txQuantity > 0
                    ? txSum.divide(new BigDecimal(String.valueOf(txQuantity)), 2, RoundingMode.HALF_UP)
                    : defaultScale(BigDecimal.ZERO);
        }

        @Override
        public void accept(TradeTx tradeTx) {
            txSum = txSum.add(tradeTx.getPrice().multiply(new BigDecimal(String.valueOf(tradeTx.getQuantity()))));
            txQuantity += tradeTx.getQuantity();
        }

        public void combine(Averager other) {
            txSum = txSum.add(other.txSum);
            txQuantity += other.txQuantity;
        }
    }
}
