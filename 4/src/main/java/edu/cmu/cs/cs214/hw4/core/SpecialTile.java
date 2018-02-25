package edu.cmu.cs.cs214.hw4.core;

import edu.cmu.cs.cs214.hw4.core.Player;

public abstract class SpecialTile {
    /**
     * You must implement at least ﬁve types of special tiles for this assignment.
     */
    private Player owner;

    public abstract void makeSpecialEffects(Game game, Location loc);

    public abstract String getName();

    public abstract int getPrice();

    public Player getOwner() {
        return owner;
    }


    public abstract void setOwner(Player player);
}
