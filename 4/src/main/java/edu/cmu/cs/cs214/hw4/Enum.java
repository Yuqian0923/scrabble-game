package edu.cmu.cs.cs214.hw4;

import javax.swing.*;

public class Enum extends JFrame {
    public Enum(){
        JFrame frame = new JFrame();
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTextField text= new JTextField();

        if (text.getText().equals(Day.SUNDAY)) {

        }
    }

    public static enum Day {
        SUNDAY, MONDAY, TUESDAY, WEDNESDAY,
        THURSDAY, FRIDAY, SATURDAY
    }



}
