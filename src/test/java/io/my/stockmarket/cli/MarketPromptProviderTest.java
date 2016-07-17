package io.my.stockmarket.cli;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MarketPromptProviderTest {

    MarketPrompt marketPromptProvider = new MarketPrompt();

    @Test
    public void getPrompt() throws Exception {
        assertEquals(MarketPrompt.DARK_POOL, marketPromptProvider.getPrompt());
    }

}