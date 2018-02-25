package edu.cmu.cs.cs214.hw4.core;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class testPlayer {
    private Player A;
    private Player B;
    private Player C;

    @Before
    public void setUp() {
        A = new Player("Grace", 1);
        B = new Player("Nancy", 2);
        C = new Player("George", 3);
    }

    @Test
    public void testGetter() {
        assertEquals(A.getName(), "Grace");
        assertEquals(B.getName(), "Nancy");
        assertEquals(C.getName(), "George");
        assertEquals(C.getId(), 3);
        assertEquals(B.getId(), 2);
        assertEquals(A.getId(), 1);
    }

    @Test
    public void testScore() {
        A.addScore(1);
        assertEquals(A.getScore(), 1);
        A.subScore(1);
        assertEquals(A.getScore(), 0);
        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.add(new Tile(1, "A"));
        Tile tile = new Tile(2, "B");
        tiles.add(tile);
        A.refillTile(tiles);
        A.getTile("B", 2);
        assertEquals(A.getTile("B", 2).getLetter(), "B");
        A.removeTiles(tiles);
        assertTrue(A.getTileList().size() == 0);
    }

}
