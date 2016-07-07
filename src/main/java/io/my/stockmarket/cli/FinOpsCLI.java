package io.my.stockmarket.cli;

import io.my.stockmarket.FinOps;
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

/**
 * CLI for Financial Operations
 */
@Component
public class FinOpsCLI implements CommandMarker, ApplicationContextAware {

    FinOps finOps = CDI.current().select(FinOps.class).get();

    private ApplicationContext ctx;

    @CliCommand(value = "finops", help = "Print all available financial operations")
    public void finOps() {
        finOps.availableFinOps();
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
