package model;

import java.awt.*;

public abstract class Organism {
    private final int initiative, power, age = 0;
    boolean alive;
    World world;
    Point position;

    public Organism(World world, Point position, int power, int initiative){
        this.world = world;
        this.position = position;
        this.initiative = initiative;
        this.power = power;
    }

    public abstract Color getColor();

    public Point getPosition() {
        return position;
    }
}
