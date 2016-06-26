package io.my.stockmarket.registry;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Latest Dividend Registry
 */
public enum LastDividendRegistry {

    LATEST_DIVIDEND;

    public static final String TEA = "TEA";
    public static final String POP = "POP";
    public static final String ALE = "ALE";
    public static final String GIN = "GIN";
    public static final String JOE = "JOE";

    private static final Map<String, BigDecimal> dividendsRegistry;

    static
    {
        dividendsRegistry = new HashMap<String, BigDecimal>();
        dividendsRegistry.put(TEA, BigDecimal.ZERO);
        dividendsRegistry.put(POP, new BigDecimal("8"));
        dividendsRegistry.put(ALE, new BigDecimal("23"));
        dividendsRegistry.put(GIN, new BigDecimal("8"));
        dividendsRegistry.put(JOE, new BigDecimal("13"));
    }

    public BigDecimal find(String id) {
        return dividendsRegistry.get(id);
    }
}
