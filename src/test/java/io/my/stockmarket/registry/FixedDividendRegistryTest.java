package io.my.stockmarket.registry;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static io.my.stockmarket.registry.LastDividendRegistry.DIVIDENDS_REGISTRY;
import static io.my.stockmarket.registry.Ticker.GIN;
import static io.my.stockmarket.registry.Ticker.JOE;
import static io.my.stockmarket.registry.Ticker.POP;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class FixedDividendRegistryTest {

    FixedDividendRegistry fixedDividendRegistry = new FixedDividendRegistry();

    @Test
    public void initialDataExists() throws Exception {
        assertFalse(DIVIDENDS_REGISTRY.isEmpty());
    }

    @Test
    public void find() throws Exception {
        assertNotNull(fixedDividendRegistry.find(GIN.name()));
    }

}