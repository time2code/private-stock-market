package io.my.stockmarket.cli;

import io.my.stockmarket.domain.TradeTx;
import io.my.stockmarket.operations.FinOps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.JLineShellComponent;
import org.springframework.shell.core.SimpleParser;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

import javax.enterprise.inject.spi.CDI;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static io.my.stockmarket.domain.TradeTxType.BUY;
import static io.my.stockmarket.domain.TradeTxType.SELL;
import static io.my.stockmarket.operations.FinOps.ALL_TXS;

/**
 * CLI for Financial Operations
 */
@Component
public class FinOpsCLI implements CommandMarker, ApplicationContextAware {

    private static final Logger log = LogManager.getFormatterLogger(FinOpsCLI.class);
    FinOps finOps = CDI.current().select(FinOps.class).get();

    private ApplicationContext ctx;

    @CliCommand(value = "finOps", help = "Print all available financial operations")
    public void finOps() {
        finOps.availableFinOps();
    }

    @CliCommand(value = "dividendYieldCommon", help = "Calculate Dividend Yield for 'common' type of stocks")
    public String dividendYieldCommon(
            @CliOption(key = {"ticker"}, mandatory = true, help = "ticker value is mandatory. E.g: dividendYieldCommon --ticker POP") final String ticker,
            @CliOption(key = {"price"}, mandatory = true, help = "price value is mandatory. E.g: dividendYieldCommon --price 2.17")
            final String price) {
        return finOps.dividendYieldCommon(ticker, price);
    }

    @CliCommand(value = "dividendYieldPreferred", help = "Calculate dividend yield for stocks of type 'preferred'")
    public String dividendYieldPreferred(
            @CliOption(key = {"ticker"}, mandatory = true, help = "ticker value is mandatory. E.g: dividendYieldPreferred --ticker POP ") final String ticker,
            @CliOption(key = {"price"}, mandatory = true, help = "price value is mandatory. E.g: dividendYieldPreferred --price 2.17")
            final String price) {
        return finOps.dividendYieldPreferred(ticker, price);
    }

    @CliCommand(value = "listTradeTxs", help = "List trade transactions either BUY or SELL")
    public void listTradeTxs(
            @CliOption(key = {"txType"}, mandatory = true, help = "One of transaction type BUY or SELL. E.g: listTradeTxs --txType SELL") final String txType
            ) {
        finOps.listTradeTxs(txType);
    }

    @CliCommand(value = "listAllTradeTxs", help = "List all trade transactions both BUY and SELL")
    public void listAllTradeTxs() {
        finOps.listTradeTxs(ALL_TXS);
    }

    @CliCommand(value = "vwap", help = "Volume weighted average price")
    public String vwap(
            @CliOption(key = {"ticker"}, mandatory = true, help = "ticker value is mandatory. E.g: dividendYieldCommon --ticker POP") final String ticker,
            @CliOption(key = {"period"}, mandatory = true, help = "Period of time in minutes to calculate vwap within. E.g: vwap --period 15") final String period
    ) {
        return finOps.vwap(ticker, Integer.parseInt(period));
    }

    //TODO: Add input validations for intended types!
    @CliCommand(value = "sellStock", help = "Sell Stock")
    public String sellStock(
            @CliOption(key = {"ticker"}, mandatory = true, help = "ticker value is mandatory. E.g: sellStock --ticker POP") final String ticker,
            @CliOption(key = {"quantity"}, mandatory = true, help = "Quantity to sell. E.g sellStock --quantity 101") final String quantity,
            @CliOption(key = {"price"}, mandatory = true, help = "sell price. E.g price --price 99") final String price
            ) {
        TradeTx tradeTx = TradeTx.builder()
                .stockTicker(ticker)
                .price(new BigDecimal(price))
                .quantity(Integer.parseInt(quantity))
                .txType(SELL)
                .time(LocalDateTime.now())
                .build();
        return finOps.trade(tradeTx);
    }

    @CliCommand(value = "buyStock", help = "Buy Stock")
    public String buyStock(
            @CliOption(key = {"ticker"}, mandatory = true, help = "ticker value is mandatory. E.g: sellStock --ticker POP") final String ticker,
            @CliOption(key = {"quantity"}, mandatory = true, help = "Quantity to sell. E.g sellStock --quantity 41") final String quantity,
            @CliOption(key = {"price"}, mandatory = true, help = "sell price. E.g price --price 17") final String price
    ) {
        TradeTx tradeTx = TradeTx.builder()
                .stockTicker(ticker)
                .price(new BigDecimal(price))
                .quantity(Integer.parseInt(quantity))
                .txType(BUY)
                .time(LocalDateTime.now())
                .build();
        return finOps.trade(tradeTx);
    }

    @CliCommand(value = "peRatio", help = "Calculate Price/Earnings Ratio")
    public String peRatio(
            @CliOption(key = {"ticker"}, mandatory = true, help = "ticker value is mandatory. E.g: peRatio --ticker POP") final String ticker,
            @CliOption(key = {"price"}, mandatory = true, help = "price value is mandatory. E.g: peRatio --price 2.17")
            final String price) {
        return finOps.peRatio(ticker, price);
    }

    @CliCommand(value = "geometricMean", help = "Geometric Mean")
    public String geometricMean() {
        return finOps.geometricMean();
    }

    @CliCommand(value = "sampleData", help = "Populate sample data set")
    public void sampleData() {
        finOps.sampleData();
    }

    @CliCommand(value = {"help"}, help = "List all commands usage")
    public void help(
            @CliOption(
                    key = {"", "command"},
                    optionContext = "disable-string-converter availableCommands",
                    help = "Command name to provide help for"
            ) String buffer) {
        JLineShellComponent shell = (JLineShellComponent) this.ctx.getBean("shell", JLineShellComponent.class);
        SimpleParser parser = shell.getSimpleParser();
        parser.obtainHelp(buffer);
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }
}
