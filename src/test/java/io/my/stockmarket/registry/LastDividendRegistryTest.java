package io.my.stockmarket.registry;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static io.my.stockmarket.registry.LastDividendRegistry.DIVIDENDS_REGISTRY;
import static io.my.stockmarket.registry.Ticker.JOE;
import static io.my.stockmarket.registry.Ticker.POP;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class LastDividendRegistryTest {

    LastDividendRegistry lastDividendRegistry = new LastDividendRegistry();

    @Test
    public void find() throws Exception {
        assertNotNull(lastDividendRegistry.find(POP.name()));
        assertNotNull(lastDividendRegistry.find(JOE.name()));
    }

    @Test
    public void initialDataExists() throws Exception {
        assertFalse(DIVIDENDS_REGISTRY.isEmpty());
    }

}