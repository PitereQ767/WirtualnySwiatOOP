package model.Organisms;

import model.Animal;
import model.Organism;
import model.Point;
import model.World;

import java.awt.*;

public class Human extends Animal {
    private Color humanColor = Color.PINK;
    private boolean immortality = false;
    private int imortalityRounds = 0;

    public Human(World world, Point position){
        super(world, position, 5, 4);
    }


    public boolean getImmortality() {
        return immortality;
    }

    public int getImortalityRounds(){
        return imortalityRounds;
    }

    public void setImortalityRounds(int tmp){
        imortalityRounds = tmp;
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
        return "Czlowiek";
    }

    @Override
    public Organism makeNewOrganism(Point position){
        return new Human(world, position);
    }

    private void immortalityFunction(){
        if (getImmortality()){
            if (--imortalityRounds <= 0){
                setImmortality(false);
            }
        }
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
            world.humanMove = null;
        }

        immortalityFunction();
    }

    @Override
    public void Collision(Organism attacker){
        if (getImmortality()) {
            world.addEvent(this.getNazwa() + " uniknął śmierci dzięki nieśmiertelności przed " + attacker.getNazwa() + " na pozycji (" + getPosition().getX() + ", " + getPosition().getY() + ")");
            attacker.setAlive(false);
            return;
        }

        if(attacker.getPower() >= this.getPower()){
            world.killHuman();
            if (!getIsAlive()){
                world.addEvent(attacker.getNazwa() + " zabił " + this.getNazwa() + " na pozycji (" + getPosition().getX() + ", " + getPosition().getY() + ")");
                Point newAttackerPos = new Point(getPosition().getX(), getPosition().getY());
                world.moveOrganism(attacker, newAttackerPos);
            }
        } else {
            attacker.setAlive(false);
            world.addEvent(this.getNazwa() + " zabił " + attacker.getNazwa() + " na pozycji (" + getPosition().getX() + ", " + getPosition().getY() + ")");
            Point newDeffenderPos = new Point(getPosition().getX(), getPosition().getY());
            if (this.getIsAlive()){
                world.moveOrganism(this, newDeffenderPos);
            }
        }
    }

}
