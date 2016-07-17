package io.my.stockmarket.metrics;

import io.my.stockmarket.domain.Stock;
import io.my.stockmarket.registry.LastDividendRegistry;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class DividendYieldCommonFinOpTest {

    private LastDividendRegistry lastDividendRegistry;
    private DividendYieldCommon dividendYieldCommon;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"8","100","0.08"},
                {"1","15", "0.07"},
                {"0","11", "0.00"},
        });
    }

    @Parameter(0)
    public String dividends;

    @Parameter(1)
    public String price;

    @Parameter(2)
    public String result;

    @Before
    public void each() throws Exception {
        lastDividendRegistry = mock(LastDividendRegistry.class);
        dividendYieldCommon = new DividendYieldCommon();
        Field field = DividendYieldCommon.class.getDeclaredField("ldRegistry");
        field.setAccessible(true);
        field.set(dividendYieldCommon, lastDividendRegistry);
    }

    @Test
    public void dividendsPositive() throws Exception {
        when(lastDividendRegistry.find(anyString())).thenReturn(new BigDecimal(dividends));
        assertEquals(new BigDecimal(result), dividendYieldCommon.evaluate(Stock.builder().build(), new BigDecimal(price)));
    }
}