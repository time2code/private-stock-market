package io.my.stockmarket.metrics;

import io.my.stockmarket.domain.Stock;
import io.my.stockmarket.registry.LastDividendRegistry;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * ego on 2016.07
 */
@RunWith(Parameterized.class)
public class PERatioFinOpTest {

    private LastDividendRegistry lastDividendRegistry;
    private PERatio peRatio;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"8","100","12.50"},
                {"2","15", "7.50"},
                {"0","11", "11.00"},
        });
    }

    @Parameterized.Parameter(0)
    public String dividends;

    @Parameterized.Parameter(1)
    public String parValue;

    @Parameterized.Parameter(2)
    public String result;


    @Before
    public void each() throws Exception {
        lastDividendRegistry = mock(LastDividendRegistry.class);
        peRatio = new PERatio();
        Field field = PERatio.class.getDeclaredField("ldRegistry");
        field.setAccessible(true);
        field.set(peRatio, lastDividendRegistry);
    }

    @Test
    public void evaluate() throws Exception {
        when(lastDividendRegistry.find(anyString())).thenReturn(new BigDecimal(dividends));
        Stock stock = Stock.builder()
                .parValue(new BigDecimal(parValue))
                .build();
        assertEquals(new BigDecimal(result), peRatio.evaluate(stock, null));
    }

}