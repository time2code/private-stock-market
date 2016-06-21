package io.my.stockmarket.metrics;

import java.math.BigDecimal;

/**
 * Created by ego on 6/21/16.
 */
public interface PreferredStock {

    BigDecimal evaluate(BigDecimal price);
}
