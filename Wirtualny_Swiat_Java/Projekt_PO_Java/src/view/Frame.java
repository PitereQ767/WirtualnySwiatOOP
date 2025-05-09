package view;

import model.World;

import javax.swing.*;

public class Frame extends JFrame {
    public Frame(World world){
        setTitle("World Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new Panel(world, 30));
        pack();
    }
}
