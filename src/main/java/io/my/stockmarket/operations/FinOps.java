package io.my.stockmarket.operations;

import io.my.stockmarket.domain.Stock;
import io.my.stockmarket.domain.TradeTx;
import io.my.stockmarket.domain.TradeTxType;
import io.my.stockmarket.infra.TickerToStock;
import io.my.stockmarket.metrics.DividendYield;
import io.my.stockmarket.metrics.FinOp;
import io.my.stockmarket.metrics.PERatio;
import io.my.stockmarket.registry.TradeCapture;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import static io.my.stockmarket.registry.TradeTxRegistry.TRANSACTIONS;
import static java.lang.String.format;

/**
 * Financial Operations
 */
public class FinOps {

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

    public void listSellTxs() {
        TRANSACTIONS.listTxs(TradeTxType.SELL);
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
}
