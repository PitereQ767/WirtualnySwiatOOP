package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Organism {
    private final int initiative;
    private int age, power;
    private boolean alive = true;
    protected World world;
    Point position;
    public final Random random = new Random();

    public Organism(World world, Point position, int power, int initiative){
        this.world = world;
        this.position = position;
        this.initiative = initiative;
        this.power = power;
    }

    public abstract Color getColor();
    public abstract void Action();
    public abstract String getNazwa();
    public abstract void Collision(Organism attacker);
    public abstract Organism makeNewOrganism(Point position);

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean getIsAlive() {
        return alive;
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

    public void setPower(int power){
        this.power = power;
    }

    protected void increaseAge(){
        this.age += 1;
    }

    protected Point findPosition() {
        List<Point> positions = new ArrayList<>();
        Point currentPosition = getPosition();

        // Sprawdź wszystkie 4 sąsiednie pola
        int x = currentPosition.getX();
        int y = currentPosition.getY();

        Point[] directions = new Point[] {
                new Point(x, y - 1), // góra
                new Point(x + 1, y), // prawo
                new Point(x, y + 1), // dół
                new Point(x - 1, y)  // lewo
        };

        for (Point pos : directions) {
            if (world.isCorrectPosition(pos) && world.isEmpty(pos)) {
                positions.add(pos);
            }
        }

        if (positions.isEmpty()) {
            return currentPosition; // nie ma gdzie się rozmnożyć
        }

        return positions.get(random.nextInt(positions.size())); // losowa dostępna pozycja
    }

    public void collisionHelper(Organism attacker, Point position){
        if(attacker.getPower() >= this.getPower()){
            this.setAlive(false);
            world.addEvent(attacker.getNazwa() + " zabił " + this.getNazwa() + " na pozycji (" + position.getX() + ", " + position.getY() + ")");
            if (attacker.getIsAlive()){
                world.moveOrganism(attacker, position);
            }

        }else {
            attacker.setAlive(false);
            world.addEvent(this.getNazwa() + " zaił " + attacker.getNazwa() + " na pozycji (" + position.getX() + ", " + position.getY() + ")");
            if (this.getIsAlive()){
                world.moveOrganism(this, position);
            }
        }
    }

}
