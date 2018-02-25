package edu.cmu.cs.cs214.hw4.core;

import java.awt.Color;

public interface Square {
    /**
     * @param score new add score
     * @return score.
     */

    public int calculateValue(int score);

    /**
     * get name.
     *
     * @return name.
     */
    public String getName();

    /**
     * get color.
     *
     * @return color.
     */
    public Color getColor();

    /**
     * get timer.
     *
     * @return timer.
     */
    public int getTimer();

    /**
     * get type.
     *
     * @return type.
     */
    public String getType();

}
