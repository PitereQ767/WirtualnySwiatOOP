package view;

import model.World;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    private final World world;
    private final int cellSize;

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
}
