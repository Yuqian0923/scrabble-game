package edu.cmu.cs.cs214.hw4.core;

import java.awt.*;

public abstract class LetterSquare implements Square {
    public LetterSquare() {
    }

    public abstract int getTimer();

    public abstract String getType();

    public abstract String getName();

    public abstract Color getColor();
}
