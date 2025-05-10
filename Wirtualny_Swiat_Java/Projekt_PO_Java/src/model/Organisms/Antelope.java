package model.Organisms;

import model.Animal;
import model.Point;
import model.World;

import java.awt.*;

public class Antelope extends Animal {
    private Color antylopeColor = Color.YELLOW;

    public Antelope(World world, Point position){
        super(world, position, 4, 4);
    }

    @Override
    public Color getColor(){
        return antylopeColor;
    }

    @Override
    public String getNazwa(){
        return "Antylopa";
    }

    @Override
    public void Action(){
        increaseAge();
        Point currentPos = getPosition();
        int direction = random.nextInt(4);
        Point newPosition = new Point(currentPos.getX(), currentPos.getY());

        switch (direction){
            case 0 -> newPosition.setY(currentPos.getY() - 2);
            case 1 -> newPosition.setX(currentPos.getX() + 2);
            case 2 -> newPosition.setY(currentPos.getY() + 2);
            case 3 -> newPosition.setX(currentPos.getX() - 2);
        }

        world.tryToMoveOrganism(this, newPosition);
    }
}
