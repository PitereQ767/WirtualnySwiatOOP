package view;

import model.Organism;
import model.Organisms.*;
import model.Point;
import model.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Frame extends JFrame {
    private final Panel panel;
    private final World world;
    private final int cellSize = 20;

    private final JLabel infoLabel;

    private final JLabel idLabel;

    private JLabel directionLabel;
    private  JLabel immortalityLabel;
    private World.Direction humanMove = null;

    private final JTextArea logArea;

    private int humanLastImmortality;

    public Frame(World world){
        this.world = world;
        setTitle("World Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        panel = new Panel(world, cellSize);
        add(panel, BorderLayout.CENTER);
        addMouseListenera(panel);


        JPanel infoPanel = new JPanel();
        infoLabel = new JLabel("Tura: " + world.getTura());
        directionLabel = new JLabel("Kierunek: brak");
        immortalityLabel = new JLabel("Niesmiertelnosc: Nieaktywna");
        infoPanel.add(infoLabel);
        infoPanel.add(directionLabel);
        infoPanel.add(immortalityLabel);
        add(infoPanel, BorderLayout.SOUTH);

        JPanel idPanel = new JPanel();
        idLabel = new JLabel("Piotr Ratkowski id: 203285");
        idPanel.add(idLabel);
        add(idPanel, BorderLayout.NORTH);

        JPanel legendPanel = new JPanel();
        legendPanel.setLayout(new BoxLayout(legendPanel, BoxLayout.Y_AXIS)); // pionowe ułożenie
        legendPanel.setBorder(BorderFactory.createTitledBorder("Description"));

        legendPanel.add(createLegendItem(Color.DARK_GRAY, "Wolf"));
        legendPanel.add(createLegendItem(Color.ORANGE, "Fox"));
        legendPanel.add(createLegendItem(Color.YELLOW, "Antylope"));
        legendPanel.add(createLegendItem(Color.CYAN, "CyberSheep"));
        legendPanel.add(createLegendItem(Color.PINK, "Human"));
        legendPanel.add(createLegendItem(Color.GREEN, "Turtle"));
        legendPanel.add(createLegendItem(Color.LIGHT_GRAY, "Sheep"));
        legendPanel.add(createLegendItem(Color.BLUE, "Milkweed"));
        legendPanel.add(createLegendItem(Color.BLACK, "Grass"));
        legendPanel.add(createLegendItem(Color.MAGENTA, "Berries"));
        legendPanel.add(createLegendItem(Color.GRAY, "Guarana"));
        legendPanel.add(createLegendItem(Color.RED, "Barszcz Sosnowskiego"));


        legendPanel.setPreferredSize(new Dimension(180, world.getHeight()));

        add(legendPanel, BorderLayout.EAST);

        logArea = new JTextArea(10, 25);
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);
        JPanel lefPanel = new JPanel();
        lefPanel.setLayout(new BoxLayout(lefPanel, BoxLayout.Y_AXIS));
        lefPanel.add(new JLabel("Log zdarzeń: "));
        lefPanel.add(scrollPane);

        add(lefPanel, BorderLayout.WEST);


        pack();

        setFocusable(true);
        requestFocusInWindow();


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
                    case KeyEvent.VK_N -> {
                        Human human = world.getHuman();
                        if (!human.getImmortality()){
                            human.setImmortality(true);
                            immortalityLabel.setText("Niesmiertelnosc: Aktywna");
                            human.setImortalityRounds(5);
                        }
                    }
                    case KeyEvent.VK_ENTER -> {
                        if ( ! world.isEndGame()){
                            if (humanMove != null) {
                                world.humanMove = humanMove;
                                world.makeRun();
                                humanMove = null;
                                directionLabel.setText("Kierunek: brak");

                                updateImmortalityLabel();

                                StringBuilder sb = new StringBuilder();
                                for (String message : world.getEvents()){
                                    sb.append(message).append("\n");
                                }
                                logArea.setText(sb.toString());
                                world.clearEventLog();

                                world.setTura(world.getTura() + 1);
                                infoLabel.setText("Tura: " + world.getTura());
                                panel.repaint();
                            }else {
                                world.makeRun();
                                updateImmortalityLabel();

                                StringBuilder sb = new StringBuilder();
                                for (String message : world.getEvents()){
                                    sb.append(message).append("\n");
                                }

                                logArea.setText(sb.toString());
                                world.clearEventLog();
                                world.setTura(world.getTura() + 1);
                                infoLabel.setText("Tura: " + world.getTura());
                                panel.repaint();
                            }
                        }else {
                            JOptionPane.showMessageDialog(Frame.this, "Gra zakończona!");
                            dispose();
                            return;
                        }
                    }
                    case KeyEvent.VK_S -> {
                        world.saveToFile();
                        JOptionPane.showMessageDialog(Frame.this, "Gra została zapisana!");
                    }
                    case KeyEvent.VK_L -> {
                        world.loadGameFromFile();
                        repaint();
                        infoLabel.setText("Tura: " + world.getTura());
                        updateImmortalityLabel();
                        JOptionPane.showMessageDialog(Frame.this, "Gra została wczytana!");
                    }
                }
            }
        });
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

    private void updateImmortalityLabel() {
        Human human = world.getHuman();
        if (human != null && human.getImmortality()) {
            immortalityLabel.setText("Niesmiertelnosc: Aktywna (" + human.getImortalityRounds() + ")");
        } else {
            immortalityLabel.setText("Niesmiertelnosc: Nieaktywna");
        }
    }

    private void addMouseListenera(Panel panel){
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                int x = event.getX() / cellSize;
                int y = event.getY() / cellSize;
                Point clickedPoint = new Point(x, y);

                if (world.isEmpty(clickedPoint)){
                    showOptionsOrganisms(clickedPoint);
                }
            }

        });
    }

    private void showOptionsOrganisms(Point position){
        String[] options = {"Wilk", "Lis", "Antylopa", "CyberOwca", "Guarana", "Mlecz", "Owca", "Trawa",
        "WilczeJagody", "Zolw", "BarszczSosnowskiego"};

        String choice = (String) JOptionPane.showInputDialog(this, "Wybierz organizm, który chcesz dodać", "Dodaj organizm", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        if (choice != null){

            Organism newOrganism = switch (choice){
                case "Wilk" -> new Wolf(world, position);
                case "Lis" -> new Fox(world, position);
                case "Antylopa" -> new Antelope(world, position);
                case "CyberOwca" -> new Cyber_Sheep(world, position);
                case "Guarana" -> new Guarana(world, position);
                case "Mlecz" -> new Milkweed(world, position);
                case "Owca" -> new Sheep(world, position);
                case "Trawa" -> new Grass(world, position);
                case "WilczeJagody" -> new Berries(world, position);
                case "Zolw" -> new Turtle(world, position);
                case "BarszczSosnowskiego" -> new BarszczSosnowskiego(world, position);
                default -> null;
            };

            if (newOrganism != null){
                world.addOrganism(newOrganism, position);
                repaint();
            }
        }
    }



}
