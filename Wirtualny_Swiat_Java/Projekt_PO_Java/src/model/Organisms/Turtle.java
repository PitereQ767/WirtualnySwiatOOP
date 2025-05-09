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
}
