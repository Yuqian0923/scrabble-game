package edu.cmu.cs.cs214.hw4.core;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class testPackage {
    private TilePackage pack;

    @Before
    public void setUp() {
        pack = new TilePackage();
    }

    @Test
    public void testEmpty() {
        Tile tile = new Tile(1, "E");
        pack.addTile(tile);
        assertTrue(pack.getNum() == 1);
        assertTrue(tile.getValue() == 1);
        assertEquals(tile.getLetter(), "E");
        ArrayList<Tile> list = new ArrayList<>();
        list = pack.getTiles(1);
        assertEquals(list.get(0).getLetter(),"E");
        pack.addTile(tile);
        pack.addTile(tile);
        pack.addTile(tile);
        list = pack.getTiles(2);
        assertTrue(pack.getNum() == 1);
        for(Tile t:list){
            assertEquals(t.getLetter(),"E");
        }
    }


}
