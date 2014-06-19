package com.betfair;

/**
 * @author Martin Anderson
 */
public class Market {

    private final String id;

    public Market(String id) {
        assert(id != null);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        return id.equals(((Market) o).id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}