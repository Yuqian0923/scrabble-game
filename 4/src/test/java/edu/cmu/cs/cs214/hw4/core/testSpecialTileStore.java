package edu.cmu.cs.cs214.hw4.core;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class testSpecialTileStore {
    private SpecialTileStore store;
    private Player playerA;

    @Before
    public void setUp() {
        store = new SpecialTileStore();
        playerA = new Player("Andy", 1);
        playerA.addScore(50);

    }

    @Test
    public void test() {
        assertEquals(store.getSpNum(), 3);
        assertTrue(store.isInStore("ReverseOrder"));
        assertEquals(store.buySpecialTile(playerA, "NegativePoints").getName(),"NegativePoints");

    }
}
