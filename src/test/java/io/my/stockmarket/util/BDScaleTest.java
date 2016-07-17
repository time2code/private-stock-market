package io.my.stockmarket.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static io.my.stockmarket.util.BDScale.defaultScale;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(MockitoJUnitRunner.class)
public class BDScaleTest {

    BigDecimal formatted = new BigDecimal("12.00");

    @Test
    public void scaleMatch() throws Exception {
        assertEquals(formatted, defaultScale(new BigDecimal("12")));
    }

    @Test
    public void scaleDiffers() throws Exception {
        BigDecimal number = new BigDecimal("12");
        assertNotEquals(number, defaultScale(number));
    }

}