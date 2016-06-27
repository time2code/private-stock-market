package io.my.stockmarket.cli;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.JLineShellComponent;
import org.springframework.shell.core.SimpleParser;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

/**
 * Market CLI Operations
 */
@Component
public class MarketOpsCLI implements CommandMarker, ApplicationContextAware {

    private ApplicationContext ctx;

    @CliCommand(value = "stocks", help = "Print all available stocks")
    public String stocks() {
        return "Stocks print here";
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
