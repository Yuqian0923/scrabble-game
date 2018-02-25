package edu.cmu.cs.cs214.hw4.gui;

import edu.cmu.cs.cs214.hw4.core.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.cmu.cs.cs214.hw4.core.GameChangeListener;
import edu.cmu.cs.cs214.hw4.core.Player;
import edu.cmu.cs.cs214.hw4.core.Tile;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ExchangePanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private final JFrame frame;
    private final GameChangeListener gamePanel;
    private final Game game;
    private static final int INPUT_LENGTH = 20;
    private static final String BUTTON_TITLE_1 = "exchange";
    private static final String BUTTON_TITLE_2 = "cancel";
    private static final String MSG_INITIAL = "";
    private JLabel exchangeInstructLabel;
    private JTextField exchangeInput;
    private JButton exchangeSubmitButton;
    private JButton exchangeCancelButton;
    private JLabel exchangeMsgLabel;

    public ExchangePanel(GamePanel gamePanel, Game game, JFrame frame) {
        this.gamePanel = gamePanel;
        this.game = game;
        this.frame = frame;
        this.exchangeInstructLabel = new JLabel("Please type in the tiles you want to exchange:");
        this.exchangeInput = new JTextField(20);
        this.exchangeSubmitButton = new JButton("exchange");
        this.exchangeCancelButton = new JButton("cancel");
        this.exchangeMsgLabel = new JLabel("");
        this.initial();
    }

    public void initial() {
        this.setLayout(new GridLayout(5, 1, 10, 10));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.DARK_GRAY));
        this.initialSubmitButton();
        this.initialCancelButton();
        this.add(this.exchangeInstructLabel);
        this.add(this.exchangeInput);
        this.add(this.exchangeSubmitButton);
        this.add(this.exchangeCancelButton);
        this.add(this.exchangeMsgLabel);
    }

    public void initialCancelButton() {
        this.exchangeCancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.setTrue();
                gamePanel.setGameMsgLabel("Player: " + game.getCurPlayer().getName() + " has cancelled his/her tile exchange.");
                gamePanel.updateAll();
            }
        });
    }

    public void initialSubmitButton() {
        //ActionListener submitListener = new 2 (this);
        this.exchangeSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = exchangeInput.getText().trim();
                if (input.equals("")) {
                    exchangeMsgLabel.setText("Your exchange tiles cannot be empty!");
                } else if (input.length() > 7) {
                    exchangeMsgLabel.setText("Your exchange tiles have exceeded the maximum number 7!");
                } else {
                    ArrayList<Tile> exchangeTiles = new ArrayList<>();

                    for (int i = 0; i < input.length(); ++i) {
                        char letter = input.charAt(i);
                        int value = game.getTilePackage().getValueByLetter(letter);
                        if (value == -1) {
                            exchangeMsgLabel.setText("There are invalid letter in your input!");
                            return;
                        }

                        System.out.println("value: " + value);
                        System.out.println("letter: " + letter);
                        Tile newTile = new Tile(value, String.valueOf(letter));
                        exchangeTiles.add(newTile);
                    }

                    if (!game.getCurPlayer().isIn(exchangeTiles)) {
                        exchangeMsgLabel.setText("There are tiles that are not in your inventory!");
                    } else {
                        game.exchangeTiles(exchangeTiles);
                        Player prevPlayer = game.getCurPlayer();
                        game.updateOrder();
                        gamePanel.setTrue();
                        gamePanel.setGameMsgLabel("Player: " + prevPlayer.getName() + " 's tiles have been successfully exchanged! Now it's " + game.getCurPlayer().getName() + "'s turn!");
                        gamePanel.updateAll();

                    }
                }
            }
        });
    }
}
