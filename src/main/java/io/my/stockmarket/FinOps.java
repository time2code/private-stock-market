package io.my.stockmarket;

import io.my.stockmarket.cli.FinOpsCLI;
import io.my.stockmarket.metrics.FinOp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.shell.core.CommandMarker;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;

/**
 * Financial Operations
 */
public class FinOps {

    private static final Logger log = LogManager.getFormatterLogger(FinOps.class);

    @Inject
    private Instance<FinOp> finOps;

    public void availableFinOps() {
        log.info("Financial Operations:");
        finOps.forEach(finOp -> log.info(finOp.name()));
    }
}
