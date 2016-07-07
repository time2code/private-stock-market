package io.my.stockmarket.registry;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static io.my.stockmarket.registry.StockRegistry.*;

/**
 * Latest Dividend Registry
 */
public enum LastDividendRegistry {

    LATEST_DIVIDEND;

    private static final Map<String, BigDecimal> dividendsRegistry;

    static
    {
        dividendsRegistry = new HashMap<>();
        dividendsRegistry.put(TEA.name(), BigDecimal.ZERO);
        dividendsRegistry.put(POP.name(), new BigDecimal("8"));
        dividendsRegistry.put(ALE.name(), new BigDecimal("23"));
        dividendsRegistry.put(GIN.name(), new BigDecimal("8"));
        dividendsRegistry.put(JOE.name(), new BigDecimal("13"));
    }

    public BigDecimal find(String id) {
        return dividendsRegistry.get(id);
    }
}
