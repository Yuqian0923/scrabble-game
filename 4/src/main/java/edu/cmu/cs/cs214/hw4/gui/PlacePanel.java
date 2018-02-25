/**
 * author:Yuqian Li
 */
package edu.cmu.cs.cs214.hw4.gui;

import edu.cmu.cs.cs214.hw4.core.Game;
import edu.cmu.cs.cs214.hw4.core.Location;
import edu.cmu.cs.cs214.hw4.core.Tile;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PlacePanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private final JFrame frame;
    private final GamePanel gamePanel;
    private final Game game;
    private final int startX;
    private final int startY;
    private final int dir;
    private final JLabel placeInstruct;
    private final JButton submitButton;
    private final JButton cancelButton;
    private final JPanel workPanel;
    private final JLabel placeMsg;
    private final int boardLength;
    private ArrayList<JTextField> allTexts;
    private ArrayList<Location> allLocations;

    public PlacePanel(GamePanel gamePanel, Game game, JFrame frame, int startX, int startY, int dir) {
        this.frame = frame;
        this.gamePanel = gamePanel;
        this.game = gamePanel.getGame();
        this.startX = startX;
        this.startY = startY;
        this.dir = dir;
        this.placeInstruct = new JLabel("Please use your tiles in inventory to fill, the board piece is according to your start location and direction of placement");
        this.workPanel = new JPanel();
        this.submitButton = new JButton("place your tiles");
        this.cancelButton = new JButton("cancel");
        this.placeMsg = new JLabel("");
        this.boardLength = 15;
        this.allTexts = new ArrayList<>();
        this.allLocations = new ArrayList<>();
        this.initial();
    }

    public void initial() {
        this.setLayout(new GridLayout(5, 1));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.DARK_GRAY));
        this.initialWorkPanel();
        this.initialSubmitButton();
        this.initialCancelButton();
        this.add(this.placeInstruct);
        this.add(this.workPanel);
        this.add(this.submitButton);
        this.add(this.cancelButton);
        this.add(this.placeMsg);
    }

    public void initialWorkPanel() {
        clearText();
        Location[][] locations = this.game.getGameBoard().getLocationList();
        int length;
        if (this.dir == 1) {
            length = this.boardLength - this.startY;
        } else {
            length = this.boardLength - this.startX;
        }

        this.workPanel.setLayout(new FlowLayout());
        int deltaX = 0;
        int deltaY = 0;
        if (this.dir == 1) {
            deltaY = 1;
        } else {
            deltaX = 1;
        }

        for (int i = 0; i < length; i++) {
            int currX = this.startX + i * deltaX;
            int currY = this.startY + i * deltaY;
            if (locations[currX][currY].isOccupied()) {
                JLabel newLabel = new JLabel();
                newLabel.setText(String.valueOf(locations[currX][currY].getTile().getLetter()));
                newLabel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, locations[currX][currY].getSquare().getColor()));
                this.workPanel.add(newLabel);
            } else {
                JTextField newText = new JTextField(5);
                newText.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, locations[currX][currY].getSquare().getColor()));
                this.workPanel.add(newText);
                this.allTexts.add(newText); //square
                this.allLocations.add(this.game.getGameBoard().getLocation(currX, currY));
            }
        }

    }

    public void clearText() {
        Iterator var2 = this.allTexts.iterator();

        while (var2.hasNext()) {
            JTextField text = (JTextField) var2.next();
            text.setText("");
        }

    }

    public void initialCancelButton() {
        //ActionListener cancelListener = new 1(this);
        this.cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.setTrue();
                gamePanel.setGameMsgLabel("Player: " + game.getCurPlayer().getName() + " has cancelled his/her placement");
                gamePanel.removeSettingPanel();
                gamePanel.updateAll();
            }
        });
    }

    public void initialSubmitButton() {
        //ActionListener submitListener = new 2(this);
        this.submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ArrayList<Tile> tempTiles = new ArrayList<>();
                //Those locations are locations belong to game board.
                ArrayList<Location> tempLocations = new ArrayList<>();
                //Those locations are new locations which are not belong to game board.
                ArrayList<Location> tempLocations2 = new ArrayList<>();
                int endIndex = 0;

                int signal;
                for (signal = 0; signal < allLocations.size(); signal++) {
                    if ((allTexts.get(signal)).getText().trim().equals("")) {
                        endIndex = signal;
                        break;
                    }
                }

                if (endIndex == 0) {
                    placeMsg.setText("You should at least fill in the start location!");
                    clearText();
                } else {
                    for (signal = endIndex + 1; signal < allLocations.size(); signal++) {
                        if (!allTexts.get(signal).getText().trim().equals("")) {
                            placeMsg.setText("Your inputs are not continues!");
                            clearText();
                            return;
                        }
                    }

                    for (signal = 0; signal < endIndex; signal++) {
                        String input = (allTexts.get(signal)).getText().trim();
                        if (input.length() > 1 || gamePanel.getGame().getTilePackage().getValueByLetter(input.charAt(0)) == -1) {
                            placeMsg.setText("Your inputs have invalid characters!");
                            clearText();
                            return;
                        }

                        tempTiles.add(new Tile(gamePanel.getGame().getTilePackage().getValueByLetter(input.charAt(0)), String.valueOf(input.charAt(0))));
                        tempLocations.add(allLocations.get(signal));

                        System.out.println(allLocations.get(0));

                    }

                    if (!gamePanel.getGame().getCurPlayer().isIn(tempTiles)) {
                        placeMsg.setText("Your input tiles are not all in your inventory!");
                        clearText();
                    } else {
                        for (int i = 0; i < tempLocations.size(); i++) {
                            tempLocations2.add(new Location(tempLocations.get(i).getX(), tempLocations.get(i).getY()));
                            tempLocations2.get(i).addTile(tempTiles.get(i));
                        }
                        gamePanel.getGame().setCurTiles(tempLocations2);

                        if (game.execute() == -1) {
                            placeMsg.setText("Your placement is not adjacent to an existing word!");
                            tempLocations2 = new ArrayList<>();
                            clearText();

                        } else {
                            for (int i = 0; i < tempLocations.size(); i++) {
                                tempLocations.get(i).addTile(tempTiles.get(i));
                            }
                            gamePanel.getGame().setCurTiles(tempLocations);
                            game.execute(); //store tiles in location
                            game.getCurPlayer().removeTiles(game.getTilePackage().getTiles(tempTiles.size()));
                            game.getCurPlayer().refillTile(game.getTilePackage().getTiles(tempTiles.size()));
                            placeMsg.setText("You have pass the filtering!");
                            gamePanel.removeSettingPanel();
                            /////////////////////////////////////////////
                            game.updateOrder();
                            gamePanel.updateAll();
                            gamePanel.setGameMsgLabel("Player: " + game.getCurPlayer().getName() + " can have your move now!");
                        }

                    }
                }
            }

        });
    }
}
