package model.Organisms;

import model.Animal;
import model.Point;
import model.World;

import java.awt.*;

public class Human extends Animal {
    private Color humanColor = Color.PINK;

    public Human(World world, Point position){
        super(world, position, 5, 4);
    }

    @Override
    public Color getColor(){
        return humanColor;
    }

    @Override
    public String getNazwa(){
        return "Człowiek";
    }
}
