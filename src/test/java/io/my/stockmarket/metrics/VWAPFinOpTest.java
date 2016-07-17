package io.my.stockmarket.metrics;

import io.my.stockmarket.domain.Stock;
import io.my.stockmarket.domain.TradeTx;
import io.my.stockmarket.registry.StockRegistry;
import io.my.stockmarket.registry.TradeTxRegistry;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class VWAPFinOpTest {

    private VWAP vwap;
    private TradeTxRegistry tradeTxRegistry;
    @Mock
    private StockRegistry stockRegistry;

    private List<String> dataSet;


    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return asList(new Object[][] {
                {asList(asList("10", "30"), asList("11", "14"), asList("9", "12")), new BigDecimal("10.04")},
                {asList(asList("15", "49"), asList("7", "18")), new BigDecimal("12.85")},
                {singletonList(asList("0", "0", "0")), new BigDecimal("0.00")},
        });
    }

    @Parameterized.Parameter(0)
    public List<List<String>> tradeTxs;

    @Parameterized.Parameter(1)
    public BigDecimal expectedVWSP;

    @Before
    public void each() throws Exception {
        vwap = new VWAP();
        tradeTxRegistry = Mockito.mock(TradeTxRegistry.class);
        when(tradeTxRegistry.stockTxsWithin(anyString(), anyObject())).thenReturn(mockTrades(tradeTxs));
        Field field = VWAP.class.getDeclaredField("tradeTxRegistry");
        field.setAccessible(true);
        field.set(vwap, tradeTxRegistry);
    }

    @Test
    public void evaluateWithParams() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("timePeriod", 1L);
        assertEquals(expectedVWSP, vwap.evaluate(Stock.builder().ticker("POP").build(), params));
    }

    private List<TradeTx> mockTrades(List<List<String>> dataSet) {
        List<TradeTx> mockedTrades = new ArrayList<>();
        dataSet.forEach(data -> mockedTrades.add(TradeTx.builder()
                .price(new BigDecimal(data.get(0)))
                .quantity(Integer.parseInt(data.get(1)))
                .build()));

        return mockedTrades;
    }

}