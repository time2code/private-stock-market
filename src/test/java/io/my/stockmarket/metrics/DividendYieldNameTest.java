package io.my.stockmarket.metrics;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class DividendYieldNameTest {

    @InjectMocks
    DividendYield dividendYield;

    @Test
    public void name() throws Exception {
        assertEquals(DividendYield.NAME, dividendYield.name());
    }

}