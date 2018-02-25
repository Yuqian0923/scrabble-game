package edu.cmu.cs.cs214.hw4.core;

import edu.cmu.cs.cs214.hw4.core.Game;

public class NegativePoints extends SpecialTile {
    /**
     * price of Negative points.
     */
    private static final int N_PRICE = 3;
    /**
     * name of Negative points.
     */
    private static final String N_NAME = "NegativePoints";
    /**
     * owner of ReverseOrder.
     */
    private Player owner = null;

    /**
     * The word that activated this tile is scored negatively for the player who
     * activated the tile; i.e., the player loses (rather than gains) the points
     * for the played word.
     */
    @Override
    public void makeSpecialEffects(Game game, Location location) {
        game.setNegativePoints();
    }

    /**
     * get price.
     *
     * @return price.
     */
    public int getPrice() {
        return N_PRICE;
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
     */

    public String getName() {
        return N_NAME;
    }
}
