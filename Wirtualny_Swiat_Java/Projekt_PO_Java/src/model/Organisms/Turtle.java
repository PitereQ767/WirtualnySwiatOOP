package model.Organisms;

import model.Animal;
import model.Organism;
import model.Point;
import model.World;

import java.awt.*;
import java.util.Objects;

public class Turtle extends Animal {
    private Color turtleColor = Color.GREEN;

    public Turtle(World world, Point position){
        super(world, position, 2, 1);
    }

    @Override
    public Color getColor(){
        return turtleColor;
    }

    @Override
    public String getNazwa(){
        return "Zolw";
    }
    @Override
    public void Action(){
        int randomNum = random.nextInt(100);
        if (randomNum < 75){
            increaseAge();
            world.addEvent("Zółw zostaje w miejscu na pozycji (" + this.getPosition().getX() + ", " + this.getPosition().getY() + ")");
            return;
        }
        super.Action();
    }

    @Override
    public Organism makeNewOrganism(Point position){
        return new Turtle(world, position);
    }

    @Override
    public void Collision(Organism attacker){
        if (Objects.equals(this.getNazwa(), attacker.getNazwa())){
            super.Multiplitaction(attacker);
            return;
        }

        if (attacker.getPower() < 5){
            world.addEvent("Zołw odparł atak " + attacker.getNazwa() + " na pozycji (" + getPosition().getX() + ", " + getPosition().getY() + ")");
            return;
        }

        collisionHelper(attacker, this.getPosition());
    }
}
