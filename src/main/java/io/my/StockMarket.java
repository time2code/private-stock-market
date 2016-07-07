package io.my;

import io.my.stockmarket.domain.Stock;
import io.my.stockmarket.domain.TradeTx;
import io.my.stockmarket.registry.Stocks;
import io.my.stockmarket.registry.TradeTxRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jboss.weld.environment.se.bindings.Parameters;
import org.jboss.weld.environment.se.events.ContainerInitialized;
import org.springframework.shell.Bootstrap;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.naming.NamingException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * Private Stock Market simulation - Dark Pool
 */
public class StockMarket {

    private static final Logger log = LogManager.getFormatterLogger(StockMarket.class);
    @Inject private TradeTxRegistry tradeTxRegistry;


    public void openMarket(@Observes ContainerInitialized event, @Parameters List<String> params) throws IOException, NamingException {
        Bootstrap.main(params.toArray(new String[params.size()]));
        Stock stock = Stock.builder()
                .id(Stocks.POP.name())
                .parValue(new BigDecimal("15"))
                .build();
        log.info("Hello Stock Market, number of params: %s! ", params.size());
        tradeTxRegistry.trade(TradeTx.builder()
                .StockID(Stocks.POP.name())
                .price(new BigDecimal("2.5"))
                .build());
        System.out.printf("Number of Txs: %s \n", tradeTxRegistry.numberOfTxs(Stocks.POP.name()));
    }
}
