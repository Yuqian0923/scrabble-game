package edu.cmu.cs.cs214.hw4.core;

import java.awt.*;

public class NormalSquare implements Square {
    /**
     * square color.
     */
    private Color color = new Color(0, 0, 0);
    /**
     * square type.
     */
    private final String type = "NS";

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
        return "NS";
    }

    /**
     * get color.
     *
     * @return color.
     */
    @Override
    public Color getColor() {
        return this.color;
    }

    /**
     * get timer.
     *
     * @return timer.
     */
    @Override
    public int getTimer() {
        return 0;
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
}
