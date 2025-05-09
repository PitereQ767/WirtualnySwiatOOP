package model.Organisms;

import model.Animal;
import model.Point;
import model.World;

import java.awt.*;

public class Sheep extends Animal {
    private Color sheepColor = Color.LIGHT_GRAY;

    public Sheep(World world, Point position){
        super(world, position, 4, 4);
    }

    @Override
    public Color getColor(){
        return sheepColor;
    }

    @Override
    public String getNazwa(){
        return "Owca";
    }
}
