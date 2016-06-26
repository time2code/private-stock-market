package io.my.stockmarket.registry;

import javax.enterprise.inject.Produces;

/**
 * Helper class to inject enums
 */
public class CDIEnumProducer {

    @Produces
    public static LastDividendRegistry getLatestDividendRegistry()
    {
        return LastDividendRegistry.LATEST_DIVIDEND;
    }
}
