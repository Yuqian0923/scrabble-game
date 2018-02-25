package edu.cmu.cs.cs214.hw4.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class Game {
    /**
     * get from GUI input.
     */
    private Player curPlayer;
    /**
     * get from GUI input.
     */
    private int order;
    /**
     * get from GUI input.
     */
    private ArrayList<Player> players;
    /**
     * get from GUI input.
     */
    private ArrayList<Location> curTiles;
    /**
     * get from GUI input.
     */
    private int playerNum;
    /**
     * game board.
     */
    private Board gameBoard;
    /**
     * buy special tile from store.
     */
    private SpecialTileStore specialTileStore;
    /**
     * the dictionary of game.
     */
    private Dictionary dict;
    /**
     * send out tiles.
     */
    private TilePackage tilePackage;

    private Boolean negativePointsFlag;
    /**
     * mark the location of boom.
     */
    private Boolean boomFlag = false;
    private Boolean stealFlag;
    private Player benefier;
    private ArrayList<Location> boomLocations = new ArrayList<>();
    /**
     * range of boom.
     */
    private int boomRange;

    /**
     * Game constructor.
     *
     * @param num        player number.
     * @param playerList playerList.
     */
    public Game(int num, ArrayList<Player> playerList) {
        playerNum = num;
        players = playerList;
        dict = new Dictionary();
        dict.check("src/main/resources/words.txt");
        tilePackage = new TilePackage();
        specialTileStore = new SpecialTileStore();
        //initGame(num);
    }

    public SpecialTileStore getSpecialTileStore() {
        return specialTileStore;
    }

    /**
     * add new player.
     *
     * @param player new player.
     */
    public void addPlayer(Player player) {
        if (playerNum == 4) {
            System.out.println("The number of players has reached maximum!");
        } else if (player == null) {
            System.out.println("The input player is null!");
        } else if (isIn(player.getName())) {
            System.out.println("The player has already in the player list!");
        } else {
            players.add(player);
            playerNum++;
        }
    }

    /**
     * get function.
     *
     * @return player number.
     */
    public int getPlayerNum() {
        return this.playerNum;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    public int getSpecialTileNum() {
        return this.specialTileStore.getSpNum();
    }

    public Player getCurPlayer() {
        return curPlayer;
    }

    /**
     * check if the input player is in game.
     *
     * @param playerName player names.
     * @return boolean.
     */
    private Boolean isIn(String playerName) {
        Iterator pList = players.iterator();

        while (pList.hasNext()) {
            Player player = (Player) pList.next();
            if (player.getName().equals(playerName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * initializes player boards, game board and tile
     */
    public void initGame() {
        //init board
        gameBoard = new Board();
        tilePackage.initial();
        // send out tiles
        /*for (Player player:players){

         sendOutTiles(tilePackage.getTiles(7), player);
        }*/

    }

    public TilePackage getTilePackage() {
        return tilePackage;
    }


    /**
     * get the current turn.
     *
     * @return order
     */
    public int getOrder() {
        return order;
    }

    /**
     * get the pre player.
     *
     * @return order
     */
    public Player getPrePlayer() {
        Player prePlayer;
        if (order - 1 > 1) {
            prePlayer = players.get(curPlayer.getId() - 1);
        } else {
            prePlayer = players.get(1);
        }
        return prePlayer;
    }

    /**
     * send out tiles to each player.
     *
     * @param tiles  tile list.
     * @param player every player in the game.
     */
    public void sendOutTiles(ArrayList<Tile> tiles, Player player) {
        player.refillTile(tiles);
    }

    /**
     * return the given player's tile list.
     *
     * @param player current player 2 to 4
     * @return List a list of Tile of the current player
     */
    public Tile getPlayerTile(Player player, String letter, int value) {

        if (players.contains(player)) {
            return player.getTile(letter, value);
        } else {
            System.out.println("No such player in the game!");
            return null;
        }
    }

    public int getPrice(SpecialTile specialTile) {
        return specialTile.getPrice();
    }

    public void setCurTiles(ArrayList<Location> curTiles) {
        this.curTiles = curTiles;
    }


    /**
     * When give start give a order.
     *
     * @return int order.
     */
    public int setFirstOrder() {
        int index = 0;
        char curClosest = 'Z';
        for (Player p : players) {
            ArrayList<Tile> curTiles = p.getTileList();
            for (Tile curTile : curTiles) {
                char curchar = curTile.getLetter().charAt(0);
                if (((int) curchar) < (int) curClosest) {
                    index = p.getId();
                    curClosest = curchar;
                }
            }
        }
        this.order = index;
        this.curPlayer = players.get(index);
        return index;
    }

    public void setCurPlayer(Player player) {
        this.curPlayer = player;
    }

    /**
     * call each turn.
     */
    public void updateOrder() {
        if (order + 1 < playerNum) {
            order = curPlayer.getId() + 1;
        } else {
            order = (curPlayer.getId() + 1) % playerNum;
        }
        curPlayer = players.get(order);
    }

    /**
     * when user hits CHALLENGE button.
     */
    public void challenge() {
        int preOrder;
        Location[] locations = new Location[curTiles.size()];
        for (int i = 0; i < curTiles.size(); i++) {
            locations[i] = curTiles.get(i);
        }
        ArrayList<Word> wordList = gameBoard.storeWord(locations);
        for (Word word : wordList) {
            if (!dict.isInDictionary(word)) {
                for (Location loc : locations) {
                    getGameBoard().removeTileFromLocation(loc.getX(), loc.getY());
                }
                if (order - 1 < 0) {
                    preOrder = playerNum - 1;
                } else {
                    preOrder = order - 1;
                }
                Player prePlayer = players.get(preOrder);
                prePlayer.subScore(word.getScore());
            } else {
                updateOrder();
            }
        }
    }

    public void skipOrder() {
        this.updateOrder();
    }

    /**
     * @param tile User choose tile.
     * @param x    index of board.
     * @param y    index of board.
     */
    public void pickFirstLocation(Tile tile, int x, int y) {
        if (curTiles.size() != 0) {
            for (Location loc : curTiles) {
                if (loc.getX() == x && loc.getY() == y) {
                    System.out.println("You can not place more than one tiles on one location!");
                    return;
                }
            }
        }
        Location temp = new Location(x, y);
        temp.addTile(tile);
        curTiles.add(temp);
    }

    /**
     * execute when firstMove is true and user hit SUBMIT button.
     */
    public void placeFirstLocation() {
        Location[] locationList = new Location[curTiles.size()];
        System.out.format("Player %d, Place Your Tiles", curPlayer.getId());
        if (!gameBoard.isValidTile(locationList) || !gameBoard.isOnStar(locationList)) {
            System.out.println("Invalid Location, Try again");
            return;
        }
        for (Location curTile : curTiles) {
            gameBoard.getLocation(curTile.getX(), curTile.getY()).addTile(curTile.getTile());
        }
        Word curWord = gameBoard.getFirstWord(locationList);
        curPlayer.addScore(curWord.getScore());
    }

    public ArrayList<Location> getCurTiles() {
        return curTiles;
    }



    /**
     * modify tiles before submit.
     *
     * @param x index of board.
     * @param y index of board.
     */
    public void removeOneTile(int x, int y) {
        for (Location loc : curTiles) {
            if (loc.getX() == x && loc.getY() == y) {
                curTiles.remove(loc);
                return;
            }
        }
        System.out.println("No such tile.");
    }

    /**
     * execute when the user hit the SUBMIT button/ add event listener.
     */
    public int execute() {
        //take user input{curTiles};
        Location[] locationList = new Location[curTiles.size()];
        ArrayList<Word> wordList;
        for (int i = 0; i < curTiles.size(); i++) {
            locationList[i] = curTiles.get(i);
        }

        if (!gameBoard.isValidTile(locationList) || !gameBoard.isValidLocation(locationList)) {
            System.out.println("Can't execute Because you are not placing tiles in a valid way");
            return -1;
        }
        for (Location curTile : curTiles) {
            gameBoard.getLocation(curTile.getX(), curTile.getY()).addTile(curTile.getTile());
            //Trigger special effects here.
            ArrayList<SpecialTile> curSpecialTiles = gameBoard.getLocation(curTile.getX(), curTile.getY()).getSpecialTile();
            if (curSpecialTiles.size() > 0) {
                for (SpecialTile thisSpecialTile : curSpecialTiles) {
                    thisSpecialTile.makeSpecialEffects(this, curTile);
                }
            }
        }

        wordList = gameBoard.storeWord(locationList);

        int scoreThisTurn = 0;
        for (Word curWord : wordList) {
            scoreThisTurn = scoreThisTurn + curWord.getScore();
            curPlayer.addScore(scoreThisTurn);
        }
        // check if trigger any special tiles
        return 0;
    }

    /**
     * when user hits EXCHANGE button.
     *
     * @param tiles tiles
     */

    public void exchangeTiles(ArrayList<Tile> tiles) {
        if (tiles != null && curPlayer != null) {
            int size = tiles.size();
            curPlayer.removeTiles(tiles);
            Iterator tileList = tiles.iterator();

            while (tileList.hasNext()) {
                Tile tile = (Tile) tileList.next();
                tilePackage.addTile(tile);
            }
            ArrayList<Tile> tiles2 = tilePackage.getTiles(size);
            curPlayer.refillTile(tiles2);
        } else {
            System.out.println("The input has null!");
        }
    }


    /**
     * remove tile from location.
     *
     * @param x x coordinate.
     * @param y y coordinate.
     */
    public void removeTileFromLocation(int x, int y) {
        if (!gameBoard.isOnBoard(x, y)) {
            System.out.println("The location is not in the board!");
        } else {
            gameBoard.removeTileFromLocation(x, y);
        }
    }

    /**
     * @param specialTileName give a name.
     * @return specialTile.
     */
    public SpecialTile buySpecialTile(String specialTileName) {
        return this.specialTileStore.buySpecialTile(curPlayer, specialTileName);
    }

    /**
     * set specialTile.
     *
     * @param specialTile give a name.
     * @param x           index of board.
     * @param y           index of board.
     */
    public void placeSpecialTiles(SpecialTile specialTile, int x, int y) {
        if (!gameBoard.isOnBoard(x, y)) {
            System.out.println("The location is not in the board!");
        } else {
            Location location = new Location(x, y);
            location.setSpecialTile(specialTile);
        }
    }

    /**
     * set negative point.
     */
    public void setNegativePoints() {
        this.negativePointsFlag = true;
    }

    /**
     * unset negative point.
     */

    public void unSetNegativePoints() {
        this.negativePointsFlag = false;
    }

    /**
     * set boom.
     *
     * @param location location.
     * @param range    radius.
     */

    public void setBoom(Location location, int range) {
        if (location == null) {
            System.out.println("The location is null!");
        } else {
            if (range < 0) {
                range *= -1;
            }
            if (range == 0) {
                System.out.println("Explosion range is 0!");
                return;
            }
            this.boomFlag = true;
            //this.boomLocations.add(location);
            this.boomRange = range;
            int curX = location.getX();
            int curY = location.getY();
            for(int deltaX = - range; deltaX <range; deltaX++){
                for(int deltaY = -range + 1; deltaY < range - 1; deltaY++) {
                    if (deltaX + deltaY > - range && deltaX + deltaY < range) {
                        if(gameBoard.isOnBoard(curX + deltaX, curY + deltaY) &&
                                gameBoard.isOccupied(curX + deltaX, curY + deltaY)) {
                            gameBoard.getLocation(curX + deltaX, curY + deltaY).removeTile();
                        }
                    }
                }
            }


        }
    }

    /**
     * set which locations are gonna boom.
     *
     * @param boomPoints boom location.
     */
    public void showBoomPoints(Set<Location> boomPoints) {
        if (boomPoints.size() > 0) {
            Iterator point = boomPoints.iterator();

            while (point.hasNext()) {
                Location removeLoc = (Location) point.next();
                System.out.println("boom location: x: " + removeLoc.getX() + " y: " + removeLoc.getY());
            }
        }
    }

    /**
     * unset boom.
     */
    public void unSetBoom() {
        this.boomFlag = false;
        this.boomLocations = new ArrayList<>();
        this.boomRange = -1;
    }

    /**
     * reverse order.
     */
    public void reverseOrder() {
        if (order - 1 < 0) {
            order = playerNum - 1;
        } else {
            order = curPlayer.getId() - 1;
        }
        curPlayer = players.get(order);
    }


    /**
     * function under construction....
     *
     * @param player owner player.
     */
    public void addScoreToPlayer(Player player) {
        if (player == null) {
            System.out.println("The player is null!");
        } else {
            int timer = 1;
            if (this.negativePointsFlag) {
                timer = -1;
            }
            int score = curPlayer.getScore() * timer;
            this.unSetNegativePoints();
            player.addScore(score);
        }
    }

    /**
     * when the specialtile has been trigger, all the consonants on board will be removed.
     */
    public void removeConsonants() {
        String[] consonants = new String[]{"b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "v", "w", "x", "y", "z"};
        Location[][] boardList = getGameBoard().getLocationList();
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (boardList[i][j].isOccupied()) {
                    for (int k = 0; k < consonants.length; k++) {
                        if (boardList[i][j].getTile().getLetter().equals(consonants[k])) {
                            boardList[i][j].removeTile();
                        }
                    }
                }
            }
        }
    }

    /**
     * When this special tile has been triggered, the owner of it will get the curpalyer's turn.
     *
     * @param player owner of addition order.
     */
    public void setAdditionOrder(Player player) {
        curPlayer = player;
    }

    /**
     * get the winner of a game.
     *
     * @return ranked player list.
     */
    public ArrayList<Player> getWinner() {
        ArrayList<Player> winners = new ArrayList<>();
        int maxScore = -2147483648;//the minimum number of int
        Iterator playerList = this.players.iterator();

        Player player;
        while (playerList.hasNext()) {
            player = (Player) playerList.next();
            if (player.getScore() > maxScore) {
                maxScore = player.getScore();
            }
        }
        playerList = this.players.iterator();
        while (playerList.hasNext()) {
            player = (Player) playerList.next();
            if (player.getScore() == maxScore) {
                winners.add(player);
            }
        }
        return winners;
    }

    /**
     * get the end of a game.
     */
    public void endGame() {
        ArrayList<Player> winners = this.getWinner();
        System.out.println("The winner is/are:");
        Iterator var = winners.iterator();

        while (var.hasNext()) {
            Player player = (Player) var.next();
            System.out.println(" " + player.getName());
        }

    }


}
