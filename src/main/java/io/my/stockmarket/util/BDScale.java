package io.my.stockmarket.util;


import java.math.BigDecimal;

public class BDScale {

    private static final int SCALE = 2;
    private static final int ROUNDING_MODE = BigDecimal.ROUND_HALF_UP;

    public static final BigDecimal defaultScale(BigDecimal number) {
        return number.setScale(SCALE, ROUNDING_MODE);
    }
}
