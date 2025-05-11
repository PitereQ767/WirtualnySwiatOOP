package model.Organisms;

import model.Organism;
import model.Plant;
import model.Point;
import model.World;

import java.awt.*;

public class BarszczSosnowskiego extends Plant {
    private final Color barszczColor = Color.RED;

    public BarszczSosnowskiego(World world, Point position){
        super(world, position, 10);
    }

    @Override
    public Color getColor(){
        return barszczColor;
    }

    @Override
    public String getNazwa(){
        return "BarszczSosnowskiego";
    }

    @Override
    public Organism makeNewOrganism(Point position){
        return new BarszczSosnowskiego(world, position);
    }

    @Override
    public void Action(){
        super.Action();

        Point currentPos = getPosition();

        for (int i = 1; i < 5; i++){
            Point target = new Point(currentPos.getX(), currentPos.getY());

            switch (i){
                case 1 -> target.setY(target.getY() - 1);
                case 2 -> target.setX(target.getX() + 1);
                case 3 -> target.setY(target.getY() + 1);
                case 4 -> target.setX(target.getX() - 1);
            }

            if (world.isCorrectPosition(target) && !world.isEmpty(target)){
                Organism other = world.getOrganismaAtPosition(target);

                if (other instanceof Cyber_Sheep){
                    continue;
                }

                if (other instanceof BarszczSosnowskiego){
                    continue;
                }

                if (other instanceof Human){
                    world.killHuman();
                    continue;
                }

                other.setAlive(false);
                Point otherPosition = other.getPosition();
                world.addEvent("Barszcz Sosnowskiego zabil " + other.getNazwa() + " na pozycji " + otherPosition.getX() + ", " + otherPosition.getY() + ")");
            }
        }
    }
}
