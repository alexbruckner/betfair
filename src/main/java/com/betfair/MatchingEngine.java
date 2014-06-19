package com.betfair;

import com.sun.tools.javap.JavapTask;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Martin Anderson
 */
public class MatchingEngine {

    private final Map<Market, List<MatchedBet>> matchedBets = new ConcurrentHashMap<Market, List<MatchedBet>>();
    private final Map<Market, List<BackBet>> backBets = new ConcurrentHashMap<Market, List<BackBet>>();
    private final Map<Market, List<LayBet>> layBets = new ConcurrentHashMap<Market, List<LayBet>>();
    private final Status status = new Status();

    public void add(Bet bet) {
        dealWithBet(bet);
    }

    private void dealWithBet(Bet bet) {

        List<? extends Bet> betsList = getMatchingList(bet);

        Bet match = null;

        for (Bet layBet : betsList) {
            if (layBet.getPrice() == bet.getPrice()) {
                match = layBet;
                break;
            }
        }

        if (match != null) { // remove from list and add to matched
            matchBet(bet, match, betsList);
        } else { // add to list
            addBet(bet);
        }
    }

    private void addBet(Bet bet) {
        if (bet.getType() == Bet.Type.BACK) {
            List<BackBet> backBets = getBackBets(bet.getMarket());
            backBets.add((BackBet) bet);
        } else {
            List<LayBet> backBets = getLayBets(bet.getMarket());
            backBets.add((LayBet) bet);
        }
    }

    private List<? extends Bet> getMatchingList(Bet bet) {
         switch (bet.getType()) {
             case BACK: return getLayBets(bet.getMarket());
             case LAY: return getBackBets(bet.getMarket());
             default: return null;
         }
    }

    private void matchBet(Bet bet, Bet match, List<? extends Bet> betsList) {
        betsList.remove(match);
        List<MatchedBet> matchedBets = getMatchedBets(bet.getMarket());
        if (bet.getType() == Bet.Type.BACK) {
            matchedBets.add(new MatchedBet((BackBet) bet, (LayBet) match));
        } else {
            matchedBets.add(new MatchedBet((BackBet) match, (LayBet) bet));
        }
    }

    private List<BackBet> getBackBets(Market market) {
        return getList(market, backBets);
    }

    private List<MatchedBet> getMatchedBets(Market market) {
        return getList(market, matchedBets);
    }

    private List<LayBet> getLayBets(Market market) {
        return getList(market, layBets);
    }

    private static <K,T> List<T> getList(K key, Map<K, List<T>> map) {
        List<T> list = map.get(key);
        if (list == null){
            list = new CopyOnWriteArrayList<T>();
            map.put(key, list);
        }
        return list;
    }

    public Status getStatus() {
        return status;
    }

    public class Status {
        public List<MatchedBet> getMatched(Market market) {
            return Collections.unmodifiableList(getMatchedBets(market));
        }
        public List<BackBet> getUnmatchedBackbets(Market market) {
            return Collections.unmodifiableList(getBackBets(market));
        }
        public List<LayBet> getUnmatchedLaybets(Market market) {
            return Collections.unmodifiableList(getLayBets(market));
        }
    }

}
