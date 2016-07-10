package io.my.stockmarket.registry;

import io.my.stockmarket.domain.TradeTx;
import io.my.stockmarket.domain.TradeTxType;
import io.my.stockmarket.operations.FinOps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static io.my.stockmarket.domain.TradeTxType.SELL;

/**
 * Recorded Trade Transactions
 */
public enum TradeTxRegistry {

    TRANSACTIONS;

    private static final Logger log = LogManager.getFormatterLogger(TradeTxRegistry.class);
    private static final Map<String, List<TradeTx>> txRegistry = new HashMap<>();

    public int numberOfTxs(String StockId) {
        return stockTxs(StockId).size();
    }

    public void listAllTxs() {
        txRegistry.keySet().forEach(key -> txRegistry.get(key).stream().forEach(tx -> log.info(tx)));
    }

    public Map<String, List<TradeTx>> allTxs() {
        return txRegistry;
    }

    public void listTxs(TradeTxType txType) {
        txRegistry.keySet().forEach(key -> txRegistry.get(key).stream().filter(tx -> tx.getTxType().equals(txType)).forEach(tx -> log.info(tx)));
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
