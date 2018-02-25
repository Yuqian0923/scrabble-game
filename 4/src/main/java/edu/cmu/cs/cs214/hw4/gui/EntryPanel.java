package edu.cmu.cs.cs214.hw4.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * create the player info before game begins.
 */
public class EntryPanel implements ActionListener {
    private JTextField text;
    private JLabel label;
    private ArrayList<String> playerList;
    private JButton submit;

    public EntryPanel(JTextField text, JLabel label) {
        //this.text = new JTextField(10);
        this.text = text;
        //this.label = new JLabel("player name:");
        this.label = label;
        playerList = new ArrayList<>();
        submit = new JButton("submit");

    }

    public ArrayList<String> getPlayerList() {
        return this.playerList;
    }

    public JTextField getTextField() {
        return this.text;
    }


    /**
     * Invoked when an action occurs.
     *
     * @param e actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String inputName = this.text.getText().trim();
        if (inputName.equals("")) {
            text.setText("The player's name cannot be empty!");
            text.setText("Enter your name here: ");
        } else if (playerList.contains(inputName)) {
            text.setText("The player's name has already exist!");
            text.setText("Enter your name here: ");
        } else if (playerList.size() == 4) {
            text.setText("The number of players has reached the maximum!");
            text.setText("Enter your name here: ");
        } else {
            playerList.add(inputName);
            text.setText("Player " + inputName + " has been successfully added!");
            text.setText(text.getText() + " " + inputName);
            text.setText("Enter your name here: ");
            text.requestFocus();
        }
    }


}

