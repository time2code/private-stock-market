package io.my.stockmarket.registry;

import io.my.stockmarket.domain.TradeTx;
import io.my.stockmarket.domain.TradeTxType;
import io.my.stockmarket.operations.FinOps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public List<TradeTx> stockTxsWithin(String stockTicker, Duration timeFrame) {
        final LocalDateTime now = LocalDateTime.now();
        List<TradeTx> stockTxs = stockTxs(stockTicker).stream().filter(tradeTx -> withinTimeRange(tradeTx, now, timeFrame)).collect(Collectors.toList());
        return stockTxs;
    }

    boolean withinTimeRange(TradeTx tradeTx, LocalDateTime now, Duration timeRange) {
        Duration sinceTxOccured = Duration.between(tradeTx.getTime(), now);
        return timeRange.compareTo(sinceTxOccured) >= 0;
    }

    List<TradeTx> stockTxs(String stockTicker) {
        List<TradeTx> stockTxs = txRegistry.get(stockTicker);
        return stockTxs != null ? stockTxs : newTxList(stockTicker);
    }

    private List<TradeTx> newTxList(String stockTicker) {
        txRegistry.put(stockTicker, new LinkedList<>());
        return txRegistry.get(stockTicker);
    }
}
