package io.my.stockmarket.metrics;

import io.my.stockmarket.domain.Stock;
import io.my.stockmarket.registry.LastDividendRegistry;
import io.my.stockmarket.registry.StockRegistry;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Map;
import java.util.function.Consumer;

import static io.my.stockmarket.util.BDScale.defaultScale;

/**
 * Geometric Mean
 */
public class GeometricMean implements FinOp {

    static final String NAME = "Geometric Mean";

    @Inject
    private LastDividendRegistry ldRegistry;

    @Inject
    private StockRegistry stockRegistry;

    public BigDecimal evaluate(Stock stock, BigDecimal price) {
        GeometricMeanImpl geometricMean =
        stockRegistry.allCommonStocks().values().stream().collect(GeometricMeanImpl::new, GeometricMeanImpl::accept, GeometricMeanImpl::combine);

        return defaultScale(geometricMean.geometricMean());
    }

    @Override
    public BigDecimal evaluate(Stock stock, Map<String, Object> params) {
        throw new UnsupportedOperationException("Operation is not supported");
    }

    @Override
    public String name() {
        return NAME;
    }

    private class GeometricMeanImpl implements Consumer<Stock> {

        private BigDecimal price = BigDecimal.ONE;
        private int totalStocks = 0;

        public BigDecimal geometricMean() {
            return totalStocks > 0
                    ? new BigDecimal(String.valueOf(Math.pow(price.doubleValue(), 1.0/totalStocks)))
                    : BigDecimal.ZERO;
        }

        @Override
        public void accept(Stock stock) {
            price = price.multiply(stock.getParValue());
            totalStocks = ++totalStocks;
        }

        public void combine(GeometricMean.GeometricMeanImpl other) {
            price = price.multiply(other.price);
            totalStocks += other.totalStocks;
        }
    }
}
