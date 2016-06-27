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

    public int numberOfTxs(String StockId) {
        return stockTxs(StockId).size();
    }

    private List<TradeTx> stockTxs(String StockId) {
        List<TradeTx> stockTxs = txRegistry.get(StockId);
        return stockTxs != null ? stockTxs : newTxList(StockId);
    }

    private List<TradeTx> newTxList(String StockId) {
        txRegistry.put(StockId, new LinkedList<>());
        return txRegistry.get(StockId);
    }
}
