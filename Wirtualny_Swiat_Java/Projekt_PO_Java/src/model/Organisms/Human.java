package model.Organisms;

import model.Animal;
import model.Organism;
import model.Point;
import model.World;

import java.awt.*;

public class Human extends Animal {
    private Color humanColor = Color.PINK;
    private boolean immortality = false;

    public Human(World world, Point position){
        super(world, position, 5, 4);
    }


    public boolean getImmortality() {
        return immortality;
    }

    public void setImmortality(boolean tmp) {
        this.immortality = tmp;
    }

    @Override
    public Color getColor(){
        return humanColor;
    }

    @Override
    public String getNazwa(){
        return "Człowiek";
    }

    @Override
    public Organism makeNewOrganism(Point position){
        return new Human(world, position);
    }

    @Override
    public void Action(){
        increaseAge();

        if (world.humanMove != null){
            Point currentPos = getPosition();
            World.Direction direction = world.humanMove;
            Point newPosition = new Point(currentPos.getX(), currentPos.getY());

            switch (direction){
                case GORA -> newPosition.setY(currentPos.getY() - 1);
                case DOL -> newPosition.setY(currentPos.getY() + 1);
                case LEWO -> newPosition.setX(currentPos.getX() - 1);
                case PRAWO -> newPosition.setX(currentPos.getX() + 1);
            }

            world.tryToMoveOrganism(this, newPosition);
        }
    }
}
