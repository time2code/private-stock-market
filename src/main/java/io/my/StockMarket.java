package io.my;

import org.jboss.weld.environment.se.bindings.Parameters;
import org.jboss.weld.environment.se.events.ContainerInitialized;

import javax.enterprise.event.Observes;
import java.util.List;

/**
 * Hello world!
 *
 */
public class StockMarket
{
    public void openMarket(@Observes ContainerInitialized event, @Parameters List<String> params)
    {
        System.out.println( "Hello Stock Market! " + params.size() );
    }
}
