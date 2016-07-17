package io.my.stockmarket.metrics;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class DividendYieldPreferredNameTest {

    @InjectMocks
    DividendYieldPreferred dividendYieldPreferred;

    @Test
    public void name() throws Exception {
        assertEquals(DividendYieldPreferred.NAME, dividendYieldPreferred.name());
    }

}