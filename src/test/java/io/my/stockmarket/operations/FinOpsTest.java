package io.my.stockmarket.operations;

import io.my.stockmarket.domain.Stock;
import io.my.stockmarket.domain.TradeTx;
import io.my.stockmarket.metrics.*;
import io.my.stockmarket.registry.LastDividendRegistry;
import io.my.stockmarket.registry.StockRegistry;
import io.my.stockmarket.registry.Ticker;
import io.my.stockmarket.registry.TradeTxRegistry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.inject.Inject;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FinOpsTest {

    private static final String SOME_PRICE = "2.1";
    private static final int SOME_TIME_PERIOD = 1;
    public static final String SOME_TICKER = "Ticker";

    @Mock
    private StockRegistry stockRegister;

    @Mock
    private DividendYieldCommon dividendYieldCommon;

    @Mock
    private DividendYieldPreferred dividendYieldPreferred;

    @Mock
    private VWAP vwap;

    @Mock
    private PERatio peRatio;

    @Mock
    private GeometricMean geometricMean;

    @Mock
    private TradeTxRegistry tradeTxRegistry;


    @InjectMocks
    FinOps finOps;

    @Test
    public void dividendYieldCommon() throws Exception {
        Stock stock = Stock.builder().build();
        when(stockRegister.commonStock(anyString())).thenReturn(stock);
        when(dividendYieldCommon.evaluate(any(), any(BigDecimal.class))).thenReturn(BigDecimal.ZERO);
        finOps.dividendYieldCommon(SOME_TICKER, SOME_PRICE);
        verify(dividendYieldCommon).evaluate(any(), any(BigDecimal.class));
    }

    @Test
    public void dividendYieldPreferred() throws Exception {
        Stock stock = Stock.builder().build();
        when(stockRegister.commonStock(anyString())).thenReturn(stock);
        when(dividendYieldPreferred.evaluate(any(), any(BigDecimal.class))).thenReturn(BigDecimal.ZERO);
        finOps.dividendYieldPreferred(SOME_TICKER, SOME_PRICE);
        verify(dividendYieldPreferred).evaluate(any(), any(BigDecimal.class));
    }

    @Test
    public void vwap() throws Exception {
        Stock stock = Stock.builder().build();
        when(stockRegister.commonStock(anyString())).thenReturn(stock);
        when(vwap.evaluate(any(), any(Map.class))).thenReturn(BigDecimal.ZERO);
        finOps.vwap(SOME_TICKER, SOME_TIME_PERIOD);
        verify(vwap).evaluate(any(), any(Map.class));
    }

    @Test
    public void peRatio() throws Exception {
        Stock stock = Stock.builder().build();
        when(stockRegister.commonStock(anyString())).thenReturn(stock);
        when(peRatio.evaluate(any(), any(BigDecimal.class))).thenReturn(BigDecimal.ZERO);
        finOps.peRatio(SOME_TICKER, SOME_PRICE);
        verify(peRatio).evaluate(any(), any(BigDecimal.class));
    }

    @Test
    public void geometricMean() throws Exception {
        when(geometricMean.evaluate(any(), any(BigDecimal.class))).thenReturn(BigDecimal.ZERO);
        finOps.geometricMean();
        verify(geometricMean).evaluate(any(), any(BigDecimal.class));
    }

    @Test
    public void trade() throws Exception {
        Stock stock = Stock.builder().build();
        when(stockRegister.commonStock(anyString())).thenReturn(stock);
        finOps.trade(TradeTx.builder().build());
        verify(tradeTxRegistry).trade(any());
    }
}