package io.my.stockmarket.registry;

import io.my.stockmarket.domain.TradeTx;
import io.my.stockmarket.domain.TradeTxType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TradeTxRegistryTest {

    private static final String SOME_TICKER = "SomeTicker";
    private TradeTxRegistry tradeTxRegistry;


    @Before
    public void each() throws Exception {
        tradeTxRegistry = new TradeTxRegistry();
    }

    @Test
    public void numberOfTxs() throws Exception {
        assertTrue(tradeTxRegistry.numberOfTxs(SOME_TICKER) == 0);
        tradeTxRegistry.trade(TradeTx.builder().stockTicker(SOME_TICKER).build());
        assertTrue(tradeTxRegistry.numberOfTxs(SOME_TICKER) != 0);
    }

    @Test
    public void listAllTxs() throws Exception {
        tradeTxRegistry.listAllTxs();
    }

    @Test
    public void trade() throws Exception {

    }

    @Test
    public void allTxs() throws Exception {
        tradeTxRegistry.allTxs();
    }

    @Test
    public void listTxs() throws Exception {
        tradeTxRegistry.listTxs(TradeTxType.SELL);
    }

    @Test
    public void stockTxsWithin() throws Exception {

    }

    @Test
    public void withinTimeRange() throws Exception {

    }

    @Test
    public void stockTxs() throws Exception {
        tradeTxRegistry.trade(TradeTx.builder().stockTicker(SOME_TICKER).build());
        tradeTxRegistry.trade(TradeTx.builder().stockTicker(SOME_TICKER).build());
        tradeTxRegistry.trade(TradeTx.builder().stockTicker(SOME_TICKER + "salt").build());
        assertTrue(tradeTxRegistry.stockTxs(SOME_TICKER).size() == 2);
    }

}