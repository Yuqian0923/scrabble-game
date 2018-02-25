package edu.cmu.cs.cs214.hw4.core;

import java.awt.Color;

public class DoubleWord extends WordSquare {
    /**
     * square name.
     */
    private final String name = "DWS";
    /**
     * square type.
     */
    private final String type = "WS";
    /**
     * square color.
     */
    private final Color color = new Color(255, 174, 185);

    /**

     */
    @Override
    public int calculateValue(int score) {

        return 2 * score;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public Color getColor() {
        return color;
    }

    /**
     * get timer.
     *
     * @return timer.
     */
    @Override
    public int getTimer() {
        return 1;
    }

}
