package edu.cmu.cs.cs214.hw4.core;

public class Tile {
    /**
     * tile value.
     */
    private int value;
    /**
     * tile character.
     */
    private String character;

    /**
     * Constructor.
     *
     * @param value value
     * @param chart character.
     */
    public Tile(int value, String chart) {
        this.value = value;
        this.character = chart;
    }

    /**
     * get value of tile.
     *
     * @return value value
     */
    public int getValue() {
        return value;
    }

    /**
     * get letter of tile.
     *
     * @return String letter.
     */
    public String getLetter() {
        return character;
    }
}
