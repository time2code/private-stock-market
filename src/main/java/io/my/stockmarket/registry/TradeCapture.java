package io.my.stockmarket.registry;

import io.my.stockmarket.domain.TradeTx;

import javax.inject.Inject;
import java.util.List;

/**
 * Trade Capture
 */
public class TradeCapture {

    @Inject
    private TradeTxRegistry tradeTxRegistry;

    public TradeTx trade(TradeTx tradeTx) {
        String ticker = tradeTx.getStockTicker();
        List<TradeTx> stockTxs = tradeTxRegistry.stockTxs(ticker);
        stockTxs.add(tradeTx);
        return tradeTx;
    }
}
