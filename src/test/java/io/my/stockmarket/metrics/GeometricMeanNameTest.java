package io.my.stockmarket.metrics;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static io.my.stockmarket.metrics.GeometricMean.NAME;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GeometricMeanNameTest {

    @InjectMocks
    private GeometricMean geometricMean;

    @Test
    public void name() throws Exception {
        assertEquals(NAME, geometricMean.name());
    }

}