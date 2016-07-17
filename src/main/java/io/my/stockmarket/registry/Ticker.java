package io.my.stockmarket.registry;

/**
 * Known tickers at system startup
 */
public enum Ticker {

    TEA("TEA"),
    POP("POP"),
    ALE("ALE"),
    GIN("GIN"),
    JOE("JOE"),
    ;

    private String ticker;

    Ticker(String ticker) {
        this.ticker = ticker;
    }
}
