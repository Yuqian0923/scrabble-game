package edu.cmu.cs.cs214.hw4.core;

import java.awt.Color;

public class DoubleLetter extends LetterSquare {
    /**
     * square name.
     */
    private static final String DL_NAME = "2LS";
    /**
     * square color.
     */
    private static final Color TWO_LS = new Color(151, 255, 255);
    /**
     * square type.
     */
    private final String type = "LS";

    /**
     * get timer.
     *
     * @return timer.
     */
    @Override
    public int getTimer() {
        return 2;
    }

    /**
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
        return DL_NAME;
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
        return TWO_LS;
    }

}
