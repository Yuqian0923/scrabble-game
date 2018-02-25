package edu.cmu.cs.cs214.hw4.core;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class testBoard {
    private Board board;
    private Tile tile;
    private Location loc1;
    private Location loc2;
    private Location loc3;
    private Location loc4;
    private Location loc5;

    @Before
    public void setUp() {
        board = new Board();
        tile = new Tile(1, "E");

    }

    @Test
    public void testValidation() {
        assertTrue(!board.isOnBoard(15, 15));
        assertTrue(board.isOnBoard(0, 0));
        assertTrue(board.isOnBoard(1, 0));
        assertTrue(!board.isOnBoard(-1, 0));
        board.addTileToLocation(tile, 12, 2);
        assertTrue(board.isOccupied(12, 2));
        board.removeTileFromLocation(12, 2);
        assertTrue(!board.isOccupied(12, 2));
        assertTrue(board.isEmpty());
    }

    @Test
    public void testFirstMove() {
        loc1 = board.getLocation(7, 7);
        loc1 = board.getLocation(8, 7);
        loc1 = board.getLocation(9, 7);
        Location[] list = {loc1, loc2, loc3};
        assertTrue(board.isOnStar(list));
    }

    @Test
    public void testStoreWord() {
        //same col
        loc1 = board.getLocation(5, 0);
        loc1.addTile(new Tile(1, "A"));
        loc2 = board.getLocation(5, 1);
        loc2.addTile(new Tile(1, "P"));
        loc3 = board.getLocation(5, 2);
        loc3.addTile(new Tile(1, "P"));
        loc4 = board.getLocation(5, 3);
        loc4.addTile(new Tile(1, "L"));
        loc5 = board.getLocation(5, 4);
        loc5.addTile(new Tile(1, "E"));
        Location loc6 = board.getLocation(4, 1);
        loc6.addTile(new Tile(4, "O"));
        Location[] list = {loc2, loc1, loc3, loc4, loc5};
        ArrayList<Location> array = new ArrayList<>();
        array.addAll(Arrays.asList(list));
        ArrayList<Location> array2 = new ArrayList<>();
        array2.add(loc6);
        array2.add(loc5);
        Word word = new Word("APPLE", array, array);
        Word word2 = new Word("OP", array2, array2);
        assertEquals(board.sameColWord(list).get(0).toString(), word.toString());
        assertEquals(board.storeWord(list).get(0).toString(), word.toString());
        assertEquals(board.checkLeftRightCol(list).get(0).toString(), word2.toString());
        assertEquals(board.storeWord(list).get(1).toString(), word2.toString());

    }

    @Test
    public void testValidTile() {
        loc1 = board.getLocation(1, 7);
        loc2 = board.getLocation(2, 7);
        loc3 = board.getLocation(3, 7);
        Location[] list = {loc2, loc1, loc3};
        assertTrue(board.isValidTile(list));
        loc2 = board.getLocation(3, 7);
        list[1] = loc2;
        assertTrue(!board.isValidTile(list));
        loc2 = board.getLocation(8, 4);
        list[1] = loc2;
        assertTrue(!board.isValidTile(list));
    }

    @Test
    public void testValidLocation() {
        loc1 = board.getLocation(7, 7);
        loc2 = board.getLocation(8, 7);
        loc3 = board.getLocation(9, 7);
        Location loc4 = board.getLocation(10, 7);
        Location loc5 = board.getLocation(10, 8);
        Location loc6 = board.getLocation(10, 9);
        loc3.addTile(tile);
        Location[] list = {loc4, loc5, loc6};
        assertTrue(board.isValidLocation(list));
    }

    @Test
    public void testStoreWord2() {
        loc1 = board.getLocation(2, 0);
        loc1.addTile(new Tile(1, "A"));
        loc2 = board.getLocation(3, 0);
        loc2.addTile(new Tile(1, "B"));
        loc3 = board.getLocation(4, 0);
        loc3.addTile(new Tile(1, "C"));
        ////////////////////////////////////////////////
        Location loc4 = board.getLocation(2, 1);
        loc4.addTile(new Tile(1, "D"));
        Location loc5 = board.getLocation(2, 2);
        loc5.addTile(new Tile(1, "D"));
        Location loc6 = board.getLocation(2, 3);
        loc6.addTile(new Tile(1, "T"));

        Location loc7 = board.getLocation(2, 4);
        loc7.addTile(new Tile(1, "B"));
        Location loc8 = board.getLocation(2, 5);
        loc8.addTile(new Tile(1, "C"));
        Location[] list = {loc1, loc4, loc5, loc6, loc7, loc8};
        Location loc9 = board.getLocation(3, 4);
        loc9.addTile(new Tile(1, "D"));
        Location loc10 = board.getLocation(4, 4);
        loc10.addTile(new Tile(1, "B"));
        ArrayList<Location> array = new ArrayList<>();
        array.addAll(Arrays.asList(list));
        Word word = new Word("ADDTBC", array, array);
        ArrayList<Location> array2 = new ArrayList<>();
        array2.add(loc1);
        array2.add(loc2);
        array2.add(loc3);
        Word word2 = new Word("ABC", array2, array2);
        ArrayList<Location> array3 = new ArrayList<>();
        array3.add(loc7);
        array3.add(loc9);
        array3.add(loc10);
        Word word3 = new Word("BDB", array3, array3);
        assertEquals(word3.getScore(),6);
        assertEquals(board.sameColWord(list).get(0).toString(), word.toString());
        System.out.println(board.checkLeftRightCol(list).get(0).toString());
        assertEquals(board.storeWord(list).get(1).toString(), word2.toString());
        assertEquals(board.storeWord(list).get(2).toString(), word3.toString());
        Location[] list2 = {loc4, loc5, loc6};
        assertTrue(board.storeWord(list2).size() == 1);
        assertEquals(board.sameColWord(list2).get(0).toString(), word.toString());
    }

    @Test
    public void testStoreWord3() {
        loc1 = board.getLocation(2, 0);
        loc1.addTile(new Tile(1, "C"));
        loc2 = board.getLocation(0, 1);
        loc2.addTile(new Tile(3, "B"));
        loc3 = board.getLocation(1, 1);
        loc3.addTile(new Tile(1, "O"));
        Location loc4 = board.getLocation(2, 1);
        loc4.addTile(new Tile(1, "A"));
        Location loc5 = board.getLocation(2, 2);
        loc5.addTile(new Tile(1, "L"));
        Location loc6 = board.getLocation(3, 1);/////loc6
        loc6.addTile(new Tile(3, "B"));
        Location loc7 = board.getLocation(4, 1);
        loc7.addTile(new Tile(3, "C"));
        Location loc8 = board.getLocation(5, 1);
        loc8.addTile(new Tile(1, "A"));
        Location loc9 = board.getLocation(6, 1);
        loc9.addTile(new Tile(1, "T"));
        Location loc11 = board.getLocation(3, 2);
        loc11.addTile(new Tile(1, "A"));/////loc11
        Location loc12 = board.getLocation(3, 3);
        loc12.addTile(new Tile(2, "D"));/////loc12
        Location[] list = {loc6, loc11, loc12};
        assertEquals(board.sameColWord(list).get(0).toString(), "BAD");
        assertEquals(board.checkLeftRightCol(list).get(0).toString(), "BOABCAT");
        assertEquals(board.checkLeftRightCol(list).size(), 2);
        assertEquals(board.checkLeftRightCol(list).get(1).toString(), "LA");
    }

    @Test
    public void testStoreWord4() {
        loc1 = board.getLocation(5, 0);
        loc1.addTile(new Tile(1, "A"));
        loc2 = board.getLocation(5, 1);
        loc2.addTile(new Tile(1, "P"));
        loc3 = board.getLocation(5, 2);
        loc3.addTile(new Tile(1, "P"));
        loc4 = board.getLocation(5, 3);
        loc4.addTile(new Tile(1, "L"));
        loc5 = board.getLocation(5, 4);
        loc5.addTile(new Tile(1, "E"));
        Location loc6 = board.getLocation(4, 1);
        loc6.addTile(new Tile(4, "O"));
        Location loc7 = board.getLocation(0, 2);
        loc7.addTile(new Tile(1, "H"));
        ///////////////////////new//////////////////////////////////
        Location loc8 = board.getLocation(1, 2);
        loc8.addTile(new Tile(4, "A"));
        Location loc9 = board.getLocation(2, 2);
        loc9.addTile(new Tile(4, "P"));
        Location loc10 = board.getLocation(3, 2);
        loc10.addTile(new Tile(4, "P"));
        Location loc11 = board.getLocation(4, 2);
        loc11.addTile(new Tile(4, "Y"));
        Location[] list = {loc8, loc9, loc10, loc11};
        //System.out.println(board.sameRowWord(list).get(0).toString());
        assertEquals(board.sameRowWord(list).get(0).toString(), "HAPPYP");
        assertEquals(board.storeWord(list).get(0).toString(),"HAPPYP");
        assertEquals(board.storeWord(list).get(1).toString(),"OY");
        assertEquals(board.storeWord(list).size(),2);
    }

}
