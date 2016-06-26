package io.my.stockmarket.registry;

import lombok.Getter;

import java.math.BigDecimal;

/**
 * Known stocks
 */
public enum Stocks {

    TEA(BigDecimal.ZERO, new BigDecimal("100")),
    POP(new BigDecimal("8"), new BigDecimal("100")),
    ALE(new BigDecimal("23"), new BigDecimal("60")),
    GIN(new BigDecimal("8"), new BigDecimal("100")),
    JOE(new BigDecimal("13"), new BigDecimal("250"));


    @Getter
    private BigDecimal dividend;
    @Getter
    private BigDecimal parValue;

    Stocks(BigDecimal dividend, BigDecimal parValue) {
        this.dividend = dividend;
        this.parValue = parValue;
    }
}
