package io.my.stockmarket.infra;

import io.my.stockmarket.registry.TradeTxRegistry;

import javax.enterprise.inject.Produces;

/**
 * Helper class to inject enums
 */
public class CDIEnumProducer {

    @Produces
    public static TradeTxRegistry tradeTxRegistry()
    {
        return TradeTxRegistry.TRANSACTIONS;
    }


}
