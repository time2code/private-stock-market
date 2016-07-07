package io.my.stockmarket.registry;

import io.my.stockmarket.domain.StockType;
import lombok.Getter;

import java.math.BigDecimal;

import static io.my.stockmarket.domain.StockType.COMMON;

/**
 * Known finOps
 */
@Getter
public enum StockRegistry {

    TEA("TEA", new BigDecimal("100"), COMMON),
    POP("POP", new BigDecimal("100"), COMMON),
    ALE("ALE", new BigDecimal("60"), COMMON),
    GIN("GIN",new BigDecimal("100"), COMMON),
    JOE("JOE", new BigDecimal("250"), COMMON);



    private BigDecimal parValue;
    private String ticker;
    private StockType stockType;

    StockRegistry(String ticker, BigDecimal parValue, StockType stockType) {
        this.parValue = parValue;
        this.ticker = ticker;
        this.stockType = stockType;
    }
}
