package io.my.stockmarket.registry;

import io.my.stockmarket.domain.Stock;
import io.my.stockmarket.domain.StockType;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static io.my.stockmarket.domain.StockType.COMMON;
import static io.my.stockmarket.registry.Ticker.*;

/**
 * Known Stocks
 */
@ApplicationScoped
public class StockRegistry {

    private static final Map<String, Stock> COMMON_STOCK_REGISTRY = new HashMap<>();

    static
    {
        COMMON_STOCK_REGISTRY.put(TEA.name(),
                Stock.builder().ticker(TEA.name()).stockType(COMMON).parValue(new BigDecimal("100"))
                        .build());
        COMMON_STOCK_REGISTRY.put(POP.name(),
                Stock.builder().ticker(POP.name()).stockType(COMMON).parValue(new BigDecimal("100"))
                        .build());
        COMMON_STOCK_REGISTRY.put(ALE.name(),
                Stock.builder().ticker(ALE.name()).stockType(COMMON).parValue(new BigDecimal("60"))
                        .build());
        COMMON_STOCK_REGISTRY.put(GIN.name(),
                Stock.builder().ticker(GIN.name()).stockType(COMMON).parValue(new BigDecimal("100"))
                        .build());
        COMMON_STOCK_REGISTRY.put(JOE.name(),
                Stock.builder().ticker(JOE.name()).stockType(COMMON).parValue(new BigDecimal("250"))
                        .build());
    }

    public Stock commonStock(String ticker) {
        return COMMON_STOCK_REGISTRY.get(ticker);
    }

    public Map<String, Stock> allCommonStocks() {
        return Collections.unmodifiableMap(COMMON_STOCK_REGISTRY);
    }
}
