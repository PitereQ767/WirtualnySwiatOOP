package view;

import model.Organism;
import model.Point;
import model.World;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Panel extends JPanel {
    private final World world;
    private final int cellSize;
    private final Color gridColor = Color.BLACK;

    public Panel(World world, int cellSize){
        this.world = world;
        this.cellSize = cellSize;
        setSize();
        setBackground(Color.WHITE);
    }

    private void setSize() {
        int width = world.getWidth() * cellSize;
        int height = world.getHeight() * cellSize;
        setPreferredSize(new Dimension(width, height));
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        drawGrid(g);
        drawOrganisms(g);
    }

    private void drawGrid(Graphics g){
        for (int i = 0; i < world.getHeight(); i++){
            g.drawLine(0, i *cellSize, world.getWidth() * cellSize, i * cellSize);
        }

        for (int i = 0; i < world.getWidth(); i++){
            g.drawLine(i * cellSize, 0, i * cellSize, world.getHeight() * cellSize);
        }
    }

    private void drawOrganisms(Graphics g){
        List<Organism> organismList = world.getOrganisms();

        for (Organism o : organismList){
            g.setColor(o.getColor());

            Point position = o.getPosition();
            int x = position.getX();
            int y = position.getY();
            g.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
        }
    }
}
