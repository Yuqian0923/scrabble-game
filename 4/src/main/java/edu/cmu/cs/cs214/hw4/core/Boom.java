package edu.cmu.cs.cs214.hw4.core;

public class Boom extends SpecialTile {
    /**
     * price of Boom.
     */
    private static final int B_PRICE = 3;
    /**
     * name of Boom.
     */
    private static final String B_NAME = "Boom";
    /**
     * radius of Boom.
     */
    private static final int B_RANGE = 3;
    /**
     * owner of ReverseOrder.
     */
    private Player owner = null;


    /**
     * get Name.
     */
    public String getName() {
        return B_NAME;
    }

    /**
     * get price.
     *
     * @return price.
     */
    public int getPrice() {
        return B_PRICE;
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
        game.setBoom(location, B_RANGE);
    }
}
