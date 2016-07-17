package io.my.stockmarket.operations;

import io.my.stockmarket.domain.Stock;
import io.my.stockmarket.metrics.DividendYieldCommon;
import io.my.stockmarket.metrics.DividendYieldPreferred;
import io.my.stockmarket.registry.StockRegistry;
import io.my.stockmarket.registry.Ticker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.inject.Inject;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FinOpsTest {

    private static final String SOME_PRICE = "2.1";

    @Mock
    private StockRegistry stockRegister;

    @Mock
    private DividendYieldCommon dividendYieldCommon;

    @Mock
    private DividendYieldPreferred dividendYieldPreferred;

    @InjectMocks
    FinOps finOps;

    @Test
    public void dividendYieldCommon() throws Exception {
        Stock stock = Stock.builder().build();
        when(stockRegister.commonStock(anyString())).thenReturn(stock);
        when(dividendYieldCommon.evaluate(any(), any(BigDecimal.class))).thenReturn(BigDecimal.ZERO);
        finOps.dividendYieldCommon("Ticker", SOME_PRICE);
        Mockito.verify(dividendYieldCommon).evaluate(any(), any(BigDecimal.class));
    }

    @Test
    public void dividendYieldPreferred() throws Exception {
        Stock stock = Stock.builder().build();
        when(stockRegister.commonStock(anyString())).thenReturn(stock);
        when(dividendYieldPreferred.evaluate(any(), any(BigDecimal.class))).thenReturn(BigDecimal.ZERO);
        finOps.dividendYieldPreferred("Ticker", SOME_PRICE);
        Mockito.verify(dividendYieldPreferred).evaluate(any(), any(BigDecimal.class));
    }

    @Test
    public void vwap() throws Exception {

    }

    @Test
    public void peRatio() throws Exception {

    }

    @Test
    public void geometricMean() throws Exception {

    }

    @Test
    public void trade() throws Exception {

    }

}