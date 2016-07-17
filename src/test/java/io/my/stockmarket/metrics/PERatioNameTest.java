package io.my.stockmarket.metrics;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static io.my.stockmarket.metrics.PERatio.NAME;
import static org.junit.Assert.*;

/**
 * ego on 2016.07
 */
@RunWith(MockitoJUnitRunner.class)
public class PERatioNameTest {

    @InjectMocks
    PERatio peRatio;

    @Test
    public void name() throws Exception {
        assertEquals(NAME, peRatio.name());
    }

}