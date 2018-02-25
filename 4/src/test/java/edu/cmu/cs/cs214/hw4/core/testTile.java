package edu.cmu.cs.cs214.hw4.core;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class testTile {
    private Tile tile;

    @Before
    public void setUp() {
        tile = new Tile(1, "E");
    }

    @Test
    public void testGetter() {
        assertEquals(tile.getLetter(), "E");
        assertEquals(tile.getValue(), 1);
    }
}
