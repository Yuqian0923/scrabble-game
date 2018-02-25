package edu.cmu.cs.cs214.hw4.core;

import java.util.ArrayList;
import java.util.Iterator;

public class SpecialTileStore {
    /**
     * specialTile name.
     */
    private ArrayList<SpecialTile> specialTileNames = new ArrayList<>();
    /**
     * specialTile number.
     */
    private int spNum;
    private SpecialTile Boom = new Boom();
    private SpecialTile AdditionOrder = new AdditionOrder();
    private SpecialTile NegativePoints = new NegativePoints();
    private SpecialTile ReverseOrder = new ReverseOrder();
    private SpecialTile RemoveConsonants = new RemoveConsonants();

    /**
     * Constructor.
     */
    public SpecialTileStore() {
        initial();
    }

    public SpecialTile getSpecialTile(String name) {
        if (name.equals("Boom")) {
            return this.Boom;
        }
        if (name.equals("AdditionOrder")) {
            return this.AdditionOrder;
        }
        if (name.equals("NegativePoints")) {
            return this.NegativePoints;
        }
        if (name.equals("ReverseOrder")) {
            return this.ReverseOrder;
        }
        if (name.equals("RemoveConsonants")) {
            return this.RemoveConsonants;
        }
        return null;
    }

    private void initial() {
        this.specialTileNames.add(this.Boom); //3
        this.specialTileNames.add(this.AdditionOrder); //3
        this.specialTileNames.add(this.NegativePoints); //3
        this.specialTileNames.add(this.ReverseOrder); //4
        this.specialTileNames.add(this.RemoveConsonants);//4
        this.spNum = this.specialTileNames.size();
    }

    /**
     * get number of specialTile.
     *
     * @return int.
     */
    public int getSpNum() {
        return this.spNum;
    }

    /**
     * check if the given name is in store.
     *
     * @param specialTileName name.
     * @return boolean.
     */
    public Boolean isInStore(String specialTileName) {
        Iterator nameList = this.specialTileNames.iterator();

        while (nameList.hasNext()) {
            SpecialTile specialTile = (SpecialTile) nameList.next();
            if (specialTileName.equals(specialTile.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * buy special tile.
     *
     * @param player          player want to buy.
     * @param specialTileName name.
     * @return special tile.
     */
    public SpecialTile buySpecialTile(Player player, String specialTileName) {
        if (player == null) {
            System.out.println("The input has null parameter!");
            return null;
        } else {
            Iterator nameList = this.specialTileNames.iterator();

            while (nameList.hasNext()) {
                SpecialTile specialTile = (SpecialTile) nameList.next();
                if (specialTileName.equals(specialTile.getName())) {
                    int price = specialTile.getPrice();
                    if (player.getScore() >= price) {
                        player.addScore(-1 * price);
                        specialTile.setOwner(player);
                        return specialTile;
                    }

                    return null;
                }
            }

            return null;
        }
    }
}
