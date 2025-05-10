package model;

import java.util.Random;

public abstract class Animal extends Organism{
    public final Random random = new Random();

    public Animal(World world, Point position, int power, int initiative){
        super(world, position, power, initiative);
    }

    @Override
    public void Action(){
        increaseAge();
        Point currentPos = getPosition();
        int direction = random.nextInt(4);
        Point newPosition = new Point(currentPos.getX(), currentPos.getY());

        switch (direction){
            case 0 -> newPosition.setY(currentPos.getY() - 1);
            case 1 -> newPosition.setX(currentPos.getX() + 1);
            case 2 -> newPosition.setY(currentPos.getY() + 1);
            case 3 -> newPosition.setX(currentPos.getX() - 1);
        }

        world.tryToMoveOrganism(this, newPosition);
    }

    @Override
    public void Collision(){

    }
}
