package io.my.stockmarket.cli;

import io.my.stockmarket.operations.FinOps;
import io.my.stockmarket.registry.Ticker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static io.my.stockmarket.registry.Ticker.POP;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

/**
 * ego on 2016.07
 */
@RunWith(MockitoJUnitRunner.class)
public class FinOpsCLITest {

    //TODO: re-factor CDI context initializer to make class mock-able
}