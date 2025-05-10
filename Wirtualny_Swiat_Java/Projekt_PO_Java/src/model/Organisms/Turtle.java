package model.Organisms;

import model.Animal;
import model.Point;
import model.World;

import java.awt.*;

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
        return "Żółw";
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
}
