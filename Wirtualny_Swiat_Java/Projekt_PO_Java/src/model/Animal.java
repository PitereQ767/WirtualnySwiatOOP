package model;

import java.util.Objects;
import java.util.Random;

public abstract class Animal extends Organism{


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
    public void Collision(Organism deffender){
        if (Objects.equals(this.getNazwa(), deffender.getNazwa())){
            Multiplitaction(deffender);
            return;
        }

        collisionHelper(deffender, deffender.getPosition());
    }

    private void Multiplitaction(Organism organism){
        Point newPosiotion = findPosition();
        if (newPosiotion.getX() != getPosition().getX() || newPosiotion.getY() != getPosition().getY()){
            Organism newOrganism = makeNewOrganism(newPosiotion);
            world.addOrganism(newOrganism, newPosiotion);
            world.addEvent(organism.getNazwa() + " rozmnożył się na pozycji " + newPosiotion.getX() + ", " + newPosiotion.getY() + ")");
        }

    }
}
