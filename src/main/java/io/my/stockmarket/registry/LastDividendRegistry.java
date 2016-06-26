package io.my.stockmarket.registry;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static io.my.stockmarket.registry.Stocks.*;

/**
 * Latest Dividend Registry
 */
public enum LastDividendRegistry {

    LATEST_DIVIDEND;

    private static final Map<String, BigDecimal> dividendsRegistry;

    static
    {
        dividendsRegistry = new HashMap<>();
        dividendsRegistry.put(TEA.name(), TEA.getDividend());
        dividendsRegistry.put(POP.name(), POP.getDividend());
        dividendsRegistry.put(ALE.name(), ALE.getDividend());
        dividendsRegistry.put(GIN.name(), GIN.getDividend());
        dividendsRegistry.put(JOE.name(), JOE.getDividend());
    }

    public BigDecimal find(String id) {
        return dividendsRegistry.get(id);
    }
}
