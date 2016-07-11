package io.my.stockmarket.metrics;

import io.my.stockmarket.domain.Stock;
import io.my.stockmarket.domain.TradeTx;
import io.my.stockmarket.registry.LastDividendRegistry;
import io.my.stockmarket.registry.StockRegistry;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Geometric Mean
 */
public class GeometricMean implements FinOp {

    static final String GEOMETRIC_MEAN = "Geometric Mean";

    @Inject
    private LastDividendRegistry ldRegistry;

    public BigDecimal evaluate(Stock stock) {
        GeometricMeanImpl geometricMean =
        Arrays.stream(StockRegistry.values()).collect(GeometricMeanImpl::new, GeometricMeanImpl::accept, GeometricMeanImpl::combine);

        return geometricMean.geometricMean();
    }

    @Override
    public BigDecimal evaluate(Stock stock, Map<String, Object> params) {
        return evaluate(stock);
    }

    @Override
    public String name() {
        return GEOMETRIC_MEAN;
    }

    private class GeometricMeanImpl implements Consumer<StockRegistry> {

        private BigDecimal price = BigDecimal.ONE;
        private int totalStocks = 0;

        public BigDecimal geometricMean() {
            return totalStocks > 0
                    ? new BigDecimal(String.valueOf(Math.pow(price.doubleValue(), 1.0/totalStocks)))
                    : BigDecimal.ZERO;
        }

        @Override
        public void accept(StockRegistry stockRegistry) {
            price = price.multiply(stockRegistry.getParValue());
            totalStocks = ++totalStocks;
        }

        public void combine(GeometricMean.GeometricMeanImpl other) {
            price = price.multiply(other.price);
            totalStocks += other.totalStocks;
        }
    }
}
