package edu.cmu.cs.cs214.hw4.gui;


import edu.cmu.cs.cs214.hw4.core.Game;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.cmu.cs.cs214.hw4.core.GameChangeListener;
import edu.cmu.cs.cs214.hw4.core.SpecialTile;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SpecialTilePanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private final JFrame frame;
    private final GameChangeListener gamePanel;
    private final Game game;
    private final SpecialTile specialTile;
    private static final String BUTTON_TITLE_1 = "submit";
    private static final String BUTTON_TITLE_2 = "cancel";
    private JLabel instructLabel;
    private JLabel xLabel;
    private JLabel yLabel;
    private JTextField xText;
    private JTextField yText;
    private JButton submitButton;
    private JButton cancelButton;
    private JLabel msgLabel;

    public SpecialTilePanel(SpecialTile specialTile, GameChangeListener gamePanel, Game game, JFrame frame) {
        this.frame = frame;
        this.gamePanel = gamePanel;
        this.game = game;
        this.specialTile = specialTile;
        this.instructLabel = new JLabel("Please type in the coordinate of your special tile:");
        this.xLabel = new JLabel("x coordinate: ");
        this.yLabel = new JLabel("y coordinate: ");
        this.xText = new JTextField(5);
        this.yText = new JTextField(5);
        this.submitButton = new JButton("submit");
        this.cancelButton = new JButton("cancel");
        this.msgLabel = new JLabel("");
        this.initial();
    }

    public void initial() {
        this.setLayout(new GridLayout(8, 1, 5, 5));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.DARK_GRAY));
        this.initialSubmitButton();
        this.initialCancelButton();
        this.add(this.instructLabel);
        this.add(this.xLabel);
        this.add(this.xText);
        this.add(this.yLabel);
        this.add(this.yText);
        this.add(this.submitButton);
        this.add(this.cancelButton);
        this.add(this.msgLabel);
    }

    public void initialCancelButton() {
        this.cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getCurPlayer().addScore(game.getPrice(specialTile));
                gamePanel.setTrue();
                gamePanel.setGameMsgLabel("Player: " + game.getCurPlayer().getName() + " has cancelled his/her special tile placement, score refunded.");
                gamePanel.updateAll();
            }
        });
    }

    public void initialSubmitButton() {
        //ActionListener submitListener = new 2(this);
        this.submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String xInput = xText.getText().trim();
                String yInput = yText.getText().trim();
                if (!xInput.equals("") && !yInput.equals("")) {
                    int xResult;
                    int yResult;
                    try {
                        xResult = Integer.parseInt(xInput);
                        yResult = Integer.parseInt(yInput);
                    } catch (NumberFormatException var7) {
                        msgLabel.setText("There is invalid character in your input!");
                        xLabel.setText("");
                        yLabel.setText("");
                        return;
                    }

                    if (!game.getGameBoard().isOnBoard(xResult, yResult)) {
                        msgLabel.setText("Your input coordinate is out of board!");
                        xLabel.setText("");
                        yLabel.setText("");
                    } else if (game.getGameBoard().getLocation(xResult, yResult).isOccupied()) {
                        msgLabel.setText("Your special tile should not set at the tile occupied location!");
                        xLabel.setText("");
                        yLabel.setText("");
                    } else {
                        /*String oldOwner = "";
                        if (game.getGameBoard().getLocation(xResult, yResult).getSpecialTile().size() != 0) {
                            for(int i=0;i<game.getGameBoard().getLocation(xResult, yResult).getSpecialTile().size();i++){
                                oldOwner = game.getGameBoard().getLocation(xResult, yResult).getSpecialTile().get(i).getOwner().getName();
                            }
                        }

                        /*if (oldOwner != "" && game.getGameBoard().getLocation(xResult, yResult).getSpecialTile().getName() == "StealMove" && game.getOwner().equals(oldOwner)) {
                            game.getGameBoard().getLocation(xResult, yResult).removeSpecialTile();
                        }*/
                        game.getGameBoard().setSpecialTileToLocation(specialTile, xResult, yResult);
                        gamePanel.setTrue();
                        gamePanel.setGameMsgLabel("Player: " + game.getCurPlayer().getName() + " 's special tile has been successfully set!");
                        gamePanel.updateAll();
                    }
                } else {
                    msgLabel.setText("Your input should not be empty!");
                    xLabel.setText("");
                    yLabel.setText("");
                }
            }
        });
    }
}
