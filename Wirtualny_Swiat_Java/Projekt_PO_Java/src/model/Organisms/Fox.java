package model.Organisms;

import model.Animal;
import model.Organism;
import model.Point;
import model.World;

import javax.swing.text.Position;
import java.awt.*;

public class Fox extends Animal {
    private Color foxColor = Color.ORANGE;
    private final int attempsToMove = 10;

    public Fox(World world, Point position){
        super(world, position, 3, 7);
    }

    @Override
    public Color getColor(){
        return foxColor;
    }

    @Override
    public String getNazwa(){
        return "Lis";
    }

    @Override
    public void Action(){
        increaseAge();
        Point currentPos = getPosition();

        for (int i = 0; i < attempsToMove; i++){
            int direction = random.nextInt(4);
            Point newPosition = new Point(currentPos.getX(), currentPos.getY());

            switch (direction){
                case 0 -> newPosition.setY(currentPos.getY() - 1);
                case 1 -> newPosition.setX(currentPos.getX() + 1);
                case 2 -> newPosition.setY(currentPos.getY() + 1);
                case 3 -> newPosition.setX(currentPos.getX() - 1);
            }

            if (shouldMove(newPosition)){
                world.tryToMoveOrganism(this, newPosition);
                return;
            }
        }
    }

    private boolean shouldMove(Point position){
        int x = position.getX();
        int y = position.getY();
        for (Organism o : world.getOrganisms()){
            if (x == o.getPosition().getX() && y == o.getPosition().getY() && o.getPower() > this.getPower()){
//                world.addEvent("Lis ucieka przed " + o.getNazwa() + " na pozycji(" + x + ", " + y + ")");
                return false;
            }
        }
        return true;
    }

    @Override
    public Organism makeNewOrganism(Point position){
        return new Fox(world, position);
    }
}
