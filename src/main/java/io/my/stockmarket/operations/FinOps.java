package io.my.stockmarket.operations;

import io.my.stockmarket.domain.Stock;
import io.my.stockmarket.domain.TradeTx;
import io.my.stockmarket.domain.TradeTxType;
import io.my.stockmarket.infra.TickerToStock;
import io.my.stockmarket.metrics.DividendYield;
import io.my.stockmarket.metrics.FinOp;
import io.my.stockmarket.metrics.PERatio;
import io.my.stockmarket.metrics.VWAP;
import io.my.stockmarket.registry.TradeCapture;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import java.util.HashMap;
import java.util.Map;

import static io.my.stockmarket.registry.TradeTxRegistry.TRANSACTIONS;
import static java.lang.String.format;

/**
 * Financial Operations
 */
public class FinOps {

    public static final String ALL_TXS = "AllTxs";
    private static final Logger log = LogManager.getFormatterLogger(FinOps.class);
    static final String STOCK_DOES_NOT_EXIST = "Stock '%s' does not exist";
    static final String TRADE_SUCCESSFUL = "Trade Transaction '%s' successful - price: %s, volume: %s, time: %s";

    @Inject
    private Instance<FinOp> finOps;

    @Inject
    private DividendYield dividendYield;

    @Inject
    private PERatio peRatio;

    @Inject
    private VWAP vwap;

    @Inject
    private TradeCapture tradeCapture;

    @Inject
    private TickerToStock tickerToStock;


    public void availableFinOps() {
        log.info("Financial Operations:");
        finOps.forEach(finOp -> log.info(finOp.name()));
    }

    public String dividendYield(String ticker) {
        return evaluate(dividendYield, ticker);
    }

    public void listTradeTxs(String txType) {
        TradeTxType tradeTxType = resolveTxType(txType);
        if (tradeTxType != null) {
            TRANSACTIONS.listTxs(tradeTxType);
        } else if (txType.equals(ALL_TXS)) {
            TRANSACTIONS.listAllTxs();
        } else {
            log.info("Transaction type: '%s' doesn't exist", txType);
        }
    }

    public String vwap(String ticker, int timePeriod) {
        Map<String, Object> params = new HashMap<>();
        params.put("timePeriod", timePeriod);
        Stock stock = resolve(ticker);
        return stock != null
                ? vwap.evaluate(stock, params).toString()
                : format(STOCK_DOES_NOT_EXIST, ticker);
    }

    public String peRatio(String ticker) {
        return evaluate(peRatio, ticker);
    }

    public String trade(TradeTx tradeTx) {
        Stock stock = resolve(tradeTx.getStockTicker());
        if (stock != null) {
            tradeCapture.trade(tradeTx);
            return format(TRADE_SUCCESSFUL, tradeTx.getTxType(), tradeTx.getPrice(), tradeTx.getQuantity(), tradeTx.getTime());
        } else {
            return format(STOCK_DOES_NOT_EXIST, tradeTx.getStockTicker());
        }
    }

    private String evaluate(FinOp finOp, String ticker) {
        Stock stock = resolve(ticker);
        return stock != null
                ? finOp.evaluate(stock).toString()
                : format(STOCK_DOES_NOT_EXIST, ticker);
    }

    private Stock resolve(String ticker) {
        try {
            return tickerToStock.resolve(ticker);
        } catch (IllegalArgumentException exception) {
            return null;
        }
    }

    private TradeTxType resolveTxType(String txType) {
        try {
            TradeTxType tradeTxType = TradeTxType.valueOf(txType);
            return tradeTxType;
        } catch (IllegalArgumentException exception) {
            return null;
        }
    }
}
