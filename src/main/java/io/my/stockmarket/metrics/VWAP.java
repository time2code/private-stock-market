package io.my.stockmarket.metrics;

import io.my.stockmarket.domain.Stock;
import io.my.stockmarket.domain.TradeTx;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Map;
import java.util.function.Consumer;

import static io.my.stockmarket.registry.TradeTxRegistry.TRANSACTIONS;

/**
 * Volume Weighted Stock Price,
 * Volume Weighted Average Price
 */
public class VWAP implements FinOp {

    static final String NAME = "Volume Weighted Stock Price";

    @Override
    public BigDecimal evaluate(Stock stock) {
        return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal evaluate(Stock stock, Map<String, Object> params) {
        Duration minutes = Duration.ofMinutes(((Long) params.get("timePeriod")));
        Averager averager =
        TRANSACTIONS.stockTxsWithin(stock.getTicker(), minutes)
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
                    ? txSum.divide(new BigDecimal(String.valueOf(txQuantity)))
                    : BigDecimal.ZERO;
        }

        @Override
        public void accept(TradeTx tradeTx) {
            txSum = txSum.add(tradeTx.getPrice().multiply(new BigDecimal(String.valueOf(tradeTx.getQuantity()))));
            txQuantity = ++txQuantity;
        }

        public void combine(Averager other) {
            txSum = txSum.add(other.txSum);
            txQuantity += other.txQuantity;
        }
    }
}
