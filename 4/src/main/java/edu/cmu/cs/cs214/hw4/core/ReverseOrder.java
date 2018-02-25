package edu.cmu.cs.cs214.hw4.core;

import edu.cmu.cs.cs214.hw4.core.Game;
import edu.cmu.cs.cs214.hw4.core.Location;
import edu.cmu.cs.cs214.hw4.core.Player;
import edu.cmu.cs.cs214.hw4.core.SpecialTile;

public class ReverseOrder extends SpecialTile {
    /**
     * price of ReverseOrder.
     */
    private static final int R_PRICE = 4;
    /**
     * name of ReverseOrder.
     */
    private static final String R_NAME = "ReverseOrder";
    /**
     * owner of ReverseOrder.
     */
    private Player owner = null;

    /**
     * get price.
     *
     * @return price.
     */
    public int getPrice() {
        return R_PRICE;
    }

    /**
     * get owner.
     *
     * @return Player.
     */
    @Override
    public Player getOwner() {

        return owner;
    }

    /**
     * set function.
     *
     * @param player player who own this special tile.
     */
    @Override
    public void setOwner(Player player) {
        this.owner = player;
    }

    /**
     * You must implement at least ﬁve types of special tiles for this assignment.
     *
     * @param game     this game.
     * @param location user input.
     */
    @Override
    public void makeSpecialEffects(Game game, Location location) {
        game.reverseOrder();
    }

    /**
     * getter.
     *
     * @return this name.
     */
    public String getName() {
        return R_NAME;
    }
}
