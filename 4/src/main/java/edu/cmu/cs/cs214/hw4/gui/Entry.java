package edu.cmu.cs.cs214.hw4.gui;

import edu.cmu.cs.cs214.hw4.core.Game;
import edu.cmu.cs.cs214.hw4.core.Player;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;

public class Entry extends JPanel {
    private static final long serialVersionUID = 1L;
    private final ArrayList<String> players;
    private final JFrame gameFrame;
    private static final int MAX_PLAYER_NUM = 4;
    private static final String PLAYER_BUTTON_NAME = "Add new player";
    private static final String LAUNCH_BUTTON_NAME = "Launch";
    private static final String PLAYER_INITIAL_MSG = "Current players:";
    private static final String PLAYER_INITIAL_TEXT = "Enter your name here: ";
    private static final int TEXT_LENGTH = 25;
    private final JLabel MsgLabel = new JLabel("Welcome to scribble 1.0! Please enter your username and launch the game.");

    public Entry(JFrame frame) {
        this.gameFrame = frame;
        this.players = new ArrayList<>();
        JPanel addPlayerPanel = new JPanel();
        JButton addPlayerButton = new JButton("Add new player");
        final JTextField addPlayerText = new JTextField(25);
        addPlayerText.setText("Enter your name here: ");
        final JLabel addPlayerMsg = new JLabel("Current players:");


        addPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputName = addPlayerText.getText().trim();
                if (inputName.equals("")) {
                    Entry.this.MsgLabel.setText("The player's name cannot be empty!");
                    addPlayerText.setText("Enter your name here: ");
                } else if (Entry.this.players.contains(inputName)) {
                    Entry.this.MsgLabel.setText("The player's name has already exist!");
                    addPlayerText.setText("Enter your name here: ");
                } else if (Entry.this.players.size() == 4) {
                    Entry.this.MsgLabel.setText("The number of players has reached the maximum!");
                    addPlayerText.setText("Enter your name here: ");
                } else {
                    Entry.this.players.add(inputName);
                    Entry.this.MsgLabel.setText("Player " + inputName + " has been successfully added!");
                    addPlayerMsg.setText(addPlayerMsg.getText() + " " + inputName);
                    addPlayerText.setText("Enter your name here: ");
                    addPlayerText.requestFocus();
                }
            }
        });
        addPlayerPanel.setLayout(new BorderLayout());
        addPlayerPanel.add(addPlayerButton, "Center");
        addPlayerPanel.add(addPlayerMsg, "South");
        addPlayerPanel.add(addPlayerText, "North");
        ////////////////////////////////////Launch//////////////////////////////////////////////////

        JButton launchButton = new JButton("Launch");
        /*ActionListener launchListener = new ActionListener() {
            public void actionPerformed(ActionEvent arg) {
                if (Entry.this.players.isEmpty()) {
                    Entry.this.MsgLabel.setText("There is no player added now...");
                } else {
                    Entry.this.runGame();
                }
            }
        };*/
        launchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Entry.this.players.isEmpty()) {
                    Entry.this.MsgLabel.setText("There is no player added now...");
                }
                if (Entry.this.players.size() == 1) {
                    Entry.this.MsgLabel.setText("There should be at least 2 players!");
                } else {
                    runGame();
                }
            }
        });
        this.setLayout(new BorderLayout());
        this.add(this.MsgLabel, "North");
        this.add(addPlayerPanel, "Center");
        this.add(launchButton, "South");
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        this.setVisible(true);
    }

    /**
     * preCondition: players are not empty.
     *
     * @return Player List
     */
    private ArrayList<Player> convertToPlayerList() {
        ArrayList<Player> playersList = new ArrayList<>();
        int index = 0;
        for (String name : Entry.this.players) {
            playersList.add(new Player(name, index));
            index++;
        }
        return playersList;
    }

    public void runGame() {
        final Game game = new Game(Entry.this.players.size(), convertToPlayerList());
        String gameName = "Scribble 1.0";
        game.initGame();
        for (Player player : game.getPlayers()) {
            player.refillTile(game.getTilePackage().getTiles(7));
        }


        //System.out.println(game.getTilePackage().getNum());
        //System.out.println(game.getTilePackage().getTiles(7));
        // System.out.println(game.getPlayers().get(0).getName());
        //System.out.println(game.getPlayers().get(0).getTileList());

        JFrame frame = new JFrame("Scribble 1.0");
        GamePanel gamePanel = new GamePanel(game);
        gamePanel.setOpaque(true);
        frame.add(gamePanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.pack();
        frame.setVisible(true);
    }

    //this.gameFrame.dispose();
}

