package edu.cmu.cs.cs214.hw4.core;

import java.awt.Color;

public class TripleWord extends WordSquare {
    /**
     * square name.
     */
    private static final String NAME = "3WS";
    /**
     * square type.
     */
    private final String type = "WS";
    /**
     * square color.
     */
    private static final Color COLOR = new Color(255, 165, 0);
    /**
     * square timer.
     */
    private int timer = 3;

    /**
     * calculate word final score.
     * @param score new add score
     * @return score.
     */
    @Override
    public int calculateValue(int score) {

        return 3 * score;
    }

    /**
     * get timer.
     *
     * @return timer.
     */
    public int getTimer() {
        return 1;
    }

    /**
     * get type.
     *
     * @return type.
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * get name.
     *
     * @return name.
     */
    public String getName() {
        return NAME;
    }

    /**
     * get color.
     *
     * @return color.
     */
    public Color getColor() {
        return COLOR;
    }
}
