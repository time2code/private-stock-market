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

import static io.my.stockmarket.domain.TradeTxType.SELL;

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

    @CliCommand(value = "dividendYield", help = "Calculate Dividend Yield")
    public String dividendYield(
            @CliOption(key = {"ticker"}, mandatory = true, help = "ticker value is mandatory. E.g: dividendYield --ticker POP") final String ticker) {
        return finOps.dividendYield(ticker);
    }

    //TODO: Add input validations!
    @CliCommand(value = "sellStock", help = "Calculate Price/Earnings Ratio")
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
        return finOps.sellStock(tradeTx);
    }

    @CliCommand(value = "peRatio", help = "Calculate Price/Earnings Ratio")
    public String peRatio(
            @CliOption(key = {"ticker"}, mandatory = true, help = "ticker value is mandatory. E.g: peRatio --ticker POP") final String ticker) {
        return finOps.peRatio(ticker);
    }


    @CliCommand(value = {"help"}, help = "List all commands usage")
    public void obtainHelp(
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
