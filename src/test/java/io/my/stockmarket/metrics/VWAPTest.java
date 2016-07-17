package io.my.stockmarket.metrics;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static io.my.stockmarket.metrics.VWAP.NAME;
import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class VWAPTest {

    @InjectMocks
    VWAP vwap;

    @Test
    public void name() throws Exception {
        assertEquals(NAME, vwap.name());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void evaluate() throws Exception {
        vwap.evaluate(null, new BigDecimal("0"));
    }

}