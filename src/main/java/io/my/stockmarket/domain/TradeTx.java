package io.my.stockmarket.domain;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Single Trade Transaction
 */
@Getter
@Builder
public class TradeTx {

    private String stockTicker;
    private BigDecimal price;
    private int quantity;
    private LocalDateTime time;
    private TradeTxType txType;

}
