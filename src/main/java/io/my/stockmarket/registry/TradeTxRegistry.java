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

    public int numberOfTxs(String StockId) {
        return stockTxs(StockId).size();
    }

    List<TradeTx> stockTxs(String StockId) {
        List<TradeTx> stockTxs = txRegistry.get(StockId);
        return stockTxs != null ? stockTxs : newTxList(StockId);
    }

    private List<TradeTx> newTxList(String StockId) {
        txRegistry.put(StockId, new LinkedList<>());
        return txRegistry.get(StockId);
    }
}
