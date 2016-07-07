package io.my.stockmarket.registry;

import io.my.stockmarket.domain.TradeTx;

import java.util.List;

import static io.my.stockmarket.registry.TradeTxRegistry.TRANSACTIONS;

/**
 * Trade Capture
 */
public class TradeCapture {

    public TradeTx trade(TradeTx tradeTx) {
        String ticker = tradeTx.getStockTicker();
        List<TradeTx> stockTxs = TRANSACTIONS.stockTxs(ticker);
        stockTxs.add(tradeTx);
        return tradeTx;
    }
}
