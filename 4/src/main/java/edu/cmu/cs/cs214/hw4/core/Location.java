package edu.cmu.cs.cs214.hw4.core;

import edu.cmu.cs.cs214.hw4.core.Tile;

import java.util.ArrayList;

public class Location implements Comparable<Location> {
    /**
     * instance variable of int x.
     */
    private int x;
    /**
     * instance variable of int x.
     */
    private int y;
    /**
     * instance variable of int x.
     */
    private Tile tile;
    /**
     * instance variable of int x.
     */
    private ArrayList<SpecialTile> spList;
    /**
     * instance variable of int x.
     */
    private Square s;

    /**
     * Constructor.
     *
     * @param corx x.
     * @param cory y.
     */
    public Location(int corx, int cory) {
        x = corx;
        y = cory;
        tile = null;
        spList = new ArrayList<>();
    }

    /**
     * get the exact Tile on that location.
     *
     * @return tile tile.
     */

    public Tile getTile() {
        return tile;
    }

    /**
     * get the x coordinate of that location.
     *
     * @return x coodinate
     */

    public int getX() {
        return x;
    }

    /*
     * get the y coordinate of that location.
     *
     */
    public int getY() {
        return y;
    }

    /**
     * set different square type.
     *
     * @param square square.
     */
    public void setSquare(Square square) {
        if (square == null) {
            System.out.println("The input square is null!");
        } else {
            s = square;
        }
    }

    /**
     * return square.
     *
     * @return square
     */
    public Square getSquare() {
        if (s == null) {
            return null;
        }
        return this.s;
    }

    /**
     * return if this location is occupied.
     *
     * @return if this location is occupied.
     */
    public boolean isOccupied() {
        return tile != null;
    }

    /**
     * add the tile to the location.
     *
     * @param tile tile.
     */

    public void addTile(Tile tile) {
        if (isOccupied()) {
            //System.out.println("This location has been occupied!");
            return;
        }
        if (tile == null) {
            System.out.println("The added tile is null!");
            return;
        }
        this.tile = tile;
    }

    /*
     *
     * remove the tile from the location.
     */
    public void removeTile() {
        if (!isOccupied()) {
            System.out.println("This location is empty!");
            return;
        }
        this.tile = null;
    }

    /**
     * return specialTile list.
     *
     * @return specialTile list.
     */
    public ArrayList<SpecialTile> getSpecialTile() {
        return spList;
    }

    /**
     * set specialTile list.
     *
     * @param specialTile list.
     */

    public void setSpecialTile(SpecialTile specialTile) {
        this.spList.add(specialTile);
    }

    /**
     * remove special tile.
     *
     * @param specialTile special tile.
     */
    public void removeSpecialTile(SpecialTile specialTile) {
        if (spList.size() == 0) {
            System.out.println("There is no specialTile");
            return;
        }
        this.spList.remove(specialTile);
    }

    /**
     * * do special effect.
     *
     * @param game        this game.
     * @param specialTile special tile of tis location.
     */

    public void makeSpecialEffect(Game game, SpecialTile specialTile) {
        if (spList.size() != 0 && spList.contains(specialTile)) {
            specialTile.makeSpecialEffects(game, this);
        }
    }

    /**
     * get tile value.
     *
     * @return value.
     */
    public int getTileValue() {
        return tile.getValue();
    }

    /**
     * compare location list by their coordinates.
     *
     * @param o other Location object.
     * @return value.
     */
    @Override
    public int compareTo(Location o) {
        if (this.getX() == o.getX()) {
            return this.getY() - o.getY();
        } else if (this.getY() == o.getY()) {
            return this.getX() - o.getX();
        }
        return 0;
    }
}
