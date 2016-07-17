package io.my.stockmarket.registry;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static io.my.stockmarket.registry.Ticker.GIN;

/**
 * Fixed dividend registry
 */
@ApplicationScoped
public class FixedDividendRegistry {
    private static final Map<String, BigDecimal> FIXED_DIVIDENDS_REGISTRY = new HashMap<>();

    static
    {
        FIXED_DIVIDENDS_REGISTRY.put(GIN.name(), new BigDecimal("2"));
    }

    public BigDecimal find(String id) {
        return FIXED_DIVIDENDS_REGISTRY.get(id);
    }
}
