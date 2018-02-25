package edu.cmu.cs.cs214.hw4.gui;

import edu.cmu.cs.cs214.hw4.core.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class GamePanel extends JPanel implements GameChangeListener {
    private Game game;
    private JButton[][] square;
    private volatile int screenX = 0;
    private volatile int screenY = 0;
    private volatile int myX = 0;
    private volatile int myY = 0;
    /**
     * board size: 15.
     */
    private final int boardLength;
    private final int playerNum;
    private final int specialTileNum;
    private static final Color GRAY = new Color(220, 220, 220);
    private static final int TRUNK_LENGTH = 45;
    private JButton[] specialTiles;
    /**
     * Letter (and score) of tiles
     */
    private JLabel[][] tileLabels;
    /**
     * name of square on board.
     */
    private JLabel[][] squareLabels;
    private JLabel[] playerNames;
    private JLabel[] playerScores;
    private JLabel[] playerInventories;
    private JLabel gameMsgLabel;
    /**
     * show the name of current player.
     */
    private JLabel currentPlayerLabel;
    /**
     * each location on the board is a grid. Here I create 16*16 grid matrices.
     */
    private JPanel[][] grids;
    private JPanel boardPanel;
    private JPanel playerInfoPanel;
    private JPanel playerCommandPanel;
    private JPanel gameMsgPanel;
    private Boolean gamePanelFlag;
    private JPanel eastPanel;
    private JPanel settingPanel;


    public GamePanel(Game game) {
        this.game = game;
        this.playerNum = game.getPlayerNum();
        this.boardLength = game.getGameBoard().getBoardSize();
        this.specialTileNum = game.getSpecialTileNum();

        /**
         * label of the square.
         */
        this.grids = new JPanel[this.boardLength + 1][this.boardLength + 1];
        this.tileLabels = new JLabel[this.boardLength][this.boardLength];
        this.squareLabels = new JLabel[this.boardLength][this.boardLength];
        this.playerNames = new JLabel[this.playerNum];
        this.playerScores = new JLabel[this.playerNum];
        this.playerInventories = new JLabel[this.playerNum];
        this.specialTiles = new JButton[this.specialTileNum];
        this.boardPanel = new JPanel();
        this.playerInfoPanel = new JPanel();
        this.playerCommandPanel = new JPanel();
        this.gameMsgPanel = new JPanel();
        this.gameMsgLabel = new JLabel("Welcome to fancy scribble 1.0!");
        this.currentPlayerLabel = new JLabel();
        this.gamePanelFlag = true;
        this.eastPanel = new JPanel();
        this.settingPanel = new JPanel();
        this.initial();
    }

    public Game getGame() {
        return this.game;
    }

    public void initial() {
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setBackground(GRAY);
        this.initialBoardPanel();
        this.initialPlayerInfoPanel();
        this.initialPlayerCommandPanel();
        this.initialGameMsgPanel();
        this.eastPanel.setLayout(new GridLayout(4, 1));
        this.eastPanel.add(this.playerInfoPanel);
        this.eastPanel.add(this.playerCommandPanel);
        this.eastPanel.add(this.gameMsgPanel);
        this.add(this.boardPanel, "Center");
        this.add(this.eastPanel, "East");
    }

    public void initialBoardPanel() {
        this.boardPanel.setLayout(new GridLayout(this.boardLength + 1, this.boardLength + 1, 5, 5));
        Location[][] locations = this.game.getGameBoard().getLocationList();

        for (int i = 0; i < this.boardLength + 1; i++) {
            for (int j = 0; j < this.boardLength + 1; j++) {
                this.grids[i][j] = new JPanel();
                this.grids[i][j].setLayout(new BorderLayout());
                this.grids[i][j].setPreferredSize(new Dimension(70, 35));
                this.grids[i][j].setOpaque(true);
                JLabel coordinate;
                if (i == 0 && j == 0) {
                    coordinate = new JLabel("X|Y");
                    coordinate.setFont(new Font("Serif", Font.PLAIN, 18));
                    this.grids[i][j].add(coordinate, "West");
                } else if ((i != 0) && (j != 0)) { //both i and j is not 0
                    Color color;
                    if (locations[i - 1][j - 1].getSquare() == null) {
                        color = new Color(10, 10, 10);
                    } else {
                        color = locations[i - 1][j - 1].getSquare().getColor();
                    }
                    this.squareLabels[i - 1][j - 1] = new JLabel("");
                    this.squareLabels[i - 1][j - 1].setFont(new Font("Serif", Font.PLAIN, 12));
                    this.squareLabels[i - 1][j - 1].setForeground(Color.red);
                    this.tileLabels[i - 1][j - 1] = new JLabel("");
                    this.tileLabels[i - 1][j - 1].setFont(new Font("Serif", Font.PLAIN, 14));
                    this.grids[i][j].setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, color));
                    this.grids[i][j].add(this.tileLabels[i - 1][j - 1], "Center");
                    this.grids[i][j].add(this.squareLabels[i - 1][j - 1], "East");
                } else {
                    coordinate = new JLabel(String.valueOf(Math.max(i, j) - 1));
                    coordinate.setFont(new Font("Serif", Font.PLAIN, 18));
                    this.grids[i][j].add(coordinate, "West");
                }

                this.boardPanel.add(this.grids[i][j]);
            }
        }

        this.repaint();
    }

    public void updateBoardPanel() {
        String currentName = this.game.getCurPlayer().getName();
        Location[][] locations = this.game.getGameBoard().getLocationList();

        for (int i = 1; i < this.boardLength + 1; i++) {
            for (int j = 1; j < this.boardLength + 1; j++) {
                String sign;
                StringBuilder str = new StringBuilder();
                if (locations[i - 1][j - 1].isOccupied()) {
                    sign = locations[i - 1][j - 1].getTile().getLetter();
                    int value = locations[i - 1][j - 1].getTile().getValue();
                    this.tileLabels[i - 1][j - 1].setText("" + sign + value);
                } else {
                    this.tileLabels[i - 1][j - 1].setText("");
                }
                if (locations[i - 1][j - 1].getSpecialTile().size() != 0) {
                    ArrayList<SpecialTile> spList = locations[i - 1][j - 1].getSpecialTile();
                    //System.out.println("name" + spList.get(0).getName());
                    for (SpecialTile sp : spList) {
                        if (sp.getOwner() != null) {
                            if (currentName.equals(sp.getOwner().getName())) {
                                str.append(sp.getName().substring(0, 1));
                                this.squareLabels[i - 1][j - 1].setText(" " + str);
                            }
                        }

                    }
                } else {
                    this.squareLabels[i - 1][j - 1].setText(" ");
                }
                Color color = locations[i - 1][j - 1].getSquare().getColor();
                this.grids[i][j].setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, color));
            }
        }
        this.repaint();
    }


    public void initialPlayerInfoPanel() {
        this.playerInfoPanel.setLayout(new GridLayout(this.playerNum, 1));
        this.playerInfoPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLUE));
        JPanel[] playerInfos = new JPanel[this.playerNum];
        ArrayList<Player> players = this.game.getPlayers();
        this.game.setCurPlayer(players.get(this.game.setFirstOrder()));

        for (int i = 0; i < this.playerNum; i++) {
            Boolean isCurrent = (players.get(i)).getName().equals(this.game.getCurPlayer().getName());
            String inventory = (players.get(i)).showInventory();
            playerInfos[i] = new JPanel(new GridLayout(3, 1));
            playerInfos[i].setPreferredSize(new Dimension(300, 100));
            playerInfos[i].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
            this.playerNames[i] = new JLabel("Player: " + ((Player) players.get(i)).getName());
            this.playerScores[i] = new JLabel("Score: " + ((Player) players.get(i)).getScore());
            this.playerInventories[i] = new JLabel("Inventory: " + inventory);
            if (!isCurrent) {
                this.playerInventories[i] = new JLabel("Inventory: ***********************");
            }

            playerInfos[i].add(this.playerNames[i]);
            playerInfos[i].add(this.playerScores[i]);
            playerInfos[i].add(this.playerInventories[i]);
            this.playerInfoPanel.add(playerInfos[i]);
        }

        this.repaint();
    }

    public void updatePlayerInfoPanel() {
        this.playerInfoPanel.removeAll();
        this.playerInfoPanel.setLayout(new GridLayout(this.playerNum, 1));
        this.playerInfoPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLUE));
        JPanel[] playerInfos = new JPanel[this.playerNum];
        ArrayList<Player> players = this.game.getPlayers();
        for (int i = 0; i < this.playerNum; i++) {
            Boolean isCurrent = ((Player) players.get(i)).getName().equals(this.game.getCurPlayer().getName());
            String inventory = ((Player) players.get(i)).showInventory();
            int moreNum = 0;
            if (inventory.length() > 45) {
                moreNum = (inventory.length() - 45) / 45 + 1;
            }

            ArrayList<JLabel> moreLabels = new ArrayList<>();
            int k;
            if (moreNum > 0) {
                for (k = 0; k < moreNum; ++k) {
                    if (k == moreNum - 1) {
                        moreLabels.add(new JLabel("           " + inventory.substring((k + 1) * 45)));
                    } else {
                        moreLabels.add(new JLabel("           " + inventory.substring((k + 1) * 45, (k + 2) * 45)));
                    }
                }
            }

            playerInfos[i] = new JPanel(new GridLayout(3 + moreNum, 1));
            playerInfos[i].setPreferredSize(new Dimension(300, 100));
            playerInfos[i].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
            this.playerNames[i] = new JLabel("Player: " + ((Player) players.get(i)).getName());
            this.playerScores[i] = new JLabel("Score: " + ((Player) players.get(i)).getScore());
            if (moreNum == 0) {
                this.playerInventories[i] = new JLabel("Inventory: " + inventory);
            } else {
                this.playerInventories[i] = new JLabel("Inventory: " + inventory.substring(0, 45));
            }

            if (!isCurrent) {
                this.playerInventories[i] = new JLabel("Inventory: *************************");
            }

            playerInfos[i].add(this.playerNames[i]);
            playerInfos[i].add(this.playerScores[i]);
            playerInfos[i].add(this.playerInventories[i]);

            /*for (k = 0; k < moreNum; k++) {
                if (!isCurrent) {
                    playerInfos[i].add(new JLabel("************************"));
                } else {
                    playerInfos[i].add((Component) moreLabels.get(k));
                }
            }*/

            this.playerInfoPanel.add(playerInfos[i]);
        }

        this.repaint();
    }

    public void initialPlayerCommandPanel() {
        this.playerCommandPanel.setLayout(new GridLayout(6, 1));
        this.playerCommandPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.DARK_GRAY));
        this.currentPlayerLabel.setText("Current player: " + this.game.getCurPlayer().getName() + " with score: " + this.game.getCurPlayer().getScore());
        this.playerCommandPanel.add(this.currentPlayerLabel);
        JPanel commandPanel = new JPanel();
        commandPanel.setLayout(new FlowLayout());
        JLabel commandInstructLabel = new JLabel("Please make your action:");
        JButton placeButton = new JButton("Place");

        placeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg) {
                if (GamePanel.this.gamePanelFlag) {
                    GamePanel.this.gameMsgLabel.setText("You have pressed the Place button!");
                    GamePanel.this.triggerPlaceSettingPanel();
                    GamePanel.this.gamePanelFlag = false;
                }
            }
        });
        JButton exchangeButton = new JButton("Exchange");
        // ActionListener exchangeListener = new 2 (this);
        exchangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (GamePanel.this.gamePanelFlag) {
                    GamePanel.this.gameMsgLabel.setText("You have pressed the Exchange button!");
                    GamePanel.this.popupExchangeWindow();
                    GamePanel.this.gamePanelFlag = false;
                }
            }
        });
        JButton challengeButton = new JButton("Challenge");
        //ActionListener passListener = new 3 (this);
        challengeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (GamePanel.this.gamePanelFlag) {
                    if (!game.getGameBoard().getLocation(7, 7).isOccupied()) {
                        GamePanel.this.gameMsgLabel.setText("You are the first player!!");
                        return;
                    }
                    game.challenge();
                    Player player;
                    GamePanel.this.gameMsgLabel.setText(game.getCurPlayer().getName() + " has challenged successfully!" + game.getPrePlayer().getName() + " lose next turn !");
                    setTrue();
                    //game.updateOrder();
                    updateAll();
                    setGameMsgLabel("Player: " + game.getCurPlayer().getName() + " can have your move now!");
                    GamePanel.this.gamePanelFlag = false;
                }
            }
        });
        JButton quitButton = new JButton("Quit");
        //ActionListener quitListener = new 4 (this);
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (GamePanel.this.gamePanelFlag) {
                    ArrayList<Player> winners = game.getWinner();
                    String winnerString = "The winner(s) is/are:";

                    Player player;
                    for (Iterator var5 = winners.iterator(); var5.hasNext(); winnerString = winnerString + " " + player.getName()) {
                        player = (Player) var5.next();
                    }
                    GamePanel.this.gameMsgLabel.setText(winnerString);
                    GamePanel.this.gamePanelFlag = true;
                }
            }
        });
        commandPanel.add(placeButton);
        commandPanel.add(exchangeButton);
        commandPanel.add(challengeButton);
        commandPanel.add(quitButton);
        JPanel specialTileStorePanel = new JPanel();
        specialTileStorePanel.setLayout(new FlowLayout());
        JLabel specialTileInstructLabel = new JLabel("Want special tile first?");
        this.specialTiles[0] = new JButton("Boom");
        //ActionListener boomListener = new 5 (this);
        this.specialTiles[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (GamePanel.this.gamePanelFlag) {
                    SpecialTile specialTile = game.buySpecialTile("Boom");//30
                    if (specialTile == null) {
                        gameMsgLabel.setText("You don't have enough money to buy special tile Boom!");
                    } else {
                        game.getSpecialTileStore().getSpecialTile("Boom").setOwner(game.getCurPlayer());
                        //System.out.println("name2" + game.getCurPlayer().getName());
                        //System.out.println("name3" + game.getSpecialTileStore().getSpecialTile("Boom").getOwner().getName());
                        gameMsgLabel.setText(game.getCurPlayer().getName() + "'s score - " + specialTile.getPrice());
                        //game.getCurPlayer().subScore(specialTile.getPrice());
                        popupSpecialTileWindow(specialTile);
                    }
                }
            }
        });
        this.specialTiles[1] = new JButton("NegativePoints");//30
        //ActionListener negativePointsListener = new 6 (this);
        this.specialTiles[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (GamePanel.this.gamePanelFlag) {
                    SpecialTile specialTile = game.buySpecialTile("NegativePoints");
                    if (specialTile == null) {
                        gameMsgLabel.setText("You don't have enough money to buy special tile NegativePoints!");
                    } else {
                        game.getSpecialTileStore().getSpecialTile("Boom").setOwner(game.getCurPlayer());
                        gameMsgLabel.setText(game.getCurPlayer().getName() + "'s score - " + specialTile.getPrice());
                        game.getCurPlayer().subScore(specialTile.getPrice());
                        popupSpecialTileWindow(specialTile);
                    }
                }
            }
        });
        this.specialTiles[2] = new JButton("RemoveConsonants");//30
        //ActionListener oneMoreOrderListener = new 7 (this);
        this.specialTiles[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (GamePanel.this.gamePanelFlag) {
                    SpecialTile specialTile = game.buySpecialTile("RemoveConsonants");
                    if (specialTile == null) {
                        gameMsgLabel.setText("You don't have enough money to buy special tile RemoveConsonants!");
                    } else {
                        game.getSpecialTileStore().getSpecialTile("Boom").setOwner(game.getCurPlayer());
                        gameMsgLabel.setText(game.getCurPlayer().getName() + "'s score - " + specialTile.getPrice());
                        game.getCurPlayer().subScore(specialTile.getPrice());
                        popupSpecialTileWindow(specialTile);
                    }
                }
            }
        });
        this.specialTiles[3] = new JButton("ReversePlayerOrder");//40
        //ActionListener reversePlayerOrderListener = new 8 (this);
        this.specialTiles[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (GamePanel.this.gamePanelFlag) {
                    SpecialTile specialTile = game.buySpecialTile("ReversePlayerOrder");
                    if (specialTile == null) {
                        gameMsgLabel.setText("You don't have enough money to buy special tile ReversePlayerOrder!");
                    } else {
                        game.getSpecialTileStore().getSpecialTile("Boom").setOwner(game.getCurPlayer());
                        gameMsgLabel.setText(game.getCurPlayer().getName() + "'s score - " + specialTile.getPrice());
                        game.getCurPlayer().subScore(specialTile.getPrice());
                        popupSpecialTileWindow(specialTile);
                    }
                }
            }
        });
        this.specialTiles[4] = new JButton("AdditionMove");//30
        //ActionListener stealMoveListener = new 9 (this);
        this.specialTiles[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (GamePanel.this.gamePanelFlag) {
                    SpecialTile specialTile = game.buySpecialTile("AdditionOrder");
                    if (specialTile == null) {
                        gameMsgLabel.setText("You don't have enough money to buy special tile StealMove!");
                    } else {
                        game.getSpecialTileStore().getSpecialTile("Boom").setOwner(game.getCurPlayer());
                        gameMsgLabel.setText(game.getCurPlayer().getName() + "'s score - " + specialTile.getPrice());
                        game.getCurPlayer().subScore(specialTile.getPrice());
                        popupSpecialTileWindow(specialTile);
                    }
                }
            }
        });

        for (int j = 0; j < this.specialTileNum - 2; ++j) {
            specialTileStorePanel.add(this.specialTiles[j]);
            specialTileStorePanel.add(new JLabel("[$ 30]"));
        }

        JPanel specialTileStorePanel_1 = new JPanel();
        specialTileStorePanel_1.setLayout(new FlowLayout());
        specialTileStorePanel_1.add(this.specialTiles[3]);
        specialTileStorePanel_1.add(new JLabel("[$ 40]"));
        specialTileStorePanel_1.add(this.specialTiles[4]);
        specialTileStorePanel_1.add(new JLabel("[$ 30]"));
        this.playerCommandPanel.add(specialTileInstructLabel);
        this.playerCommandPanel.add(specialTileStorePanel);
        this.playerCommandPanel.add(specialTileStorePanel_1);
        this.playerCommandPanel.add(commandInstructLabel);
        this.playerCommandPanel.add(commandPanel);
        this.repaint();
    }

    public void updatePlayerCommandPanel() {
        this.currentPlayerLabel.setText("Current player: " + this.game.getCurPlayer().getName() + " with score: " + this.game.getCurPlayer().getScore());
        this.gamePanelFlag = true;
        this.repaint();
    }

    public void updateAll() {
        this.updateBoardPanel();
        this.updatePlayerInfoPanel();
        this.updatePlayerCommandPanel();
    }

    public void initialGameMsgPanel() {
        this.gameMsgPanel.setLayout(new BorderLayout());
        JPanel helpPanel = new JPanel();
        helpPanel.setLayout(new FlowLayout());
        JPanel twols = new JPanel();
        twols.setPreferredSize(new Dimension(70, 35));
        twols.setOpaque(true);
        twols.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(151, 255, 255)));
        helpPanel.add(twols);
        helpPanel.add(new JLabel(": 2LS  "));
        JPanel threels = new JPanel();
        threels.setPreferredSize(new Dimension(70, 35));
        threels.setOpaque(true);
        threels.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(127, 255, 212)));
        helpPanel.add(threels);
        helpPanel.add(new JLabel(": 3LS  "));
        JPanel twows = new JPanel();
        twows.setPreferredSize(new Dimension(70, 35));
        twows.setOpaque(true);
        twows.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(255, 174, 185)));
        helpPanel.add(twows);
        helpPanel.add(new JLabel(": 2WS  "));
        JPanel threews = new JPanel();
        threews.setPreferredSize(new Dimension(70, 35));
        threews.setOpaque(true);
        threews.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(255, 165, 0)));
        helpPanel.add(threews);
        helpPanel.add(new JLabel(": 3WS"));
        this.gameMsgPanel.add(helpPanel, "North");
        this.gameMsgPanel.add(this.gameMsgLabel, "Center");
        this.gameMsgPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED));
        this.repaint();
    }

    public void setTrue() {
        this.gamePanelFlag = true;
    }

    public void setGameMsgLabel(String string) {
        this.gameMsgLabel.setText(string);
    }

    public void removeSettingPanel() {
        this.eastPanel.remove(this.settingPanel);
    }

    public void triggerPlaceSettingPanel() {
        this.settingPanel = new JPanel();
        this.settingPanel.setLayout(new GridLayout(10, 1));
        this.settingPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        JLabel settingInstruct = new JLabel("Define the start location and direction of your placement:");
        JLabel startLocationInstruct = new JLabel("start location: ");
        JLabel directionInstruct = new JLabel("direction of placement: '1' means horizontal while '0' means vertical.");
        JLabel xInstruct = new JLabel("x coordinate: ");
        JLabel yInstruct = new JLabel("y coordinate: ");
        final JTextField xInput = new JTextField(5);
        final JTextField yInput = new JTextField(5);
        final JTextField direction = new JTextField(5);
        JButton submitButton = new JButton("submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg) {
                String xResult = xInput.getText().trim();
                String yResult = yInput.getText().trim();
                String dResult = direction.getText().trim();
                if (!game.getGameBoard().getLocation(7, 7).isOccupied()) {
                    if (!xResult.equals("7") || !yResult.equals("7")) {
                        GamePanel.this.gameMsgLabel.setText("The start location should be on the star!");
                        return;
                    }
                }
                if (!xResult.equals("") && !yResult.equals("") && !dResult.equals("")) {
                    int xx;
                    int y;
                    int d;
                    try {
                        xx = Integer.parseInt(xResult);
                        y = Integer.parseInt(yResult);
                        d = Integer.parseInt(dResult);
                    } catch (NumberFormatException var9) {
                        GamePanel.this.gameMsgLabel.setText("There is invalid character in your input!");
                        xInput.setText("");
                        yInput.setText("");
                        direction.setText("");
                        return;
                    }

                    if (!GamePanel.this.game.getGameBoard().isOnBoard(xx, y) || (d != 1 && d != 0)) {
                        GamePanel.this.gameMsgLabel.setText("Your input coordinate is out of board or invalid direction!");
                        xInput.setText("");
                        yInput.setText("");
                        direction.setText("");
                    } else {
                        GamePanel.this.gamePanelFlag = false;
                        int length;
                        if (d == 1) {
                            length = GamePanel.this.boardLength - y;
                        } else {
                            length = GamePanel.this.boardLength - xx;
                        }
                        GamePanel.this.setPlacementInstruction(xx, y, d, length);
                        GamePanel.this.popupPlaceWindow(xx, y, d);
                    }
                } else {
                    GamePanel.this.gameMsgLabel.setText("Input cannot be empty!");
                    xInput.setText("");
                    yInput.setText("");
                    direction.setText("");
                }
            }
        });
        JButton cancelButton = new JButton("cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg) {
                GamePanel.this.gameMsgLabel.setText("Player: " + GamePanel.this.game.getCurPlayer().getName() + " has cancelled his/her placement.");
                GamePanel.this.eastPanel.remove(GamePanel.this.settingPanel);
                GamePanel.this.gamePanelFlag = true;
                GamePanel.this.repaint();
            }
        });
        this.settingPanel.add(settingInstruct);
        this.settingPanel.add(startLocationInstruct);
        this.settingPanel.add(xInstruct);
        this.settingPanel.add(xInput);
        this.settingPanel.add(yInstruct);
        this.settingPanel.add(yInput);
        this.settingPanel.add(directionInstruct);
        this.settingPanel.add(direction);
        this.settingPanel.add(submitButton);
        this.settingPanel.add(cancelButton);
        this.eastPanel.add(this.settingPanel);
    }

    public void popupSpecialTileWindow(final SpecialTile specialTile) {
        String specialTileWindowName = "SpecialTile Coordinate Window For Fancy Scribble 1.0";
        JFrame frame = new JFrame("SpecialTile Coordinate Window For Fancy Scribble 1.0");
        SpecialTilePanel specialTilePanel = new SpecialTilePanel(specialTile, this, this.game, frame);
        specialTilePanel.setOpaque(true);
        frame.add(specialTilePanel);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setResizable(true);
        frame.pack();
        frame.setVisible(true);

    }

    public void popupExchangeWindow() {
        String exchangeWindowName = "Tile Exchange Window For Bug Scribble 1.0";
        JFrame frame = new JFrame("Tile Exchange Window For Bug Scribble 1.0");
        ExchangePanel exchangePanel = new ExchangePanel(this, this.game, frame);
        exchangePanel.setOpaque(true);
        frame.add(exchangePanel);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setResizable(true);
        frame.pack();
        frame.setVisible(true);
    }

    public void setPlacementInstruction(int startX, int startY, int dir, int length) {
        int deltaX = 0;
        int deltaY = 0;
        if (dir == 1) {
            deltaY = 1;
        } else {
            deltaX = 1;
        }
        for (int i = 0; i < length; i++) {
            int currX = startX + i * deltaX;
            int currY = startY + i * deltaY;
            this.grids[currX + 1][currY + 1].setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.RED));
        }
    }

    public void popupPlaceWindow(int x, int y, int direction) {
        String placeWindowName = "Tile Place Window For Bug Scribble 1.0";
        new GamePanelHelper(this, x, y, direction); //create a new windows for place panel.
    }

    /**
     * Called when the current player changed
     *
     * @param player The new current player.
     */

    public void currentPlayerChanged(Player player) {
        currentPlayerLabel.setText("current player " + player.toString());

    }


    /**
     * Called when the game ends, announcing the winner (or null on a tie).
     *
     * @param winner The winner of the game, or null on a tie.
     */

    public void gameEnded(Player winner) {

    }
}
