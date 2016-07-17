package io.my.stockmarket.registry;

import com.sun.org.apache.bcel.internal.generic.POP;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static io.my.stockmarket.registry.StockRegistry.*;
import static io.my.stockmarket.registry.Ticker.*;

/**
 * Latest Dividend Registry
 */
@ApplicationScoped
public class LastDividendRegistry {

    private static final Map<String, BigDecimal> DIVIDENDS_REGISTRY = new HashMap<>();
    static final int SCALE = 2;
    static final int ROUNDING_MODE = BigDecimal.ROUND_HALF_UP;

    static
    {
        DIVIDENDS_REGISTRY.put(TEA.name(), BigDecimal.ZERO);
        DIVIDENDS_REGISTRY.put(POP.name(), scale(new BigDecimal("8")));
        DIVIDENDS_REGISTRY.put(ALE.name(), scale(new BigDecimal("23")));
        DIVIDENDS_REGISTRY.put(GIN.name(), scale(new BigDecimal("8")));
        DIVIDENDS_REGISTRY.put(JOE.name(), scale(new BigDecimal("13")));
    }

    private static BigDecimal scale(BigDecimal number) {
        return number.setScale(SCALE, ROUNDING_MODE);
    }

    public BigDecimal find(String id) {
        return DIVIDENDS_REGISTRY.get(id);
    }
}
