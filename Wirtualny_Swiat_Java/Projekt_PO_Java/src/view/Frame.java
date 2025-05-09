package view;

import model.World;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    private final Panel panel;
    private final World world;
    private final int cellSize = 20;

    private final JLabel infoLabel;
    private int tura = 0;

    private final JLabel idLabel;

    public Frame(World world){
        this.world = world;
        setTitle("World Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        panel = new Panel(world, cellSize);
        add(panel, BorderLayout.CENTER);

        JPanel infoPanel = new JPanel();
        infoLabel = new JLabel("Tura: " + tura);
        infoPanel.add(infoLabel);
        add(infoPanel, BorderLayout.SOUTH);

        JPanel idPanel = new JPanel();
        idLabel = new JLabel("Piotr Ratkowski id: 203285");
        idPanel.add(idLabel);
        add(idPanel, BorderLayout.NORTH);

        pack();
        setFocusable(true);
    }
}
