package io.my;

import io.my.stockmarket.domain.Stock;
import io.my.stockmarket.metrics.DividendYield;
import io.my.stockmarket.metrics.DividentYieldCS;
import org.jboss.weld.environment.se.bindings.Parameters;
import org.jboss.weld.environment.se.events.ContainerInitialized;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

import static io.my.stockmarket.registry.LatestDividendRegistry.POP;
import static io.my.stockmarket.registry.LatestDividendRegistry.TEA;

/**
 * Private Stock Market simulation - Dark Pool
 */
public class StockMarket {

    @Inject @DividentYieldCS
    private DividendYield dividendYield;

    public void openMarket(@Observes ContainerInitialized event, @Parameters List<String> params) {
        Stock stock = Stock.builder()
                .id(POP)
                .parValue(new BigDecimal("13"))
                .build();
        BigDecimal price = dividendYield.evaluate(stock);
        System.out.println("Hello Stock Market! " + params.size());
        System.out.printf("DividendYield: %s \n", price);
    }
}
