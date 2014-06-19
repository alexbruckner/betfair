package com.betfair;

public class LayBet extends Bet {
    public LayBet(Market market, double odds, int price) {
        super(Type.LAY, market, odds, price);
    }
}
