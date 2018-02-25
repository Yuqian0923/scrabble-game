package edu.cmu.cs.cs214.hw4.core;

import java.awt.Color;

public abstract class WordSquare implements Square {

    /**
     * get type.
     *
     * @return type.
     */
    public abstract String getType();

    /**
     * @param score new add score
     * @return score.
     */
    public abstract int calculateValue(int score);

    /**
     * get name.
     *
     * @return name.
     */
    public abstract String getName();

    /**
     * get color.
     *
     * @return color.
     */
    public abstract Color getColor();
}
