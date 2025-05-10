package model;

import java.awt.*;

public abstract class Organism {
    private final int initiative, power;
    private int age;
    boolean alive;
    protected World world;
    Point position;

    public Organism(World world, Point position, int power, int initiative){
        this.world = world;
        this.position = position;
        this.initiative = initiative;
        this.power = power;
    }

    public abstract Color getColor();
    public abstract void Action();
    public abstract String getNazwa();
    public abstract void Collision();

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public int getInitiative() {
        return initiative;
    }

    public int getAge() {
        return age;
    }

    public int getPower() {
        return power;
    }

    protected void increaseAge(){
        this.age += 1;
    }
}
