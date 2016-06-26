package io.my.stockmarket.domain;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * Stock
 */
@Builder
@Getter
public class Stock {

    private String id;
    private String name;
    private StockType stockType;
    private BigDecimal parValue;
}
