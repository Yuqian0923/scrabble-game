package edu.cmu.cs.cs214.hw4.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Player {
    /*
     * the string representation of Player name.
     */
    private String name;
    /*
     * the string representation of Player id.
     */
    private int id;
    /*
     * the score of each player.
     */
    private int score;
    /*
     * the board for each player.
     */
    private Board board;
    /*
     * the tile List of each player.
     */
    private ArrayList<Tile> tileList = new ArrayList<>();
    /*
     * the inventory of each player.
     */
    private final int inventorySize = 7;

    /**
     * Constructor.
     *
     * @param name player name
     * @param id   player id.
     */

    public Player(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public int getInventorySize() {
        return this.inventorySize;
    }

    public String showInventory() {
        StringBuffer sb = new StringBuffer();
        Iterator inventory = this.tileList.iterator();

        while (inventory.hasNext()) {
            Tile tile = (Tile) inventory.next();
            sb.append(" | " + tile.getLetter() + " " + tile.getValue());
        }

        sb.append(" | ");
        System.out.println(sb.toString().length());
        return sb.toString();
    }

    /**
     * return player name.
     *
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * return id.
     *
     * @return player id.
     */
    public int getId() {
        return this.id;
    }

    /**
     * return tile list.
     *
     * @return tile list.
     */
    public ArrayList<Tile> getTileList() {
        return this.tileList;
    }

    /**
     * return player score.
     *
     * @return score.
     */
    public int getScore() {
        return this.score;
    }

    /**
     * add score for player.
     *
     * @param value value.
     */
    public void addScore(int value) {
        this.score += value;
    }

    /**
     * substract value for player.
     *
     * @param value value
     */
    public void subScore(int value) {
        this.score -= value;
    }

    /**
     * get tile from package.
     *
     * @param letter character.
     * @param value  score.
     * @return tile obj.
     */
    public Tile getTile(String letter, int value) {
        for (Tile tile : tileList) {
            if (tile.getLetter().equals(letter) && tile.getValue() == value) {
                return tile;
            }
        }
        return null;
    }

    public Boolean isIn(ArrayList<Tile> tiles) {
        if (tiles != null && tiles.size() <= this.tileList.size()) {
            ArrayList<Tile> deleteTiles = new ArrayList<>(tiles);
            ArrayList<Tile> checkTiles = new ArrayList<>(this.tileList);
            int sizePrev = checkTiles.size();
            int deleteSize = deleteTiles.size();
            Iterator var7 = deleteTiles.iterator();

            while (true) {
                while (var7.hasNext()) {
                    Tile tile = (Tile) var7.next();

                    for (int i = 0; i < checkTiles.size(); i++) {
                        if (Objects.equals((checkTiles.get(i)).getLetter(), tile.getLetter()) && (checkTiles.get(i)).getValue() == tile.getValue()) {
                            checkTiles.remove(i);
                            break;
                        }
                    }
                }

                if (checkTiles.size() != sizePrev - deleteSize) {
                    return false;
                }

                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * remove tiles from inventory.
     *
     * @param tiles remove tiles from inventory
     */
    public void removeTiles(ArrayList<Tile> tiles) {
        for (int i = 0; i < tiles.size(); i++) {

            /*if (!tileList.contains(tile)) {
                System.out.println("There is no such tile in the TilePackage");
                break;
            }*/
            tileList.remove(i);

        }

    }

    /**
     * add more tile.
     *
     * @param tiles add more tile.
     */

    public void refillTile(ArrayList<Tile> tiles) {
        if (tiles == null) {
            throw new IllegalArgumentException("Input tiles can not be null");
        }
        if (tiles.size() == 0) {
            return;
        }
        for (Tile tile : tiles) {
            this.tileList.add(tile);
        }

    }

    /**
     * check if the inventory should be add.
     *
     * @return boolean.
     */
    public boolean isEmpty() {
        return tileList.size() < inventorySize;
    }


}
