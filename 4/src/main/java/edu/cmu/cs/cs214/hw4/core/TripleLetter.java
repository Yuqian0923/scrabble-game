package edu.cmu.cs.cs214.hw4.core;

import java.awt.*;

public class TripleLetter extends LetterSquare {
    /**
     * square name.
     */
    private static final String TL_NAME = "3LS";
    /**
     * square type.
     */
    private final String type = "LS";
    /**
     * square color.
     */
    private static final Color THREE_LS = new Color(127, 255, 212);

    /**
     * get timer.
     *
     * @return timer.
     */
    @Override
    public int getTimer() {

        return 3;
    }

    /**
     * calculate word final score.
     *
     * @param score new add score
     * @return score.
     */
    @Override
    public int calculateValue(int score) {
        return 0;
    }

    /**
     * get name.
     *
     * @return name.
     */
    @Override
    public String getName() {
        return TL_NAME;
    }

    /**
     * get type.
     *
     * @return type.
     */

    public String getType() {
        return type;
    }

    /**
     * get color.
     *
     * @return color.
     */
    @Override
    public Color getColor() {
        return THREE_LS;
    }
}

