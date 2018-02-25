package edu.cmu.cs.cs214.hw4.core;

public class RemoveConsonants extends SpecialTile {
    /**
     * price of RemoveConsonants.
     */
    private static final int R_PRICE = 3;
    /**
     * name of RemoveConsonants.
     */
    private static final String R_NAME = "RemoveConsonants";
    /**
     * owner of RemoveConsonants.
     */
    private Player owner = null;


    @Override
    public void makeSpecialEffects(Game game, Location loc) {
        game.removeConsonants();
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

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getPrice() {
        return R_PRICE;
    }

    @Override
    public void setOwner(Player player) {
        this.owner = player;
    }
}
