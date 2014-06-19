package com.betfair;


import junit.framework.Assert;
import org.junit.Test;

/**
 * Scenario 1
 * Input is: 1 Back bet on Market A, 1 Lay bet on Market A
 * Output is: 1 Matched bet on Market A
 * <p/>
 * Scenario 2
 * Input is: 1 Back bet on Market A, 1 Lay bet on Market B
 * Output is: No Matched bets, 1 Back bet on Market A, 1 Lay bet on Market B
 * <p/>
 * Scenario 3
 * Input is: 1 Back bet on Market A, 1 Back bet on Market B
 * Output is: No Matched bets, 1 Back bet on Market A, 1 Back bet on Market B
 * <p/>
 * Scenario 4
 * Input is: 2 Back bets on Market A, 1 Lay bet on Market A
 * Output is: 1 Matched bet on Market A, 1 Back bet on Market A
 * <p/>
 * Scenario 5
 * Input is: 1 Back bet on Market A, 1 Lay bet on Market A, 1 Back bet on Market B, 1 Lay bet on Market B
 * Output is: 1 Matched bet on Market A, 1 Matched bet on Market B
 * <p/>
 * Scenario 6
 * Input is: 2 Back bets on Market A, 1 Lay bet on Market A, 1 Back bet on Market B, 2 Lay bets on Market B
 * Output is: 1 Matched bet on Market A, 1 Matched bet on Market B, 1 Back bet on Market A, 1 Lay bet on Market B
 */
public class MatchingEngineTest {

    private static enum Markets {
        A("A"),
        B("B");

        private final Market market;

        Markets(String id) {
            this.market = new Market(id);
        }

        private Market getMarket() {
            return market;
        }
    }

    private static double odds = 2.0;
    private static int price = 1000;

    /**
     * Scenario 1
     * Input is: 1 Back bet on Market A, 1 Lay bet on Market A
     * Output is: 1 Matched bet on Market A
     */
    @Test
    public void scenario1() {
        MatchingEngine engine = new MatchingEngine();
        engine.add(new BackBet(Markets.A.getMarket(), odds, price));
        engine.add(new LayBet(Markets.A.getMarket(), odds, price));
        MatchingEngine.Status status = engine.getStatus();
        Assert.assertTrue(status.getMatched(Markets.A.getMarket()).size() == 1);
    }

    /**
     * Scenario 2
     * Input is: 1 Back bet on Market A, 1 Lay bet on Market B
     * Output is: No Matched bets, 1 Back bet on Market A, 1 Lay bet on Market B
     */
    @Test
    public void scenario2() {
        MatchingEngine engine = new MatchingEngine();
        engine.add(new BackBet(Markets.A.getMarket(), odds, price));
        engine.add(new LayBet(Markets.B.getMarket(), odds, price));
        MatchingEngine.Status status = engine.getStatus();

        Assert.assertTrue(status.getMatched(Markets.A.getMarket()).size() == 0);
        Assert.assertTrue(status.getMatched(Markets.B.getMarket()).size() == 0);

        Assert.assertTrue(status.getUnmatchedBackbets(Markets.A.getMarket()).size() == 1);
        Assert.assertTrue(status.getUnmatchedLaybets(Markets.B.getMarket()).size() == 1);

    }


    /**
     * Scenario 3
     * Input is: 1 Back bet on Market A, 1 Back bet on Market B
     * Output is: No Matched bets, 1 Back bet on Market A, 1 Back bet on Market B
     */
    @Test
    public void scenario3() {
        MatchingEngine engine = new MatchingEngine();
        engine.add(new BackBet(Markets.A.getMarket(), odds, price));
        engine.add(new BackBet(Markets.B.getMarket(), odds, price));
        MatchingEngine.Status status = engine.getStatus();

        Assert.assertTrue(status.getMatched(Markets.A.getMarket()).size() == 0);
        Assert.assertTrue(status.getMatched(Markets.B.getMarket()).size() == 0);

        Assert.assertTrue(status.getUnmatchedBackbets(Markets.A.getMarket()).size() == 1);
        Assert.assertTrue(status.getUnmatchedBackbets(Markets.B.getMarket()).size() == 1);

    }

    /**
     * Scenario 4
     * Input is: 2 Back bets on Market A, 1 Lay bet on Market A
     * Output is: 1 Matched bet on Market A, 1 Back bet on Market A
     */
    @Test
    public void scenario4() {
        MatchingEngine engine = new MatchingEngine();
        engine.add(new BackBet(Markets.A.getMarket(), odds, price));
        engine.add(new BackBet(Markets.A.getMarket(), odds, price));
        engine.add(new LayBet(Markets.A.getMarket(), odds, price));
        MatchingEngine.Status status = engine.getStatus();

        Assert.assertTrue(status.getMatched(Markets.A.getMarket()).size() == 1);
        Assert.assertTrue(status.getUnmatchedBackbets(Markets.A.getMarket()).size() == 1);

    }

    /**
     * Scenario 5
     * Input is: 1 Back bet on Market A, 1 Lay bet on Market A, 1 Back bet on Market B, 1 Lay bet on Market B
     * Output is: 1 Matched bet on Market A, 1 Matched bet on Market B
     */
    @Test
    public void scenario5() {
        MatchingEngine engine = new MatchingEngine();
        engine.add(new BackBet(Markets.A.getMarket(), odds, price));
        engine.add(new LayBet(Markets.A.getMarket(), odds, price));
        engine.add(new BackBet(Markets.B.getMarket(), odds, price));
        engine.add(new LayBet(Markets.B.getMarket(), odds, price));
        MatchingEngine.Status status = engine.getStatus();

        Assert.assertTrue(status.getMatched(Markets.A.getMarket()).size() == 1);
        Assert.assertTrue(status.getMatched(Markets.B.getMarket()).size() == 1);

        Assert.assertTrue(status.getUnmatchedBackbets(Markets.A.getMarket()).size() == 0);
        Assert.assertTrue(status.getUnmatchedBackbets(Markets.B.getMarket()).size() == 0);

        Assert.assertTrue(status.getUnmatchedLaybets(Markets.A.getMarket()).size() == 0);
        Assert.assertTrue(status.getUnmatchedLaybets(Markets.B.getMarket()).size() == 0);

    }

    /**
     *
     * Scenario 6
     * Input is: 2 Back bets on Market A, 1 Lay bet on Market A, 1 Back bet on Market B, 2 Lay bets on Market B
     * Output is: 1 Matched bet on Market A, 1 Matched bet on Market B, 1 Back bet on Market A, 1 Lay bet on Market B
     */
    @Test
    public void scenario6() {
        MatchingEngine engine = new MatchingEngine();
        engine.add(new BackBet(Markets.A.getMarket(), odds, price));
        engine.add(new BackBet(Markets.A.getMarket(), odds, price));
        engine.add(new LayBet(Markets.A.getMarket(), odds, price));
        engine.add(new BackBet(Markets.B.getMarket(), odds, price));
        engine.add(new LayBet(Markets.B.getMarket(), odds, price));
        engine.add(new LayBet(Markets.B.getMarket(), odds, price));
        MatchingEngine.Status status = engine.getStatus();

        Assert.assertTrue(status.getMatched(Markets.A.getMarket()).size() == 1);
        Assert.assertTrue(status.getMatched(Markets.B.getMarket()).size() == 1);

        Assert.assertTrue(status.getUnmatchedBackbets(Markets.A.getMarket()).size() == 1);
        Assert.assertTrue(status.getUnmatchedBackbets(Markets.B.getMarket()).size() == 0);

        Assert.assertTrue(status.getUnmatchedLaybets(Markets.A.getMarket()).size() == 0);
        Assert.assertTrue(status.getUnmatchedLaybets(Markets.B.getMarket()).size() == 1);

    }



}
