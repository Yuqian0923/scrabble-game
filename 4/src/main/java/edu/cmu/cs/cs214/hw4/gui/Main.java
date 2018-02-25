package edu.cmu.cs.cs214.hw4.gui;


import java.io.IOException;
import javax.swing.*;
import javax.swing.JFrame;

public class Main {
    private static final String GAME_NAME = "Bored Scribble 1.0";




    public static void main(String[] args) {
        JFrame frame = new JFrame("Bored Scribble 1.0");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Entry gamePanel = new Entry(frame);
        gamePanel.setOpaque(true);
        frame.add(gamePanel);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(true);
    }
}
