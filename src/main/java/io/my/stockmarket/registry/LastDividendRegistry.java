package io.my.stockmarket.registry;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static io.my.stockmarket.registry.Ticker.*;
import static io.my.stockmarket.util.BDScale.defaultScale;

/**
 * Latest Dividend Registry
 */
@ApplicationScoped
public class LastDividendRegistry {

    static final Map<String, BigDecimal> DIVIDENDS_REGISTRY = new HashMap<>();

    static
    {
        DIVIDENDS_REGISTRY.put(TEA.name(), BigDecimal.ZERO);
        DIVIDENDS_REGISTRY.put(POP.name(), defaultScale(new BigDecimal("8")));
        DIVIDENDS_REGISTRY.put(ALE.name(), defaultScale(new BigDecimal("23")));
        DIVIDENDS_REGISTRY.put(GIN.name(), defaultScale(new BigDecimal("8")));
        DIVIDENDS_REGISTRY.put(JOE.name(), defaultScale(new BigDecimal("13")));
    }

    public BigDecimal find(String id) {
        return DIVIDENDS_REGISTRY.get(id);
    }
}
