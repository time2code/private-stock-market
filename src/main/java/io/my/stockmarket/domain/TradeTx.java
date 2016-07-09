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

    public String toString() {
        return "(stockTicker=" + this.stockTicker + ", price=" + this.price + ", quantity=" + this.quantity + ", time=" + this.time + ", txType=" + this.txType + ")";
    }
}
