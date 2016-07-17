package io.my.stockmarket.metrics;

import io.my.stockmarket.domain.Stock;
import io.my.stockmarket.registry.StockRegistry;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class GeometricMeanFinOpTest {

    private StockRegistry stockRegistry;
    private GeometricMean geometricMean;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            {Arrays.asList("10", "30"), new BigDecimal("17.32")},
            {Arrays.asList("11", "10", "19"), new BigDecimal("12.79")},
            {Arrays.asList("0", "0", "0"), new BigDecimal("0.00")},
            {Arrays.asList(), new BigDecimal("0.00")},
        });
    }

    @Parameter(0)
    public List<String> prices;

    @Parameter(1)
    public BigDecimal geometricMeanResult;

    @Before
    public void each() throws Exception {
        stockRegistry = Mockito.mock(StockRegistry.class);
        when(stockRegistry.allCommonStocks()).thenReturn(mockStocks(prices));
        Field field = GeometricMean.class.getDeclaredField("stockRegistry");
        field.setAccessible(true);
        geometricMean = new GeometricMean();
        field.set(geometricMean, stockRegistry);
    }

    @Test
    public void evaluate() throws Exception {
        assertEquals(geometricMeanResult, geometricMean.evaluate(null, null));
    }

    private Map<String, Stock> mockStocks(List<String> prices) {
        Map<String, Stock> mockStoks = new HashMap<>();
        prices.forEach(price -> mockStoks.put(uniqueMapKey(), Stock.builder().parValue(new BigDecimal(price)).build()));
        return mockStoks;
    }

    private String uniqueMapKey() {
        return UUID.randomUUID().toString();
    }
}