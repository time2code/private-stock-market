package io.my;

import io.my.stockmarket.domain.Stock;
import io.my.stockmarket.domain.TradeTx;
import io.my.stockmarket.metrics.DividendYield;
import io.my.stockmarket.metrics.FinOp;
import io.my.stockmarket.metrics.GeometricMean;
import io.my.stockmarket.metrics.PERatio;
import io.my.stockmarket.registry.Stocks;
import io.my.stockmarket.registry.TradeTxRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jboss.weld.environment.se.bindings.Parameters;
import org.jboss.weld.environment.se.events.ContainerInitialized;
import org.springframework.shell.Bootstrap;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * Private Stock Market simulation - Dark Pool
 */
public class StockMarket {

    private static final Logger log = LogManager.getFormatterLogger(StockMarket.class);
    @Inject private DividendYield dividendYield;
    @Inject private PERatio peRatio;
    @Inject private GeometricMean geometricMean;
    @Inject private TradeTxRegistry tradeTxRegistry;
    @Inject private Instance<FinOp> commonStock;

    public void openMarket(@Observes ContainerInitialized event, @Parameters List<String> params) throws IOException {
        Stock stock = Stock.builder()
                .id(Stocks.POP.name())
                .parValue(new BigDecimal("15"))
                .build();
        BigDecimal dividendYieldValue = dividendYield.evaluate(stock);
        log.info("Hello Stock Market, number of params: %s! ", params.size());
        System.out.printf("DividendYield: %s \n", dividendYieldValue);
        BigDecimal peRationValue = peRatio.evaluate(stock);
        System.out.printf("P/E Ratio: %s \n", peRationValue);
        BigDecimal geoMetricMean = geometricMean.evaluate(stock);
        System.out.printf("Geometric Mean: %s \n", geoMetricMean);
        tradeTxRegistry.trade(TradeTx.builder()
                .StockID(Stocks.POP.name())
                .price(new BigDecimal("2.5"))
                .build());
        System.out.printf("Number of Txs: %s \n", tradeTxRegistry.numberOfTxs(Stocks.POP.name()));
        Bootstrap.main(null);
    }
}
