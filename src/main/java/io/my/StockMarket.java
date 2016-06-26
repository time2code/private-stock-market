package io.my;

import io.my.stockmarket.domain.Stock;
import io.my.stockmarket.metrics.DividendYield;
import io.my.stockmarket.metrics.GeometricMean;
import io.my.stockmarket.metrics.PERatio;
import io.my.stockmarket.registry.Stocks;
import org.jboss.weld.environment.se.bindings.Parameters;
import org.jboss.weld.environment.se.events.ContainerInitialized;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

/**
 * Private Stock Market simulation - Dark Pool
 */
public class StockMarket {

    @Inject private DividendYield dividendYield;
    @Inject private PERatio peRatio;
    @Inject private GeometricMean geometricMean;

    public void openMarket(@Observes ContainerInitialized event, @Parameters List<String> params) {
        Stock stock = Stock.builder()
                .id(Stocks.POP.name())
                .parValue(new BigDecimal("13"))
                .build();
        BigDecimal dividendYieldValue = dividendYield.evaluate(stock);
        System.out.println("Hello Stock Market! " + params.size());
        System.out.printf("DividendYield: %s \n", dividendYieldValue);
        BigDecimal peRationValue = peRatio.evaluate(stock);
        System.out.printf("P/E Ratio: %s \n", peRationValue);
        BigDecimal geoMetricMean = geometricMean.evaluate(stock);
        System.out.printf("Geometric Mean: %s \n", geoMetricMean);
    }
}
