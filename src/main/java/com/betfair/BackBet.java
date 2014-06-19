package com.betfair;

public class BackBet extends Bet {
    public BackBet(Market market, double odds, int price) {
        super(Type.BACK, market, odds, price);
    }
}
