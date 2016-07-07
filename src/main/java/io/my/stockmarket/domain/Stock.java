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

    //id
    private String ticker;
    private StockType stockType;
    private BigDecimal parValue;
}
