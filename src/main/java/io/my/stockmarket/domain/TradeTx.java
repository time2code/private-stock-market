package io.my.stockmarket.domain;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Single Trade transaction
 */
@Getter
@Builder
public class TradeTx {

    private String StockID;
    private BigDecimal price;
    private int quantity;
    private LocalDateTime time;
    private TradeTxType txType;

}
