package bricksgame;

import javax.swing.*;

public class Main {

    public static void main(String[] args)
    {
        JFrame obj=new JFrame();
        Gameplay gameplay=new Gameplay();
        obj.setBounds(0,0,550,550);
        obj.setTitle("game");
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gameplay);
//      System.out.println("hi");
    }
}
