package io.my;

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
import java.util.List;

/**
 * Private Stock Market simulation - Dark Pool
 */
public class StockMarket {

    private static final Logger log = LogManager.getFormatterLogger(StockMarket.class);
    @Inject private TradeTxRegistry tradeTxRegistry;


    public void openMarket(@Observes ContainerInitialized event, @Parameters List<String> params) throws IOException, NamingException {
        Bootstrap.main(params.toArray(new String[params.size()]));
        log.info("Hello Stock Market, number of params: %s! ", params.size());
    }
}
