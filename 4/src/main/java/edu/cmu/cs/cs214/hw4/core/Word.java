package edu.cmu.cs.cs214.hw4.core;

import edu.cmu.cs.cs214.hw4.core.Tile;

import java.util.ArrayList;

public class Word {
    /*
    * the string representation of a word.
    */
    private String str;
    /*
     * the final score for a word.
     */
    private int score;
    /*
     * the final score for a word.
     */
    private ArrayList<Tile> tileList = new ArrayList<>();

    private ArrayList<Location> locations;

    private ArrayList<Location> curlocations;

    /**
     * Constructor of Word class. When create a new word, take the location and
     * calculate the word at the same time.
     *
     * @param str       word in string form.
     * @param locations location list from user input.
     */
    public Word(String str, ArrayList<Location> locations, ArrayList<Location> curlocations) {
        String curstr = "";
        for (Location loc : locations) {
            curstr += loc.getTile().getLetter();
        }
        this.str = curstr;
        this.locations = locations;
        this.curlocations = curlocations;
    }

    /*
     * pass a String to dict.
     */
    @Override
    public String toString() {
        return (this.str);
    }

    /*
     * get the final score of a word.
     */
    public int getScore() {
        System.out.println("testing getting store, cur word is:");
        System.out.println(str);
        System.out.println(locations.size());
        for (Location loc : locations) {
            tileList.add(loc.getTile());
            this.score += loc.getTile().getValue();
            System.out.println("adding score");
            System.out.println(loc.getTile().getLetter());
            System.out.println(loc.getTile().getValue());
            if (loc.getSquare() != null) {
                boolean incurlocations = false;
                for (Location loc2 : curlocations) {
                    if (loc2.getX() == loc.getX() && loc2.getY() == loc.getY()) {
                        incurlocations = true;
                    }
                }
                Square s = loc.getSquare();
                if (s.getType().equals("LS") && incurlocations == true) {
                    System.out.println("get here score if you are ls");
                    score += (s.getTimer() - 1) * loc.getTileValue();
                }
            }
        }
        for (Location loc : locations) {
            if (loc.getSquare() != null) {
                boolean incurlocations = false;
                for (Location loc2 : curlocations) {
                    if (loc2.getX() == loc.getX() && loc2.getY() == loc.getY()) {
                        incurlocations = true;
                    }
                }
                Square s = loc.getSquare();
                if (s.getType().equals("WS") && incurlocations == true) {
                    System.out.println("get here score if you are ws");
                    int newScore = loc.getSquare().calculateValue(score);
                    System.out.println(newScore);
                    score = newScore;
                }
            }
        }
        return this.score;
    }

}
