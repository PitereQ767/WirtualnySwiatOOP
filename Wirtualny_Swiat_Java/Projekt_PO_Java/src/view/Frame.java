package view;

import model.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Frame extends JFrame {
    private final Panel panel;
    private final World world;
    private final int cellSize = 20;

    private final JLabel infoLabel;
    private int tura = 0;

    private final JLabel idLabel;

    private JLabel directionLabel;
    private World.Direction humanMove = null;

    public Frame(World world){
        this.world = world;
        setTitle("World Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        panel = new Panel(world, cellSize);
        add(panel, BorderLayout.CENTER);


        JPanel infoPanel = new JPanel();
        infoLabel = new JLabel("Tura: " + tura);
        directionLabel = new JLabel("Kierunek: brak");
        infoPanel.add(infoLabel);
        infoPanel.add(directionLabel);
        add(infoPanel, BorderLayout.SOUTH);

        JPanel idPanel = new JPanel();
        idLabel = new JLabel("Piotr Ratkowski id: 203285");
        idPanel.add(idLabel);
        add(idPanel, BorderLayout.NORTH);

        JPanel legendPanel = new JPanel();
        legendPanel.setLayout(new BoxLayout(legendPanel, BoxLayout.Y_AXIS)); // pionowe ułożenie
        legendPanel.setBorder(BorderFactory.createTitledBorder("Description"));

        legendPanel.add(createLegendItem(Color.GRAY, "Wolf"));
        legendPanel.add(createLegendItem(Color.ORANGE, "Fox"));
        legendPanel.add(createLegendItem(Color.YELLOW, "Antylope"));
        legendPanel.add(createLegendItem(Color.CYAN, "CyberSheep"));
        legendPanel.add(createLegendItem(Color.PINK, "Human"));
        legendPanel.add(createLegendItem(Color.GREEN, "Turtle"));
        legendPanel.add(createLegendItem(Color.LIGHT_GRAY, "Sheep"));

        legendPanel.setPreferredSize(new Dimension(120, world.getHeight()));

        add(legendPanel, BorderLayout.EAST);



        pack();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP -> {
                        humanMove = World.Direction.GORA;
                        directionLabel.setText("Kierunek: GÓRA");
                    }
                    case KeyEvent.VK_DOWN -> {
                        humanMove = World.Direction.DOL;
                        directionLabel.setText("Kierunek: DÓŁ");
                    }
                    case KeyEvent.VK_LEFT -> {
                        humanMove = World.Direction.LEWO;
                        directionLabel.setText("Kierunek: LEWO");
                    }
                    case KeyEvent.VK_RIGHT -> {
                        humanMove = World.Direction.PRAWO;
                        directionLabel.setText("Kierunek: PRAWO");
                    }
                    case KeyEvent.VK_ENTER -> {
                        if (humanMove != null) {
                            world.humanMove = humanMove;
//                            world.wykonajTure();
                            humanMove = null;
                            directionLabel.setText("Kierunek: brak");
                            tura++;
                            infoLabel.setText("Tura: " + tura);
                            panel.repaint();
                        }
                    }
                }
            }
        });

        setFocusable(true);
    }

    private JPanel createLegendItem(Color color, String name) {
        JPanel item = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel colorLabel = new JLabel("■");
        colorLabel.setForeground(color);
        JLabel textLabel = new JLabel(" - " + name);
        item.add(colorLabel);
        item.add(textLabel);
        return item;
    }


}
