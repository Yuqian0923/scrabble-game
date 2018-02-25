package edu.cmu.cs.cs214.hw4.core;

public class AdditionOrder extends SpecialTile {
    private static final int N_PRICE = 3;
    private static final String N_NAME = "AdditionOrder";
    /**
     * owner of ReverseOrder.
     */
    private Player owner = null;

    /**
     * You must implement at least ﬁve types of special tiles for this assignment
     *
     * @param game
     * @param location
     */
    @Override
    public void makeSpecialEffects(Game game, Location location) {
        game.setAdditionOrder(owner);
    }

    public int getPrice() {
        return N_PRICE;
    }

    @Override
    public void setOwner(Player player) {
        this.owner = player;
    }

    public String getName() {
        return N_NAME;
    }
}
