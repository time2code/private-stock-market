package io.my.stockmarket.registry;

import io.my.stockmarket.domain.TradeTx;
import io.my.stockmarket.domain.TradeTxType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class TradeTxRegistry {

    private static final Logger log = LogManager.getFormatterLogger(TradeTxRegistry.class);
    private static final Map<String, List<TradeTx>> TX_REGISTRY = new HashMap<>();

    public int numberOfTxs(String StockId) {
        return stockTxs(StockId).size();
    }

    public void listAllTxs() {
        TX_REGISTRY.keySet().forEach(key -> TX_REGISTRY.get(key).stream().forEach(tx -> log.info(tx)));
    }

    public TradeTx trade(TradeTx tradeTx) {
        String ticker = tradeTx.getStockTicker();
        List<TradeTx> stockTxs = stockTxs(ticker);
        stockTxs.add(tradeTx);
        return tradeTx;
    }

    public Map<String, List<TradeTx>> allTxs() {
        return TX_REGISTRY;
    }

    public void listTxs(TradeTxType txType) {
        TX_REGISTRY.keySet().forEach(key -> TX_REGISTRY.get(key).stream().filter(tx -> tx.getTxType().equals(txType)).forEach(tx -> log.info(tx)));
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
        List<TradeTx> stockTxs = TX_REGISTRY.get(stockTicker);
        return stockTxs != null ? stockTxs : newTxList(stockTicker);
    }

    private List<TradeTx> newTxList(String stockTicker) {
        TX_REGISTRY.put(stockTicker, new LinkedList<>());
        return TX_REGISTRY.get(stockTicker);
    }
}
