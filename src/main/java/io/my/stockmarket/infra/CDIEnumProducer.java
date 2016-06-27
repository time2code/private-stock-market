package io.my.stockmarket.infra;

import io.my.stockmarket.registry.LastDividendRegistry;
import io.my.stockmarket.registry.TradeTxRegistry;

import javax.enterprise.inject.Produces;

/**
 * Helper class to inject enums
 */
public class CDIEnumProducer {

    @Produces
    public static LastDividendRegistry latestDividendRegistry()
    {
        return LastDividendRegistry.LATEST_DIVIDEND;
    }

    @Produces
    public static TradeTxRegistry tradeTxRegistry()
    {
        return TradeTxRegistry.TRANSACTIONS;
    }


}
