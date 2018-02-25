package edu.cmu.cs.cs214.hw4.gui;

import edu.cmu.cs.cs214.hw4.core.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * take the arrayList from Panel1 and then init game.
 */
class EntryPanel2 implements ActionListener {
    private EntryPanel panel;
    private Game game;

    public EntryPanel2(EntryPanel panel, Game game) {
        this.panel = panel;
        this.game = game;
    }

    public void actionPerformed(ActionEvent arg) {
        if (panel.getPlayerList().size() != 0) {
            panel.getTextField().setText("There is no player added now...");
        } else {
            this.game.initGame();
        }
    }
}