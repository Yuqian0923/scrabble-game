package edu.cmu.cs.cs214.hw4.core;

import java.util.ArrayList;
import java.util.Random;

public class TilePackage {
    /**
     * tile number.
     */
    private static final int[] TILENUM = new int[]{9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 2, 2, 4, 1, 2, 1};
    /**
     * tile score.
     */
    private static final int[] TILESCORE = new int[]{1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 4, 4, 1, 8, 4, 10};
    /**
     * tile name..
     */
    private static final String[] TILENAME = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
            "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    /**
     * tile list.
     */
    private ArrayList<Tile> tiles = new ArrayList<>();
    /**
     * tile number.
     */
    private int num;

    /**
     * initial tile package.
     */
    public void initial() {
        for (int i = 0; i < TILENAME.length; i++) {
            for (int j = 0; j < TILENUM[i]; j++) {
                tiles.add(new Tile(TILESCORE[i], TILENAME[i]));
                num++;
            }
        }
    }

    /**
     * check if it if empty.
     *
     * @return boolean
     */

    private boolean isEmpty() {
        return num == 0;
    }

    /**
     * Add tile to tile list.
     *
     * @param tile tile need to ba added.
     */
    public void addTile(Tile tile) {
        if (tile == null) {
            throw new IllegalArgumentException("The tile is null!");
        } else {
            tiles.add(tile);
            num++;
        }
    }

    /**
     * return how many tiles are in the package.
     *
     * @return num.
     */
    public int getNum() {
        return this.num;
    }

    /**
     * Get random index.
     *
     * @return num.
     */
    private int getRandom() {
        Random rand = new Random();
        int n = rand.nextInt(this.num);
        return n;
    }

    public int getValueByLetter(char letter) {
        for (char i = 'A'; i < '['; ++i) {
            if (letter == i) {
                return TILESCORE[i - 65];
            }
        }
        return -1;
    }

    /**
     * get tiles.
     *
     * @param number number of tiles.
     * @return tile list.
     */
    public ArrayList<Tile> getTiles(int number) {
        if (number > 0 && number <= 7) {
            if (isEmpty()) {
                System.out.println("The Tile Package is empty!");
                return new ArrayList<Tile>();
            } else if (number > num) {
                num = 0;
                ArrayList<Tile> newTiles = tiles;
                tiles = new ArrayList<>();
                return newTiles;
            } else {
                ArrayList<Tile> tmp = new ArrayList<Tile>();
                for (int i = 0; i < number; i++) {
                    int index = getRandom();
                    tmp.add(tiles.remove(index));
                    num--;
                }
                return tmp;
            }
        } else {
            System.out.println("The request tile number is invalid!");
            return new ArrayList<Tile>();
        }
    }

}
