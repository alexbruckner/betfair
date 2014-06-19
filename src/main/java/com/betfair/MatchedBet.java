package com.betfair;

public class MatchedBet {

    private final BackBet back;
    private final LayBet lay;

    public MatchedBet(BackBet back, LayBet lay) {
        assert(back.getMarket().equals(lay.getMarket()));
        assert(back.getPrice() == lay.getPrice());
        this.back = back;
        this.lay = lay;
    }

    public BackBet getBack() {
        return back;
    }

    public LayBet getLay() {
        return lay;
    }
}
