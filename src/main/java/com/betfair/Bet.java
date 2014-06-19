package com.betfair;

public class Bet {

    public static enum Type {
        BACK,
        LAY
    }

    private final Type type;
    private final Market market;
    private final double odds;
    private final int price;

    public Bet(Type type, Market market, double odds, int price) {
        this.type = type;
        this.market = market;
        this.odds = odds;
        this.price = price;
    }

    public Type getType() {
        return type;
    }

    public Market getMarket() {
        return market;
    }

    public double getOdds() {
        return odds;
    }

    public int getPrice() {
        return price;
    }
}
