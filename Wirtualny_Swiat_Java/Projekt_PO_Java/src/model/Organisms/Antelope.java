package model.Organisms;

import model.Animal;
import model.Organism;
import model.Point;
import model.World;

import java.awt.*;
import java.util.Objects;

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

    @Override
    public Organism makeNewOrganism(Point position){
        return new Antelope(world, position);
    }

    @Override
    public void Collision(Organism attacker){
        if (Objects.equals(this.getNazwa(), attacker.getNazwa())){
            super.Multiplitaction(attacker);
            return;
        }

        int ranNum = random.nextInt(100);

        if (ranNum < 50){
            Point newPos = findPosition();
            if (newPos.getX() != getPosition().getX() || newPos.getY() != getPosition().getY()){
                world.moveOrganism(attacker, this.getPosition());
                world.moveOrganism(this, newPos);
                world.addEvent("Antylopa ucieka przed " + attacker.getNazwa() + " na pozycje (" + newPos.getX() + ", " + newPos.getY() + ")");
                return;
            }
        }

        collisionHelper(attacker, this.getPosition());
    }

}
