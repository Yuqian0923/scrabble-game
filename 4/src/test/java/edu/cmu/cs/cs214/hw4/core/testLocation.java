package edu.cmu.cs.cs214.hw4.core;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class testLocation {
    private Location location1;
    private Tile tile;
    private SpecialTile sp;

    @Before
    public void setUp() {
        location1 = new Location(1, 3);
        tile = new Tile(1, "A");
        sp = new Boom();

    }

    @Test
    public void testLoc() {
        assertEquals(location1.getX(), 1);
        assertEquals(location1.getY(), 3);
        location1.addTile(new Tile(1, "A"));
        assertEquals(location1.getTile().getLetter(), "A");
        TripleWord s = new TripleWord();
        location1.setSquare(s);
        assertEquals(location1.getSquare().getType(), "WS");
        location1.addTile(tile);
        location1.removeTile();
        assertTrue(!location1.isOccupied());
        location1.setSpecialTile(sp);
        assertEquals(location1.getSpecialTile().get(0).getName(), "Boom");
        location1.removeSpecialTile(sp);
        assertEquals(location1.getSpecialTile().size(), 0);
        location1.addTile(new Tile(1, "A"));
        assertEquals(location1.getTileValue(),1);
    }
}
