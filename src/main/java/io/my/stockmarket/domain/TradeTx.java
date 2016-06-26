package io.my.stockmarket.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Single Trade transaction
 */
public class TradeTx {

    private BigDecimal price;
    private int quantity;
    private LocalDateTime time;
    private TradeTxType txType;

}
