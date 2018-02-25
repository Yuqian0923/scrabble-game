package edu.cmu.cs.cs214.hw4.gui;

import edu.cmu.cs.cs214.hw4.core.Game;
import edu.cmu.cs.cs214.hw4.core.GameChangeListener;

import javax.swing.*;

public class GamePanelHelper2 {
    private Game game;
    private GamePanel gamePanel;
    private int startX;
    private int startY;
    private int direction;
    //private GameChangeListener gameChangeListener;

    public GamePanelHelper2(GamePanel var1, int var3, int var4, int var5) {
        this.gamePanel = var1;
        //this.gameChangeListener = var2;
        this.startX = var3;
        this.startY = var4;
        this.direction = var5;
        run();
    }

    public void run() {
        JFrame frame = new JFrame("Tile Place Window For Fancy Scribble 1.0");
        PlacePanel placePanel = new PlacePanel(this.gamePanel, this.game, frame, this.startX, this.startY, this.direction);
        placePanel.setOpaque(true);
        frame.add(placePanel);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setResizable(true);
        frame.pack();
        frame.setVisible(true);
    }
}
