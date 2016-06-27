package io.my.stockmarket.registry;

import io.my.stockmarket.domain.TradeTx;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Recorded Trade Transactions
 */
public enum TradeTxRegistry {

    TRANSACTIONS;

    private static final Map<String, List<TradeTx>> txRegistry = new HashMap<>();


    public TradeTx trade(TradeTx tradeTx) {
        String stockID = tradeTx.getStockID();
        List<TradeTx> stockTxs = stockTxs(stockID);
        stockTxs.add(tradeTx);
        return tradeTx;
    }

    private List<TradeTx> stockTxs(String stockID) {
        List<TradeTx> stockTxs = txRegistry.get(stockID);
        return stockTxs != null ? stockTxs : newTxList(stockID);
    }

    private List<TradeTx> newTxList(String stockID) {
        txRegistry.put(stockID, new LinkedList<>());
        return txRegistry.get(stockID);
    }
}
