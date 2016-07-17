package io.my.stockmarket.metrics;

import io.my.stockmarket.domain.Stock;
import io.my.stockmarket.registry.FixedDividendRegistry;
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


@RunWith(Parameterized.class)
public class DividendYieldPreferredFinOpTest {

    private FixedDividendRegistry fixedDividendRegistry;
    private DividendYieldPreferred dividendYieldPreferred;


    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"12.5", "2", "100", "0.16"},
                {"16", "3", "60", "0.11"},
                {"0", "11", "100", "0.00"},
        });
    }

    @Parameterized.Parameter(0)
    public String price;

    @Parameterized.Parameter(1)
    public String dividends;

    @Parameterized.Parameter(2)
    public String parValue;

    @Parameterized.Parameter(3)
    public String result;


    @Before
    public void each() throws Exception {
        fixedDividendRegistry = mock(FixedDividendRegistry.class);
        dividendYieldPreferred = new DividendYieldPreferred();
        Field field = DividendYieldPreferred.class.getDeclaredField("fdRegistry");
        field.setAccessible(true);
        field.set(dividendYieldPreferred, fixedDividendRegistry);
    }

    @Test
    public void evaluate() throws Exception {
        when(fixedDividendRegistry.find(anyString())).thenReturn(new BigDecimal(dividends));
        Stock stock = Stock.builder()
                .parValue(new BigDecimal(parValue))
                .build();
        assertEquals(new BigDecimal(result), dividendYieldPreferred.evaluate(stock, new BigDecimal(price)));
    }
}